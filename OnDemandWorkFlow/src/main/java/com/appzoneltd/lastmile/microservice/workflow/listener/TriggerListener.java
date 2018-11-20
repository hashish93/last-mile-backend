package com.appzoneltd.lastmile.microservice.workflow.listener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.postgresql.PGNotification;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.postgresql.PGConnection;

import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowProcessExecution;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class TriggerListener extends Thread {

	private static final String NULL_ACTION_ID = "##";
	private KafkaProducer<Integer, String> producer;
	private final String topic = "PROCESS_COMPLETE";
	private Connection connection;
	private PGConnection pgconn;
	private String lastProcessInstance = "";
	private Disposable timer;

	public TriggerListener(Connection connection) throws SQLException {
		
		// Getting Connection
		this.connection = connection;
		this.pgconn = (org.postgresql.PGConnection) connection;
		Statement stmt = connection.createStatement();
		stmt.execute("LISTEN urlwork");
		stmt.close();

		configureKafkaProducer();
	}

	private void configureKafkaProducer() {
		/// Properties
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("client.id", "LISTENER_PRODUCER");
		props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer<>(props);
	}

	public void run() {
		while (true) {
			// Connect to Database
			PGNotification notifications[] = null;
			try {
				notifications = pgconn.getNotifications();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (notifications != null) {
				for (int i = 0; i < notifications.length; i++) {
					System.out.println(">>>>>>>>>>>>> NOTIFICATION >>> " + notifications[i].getName() + " , PARAMETER "
							+ notifications[i].getParameter());
					if (!notifications[i].getParameter().isEmpty()) {
						processNotification(notifications[i].getParameter());
					} // end if Condition
				} // end for Loop
			}
		} // end while Loop
	}// end Run Method

	private void processNotification(String parameter) {
		//
		System.out.println("*********** CLEAR TIMER");
		clearTimerIfNotNull();
		JSONObject jsonObject = new JSONObject(parameter);
		String processInstanceId = (String) jsonObject.get("proc_inst_id_");
		String executionId = (String) jsonObject.get("execution_id_");
		System.out.println("**** PROCESS INSTANCE " + processInstanceId);
		System.out.println("**** EXECUTION " + executionId);
		timer = createTimer(processInstanceId);
		if (lastProcessInstance.isEmpty()) {
			lastProcessInstance = processInstanceId;
		} else if (!processInstanceId.equals(lastProcessInstance)) {
			processInstanceExecution(lastProcessInstance);
			lastProcessInstance = processInstanceId;
		}
	}

	private void processInstanceExecution(String processInstance) {
		System.out.println(">>>>>>>>>> LOGIC EXECUTION " + processInstance);
		// Create PackageQuery
		Observable<String> packageIdQuery = Observable.create(createPackageQuery(processInstance));

		Observable<String> actionNameQuery = Observable.create(createExecutionStepQuery(processInstance));

		Observable.zip(packageIdQuery, actionNameQuery, collectQueryResult())
				.subscribe(sendProcessInstanceResultToKafka());

	}

	private Disposable createTimer(final String processInstance) {
		return Observable.interval(500L, TimeUnit.MILLISECONDS).subscribe(new Consumer<Long>() {
			@Override
			public void accept(Long arg0) throws Exception {
				System.out.println("*********** TIMER FIRED FOR PROCESS: " + processInstance);
				processInstanceExecution(processInstance);
				clearTimerIfNotNull();
				lastProcessInstance = "";
			}
		});
	}

	private void clearTimerIfNotNull() {
		if (timer != null) {
			timer.dispose();
			timer = null;
		}
	}

	private ObservableOnSubscribe<String> createPackageQuery(String processInstanceId) {
		return new ObservableOnSubscribe<String>() {
			@Override
			public void subscribe(ObservableEmitter<String> emitter) throws Exception {
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("select text_ as package_id from act_hi_varinst " + " where proc_inst_id_ = '"
								+ processInstanceId + "' and name_ = 'packageId' ORDER BY id_ DESC LIMIT 1");

				resultSet.next();
				String packageId = resultSet.getString("package_id");
				emitter.onNext(packageId);
			}
		};
	}

	private ObservableOnSubscribe<String> createExecutionStepQuery(String processInstanceId) {
		return new ObservableOnSubscribe<String>() {
			@Override
			public void subscribe(ObservableEmitter<String> emitter) throws Exception {
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select act_id_ as action_id from act_hi_actinst "
						+ " where proc_inst_id_ = '" + processInstanceId + "' ORDER BY id_ DESC LIMIT 1");
				resultSet.next();
				String actionId = resultSet.getString("action_id");
				emitter.onNext(actionId);
			}
		};
	}

	private BiFunction<String, String, String> collectQueryResult() {
		return new BiFunction<String, String, String>() {
			@Override
			public String apply(String packageId, String actionId) throws Exception {
				System.out.println("*************** Preparing SEND TO KAFKA " + actionId);
				if (!isWaitAction(actionId)) {
					return NULL_ACTION_ID;
				} else {
					return buildObjectAndConvertToJson(packageId, actionId);
				}
			}

			private boolean isWaitAction(String actionId) {
				if(actionId.length()>=4){
					String actionPrefix=actionId.substring(0,4);
					return actionPrefix.equalsIgnoreCase("wait");
				}
				return false;
			}

		};
	}

	private String buildObjectAndConvertToJson(String packageId, String actionId) throws JsonProcessingException {
		WorkflowProcessExecution workflowProcessExecution = new WorkflowProcessExecution();
		workflowProcessExecution.setPackageId(Long.parseLong(packageId));
		workflowProcessExecution.setActionId(actionId);
		/// Convert to Json
		ObjectMapper workflowProcessMapper = new ObjectMapper();
		return workflowProcessMapper.writeValueAsString(workflowProcessExecution);
	}

	private Consumer<String> sendProcessInstanceResultToKafka() {
		return new Consumer<String>() {
			@Override
			public void accept(String workflowProcessJson) throws Exception {
				System.out.println("*************** SEND TO KAFKA " + workflowProcessJson);
				if (!NULL_ACTION_ID.equals(workflowProcessJson)) {
					producer.send(new ProducerRecord<>(topic, workflowProcessJson));
				}
			}
		};
	}

}
