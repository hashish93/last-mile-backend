package com.appzoneltd.lastmile.microservices.syncengine.engine.job;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.appzoneltd.lastmile.microservices.syncengine.engine.model.ModuleExecutionModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QuartzJob implements Job {

	public QuartzJob() {
		configureKafkaProducer();
	}

	private KafkaTemplate<Integer, String> kafkaTemplate;

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		final ObjectMapper mapper = new ObjectMapper();
		JobDataMap data = jobExecutionContext.getJobDetail().getJobDataMap();
		ModuleExecutionModel moduleExecution = (ModuleExecutionModel) data.get("moduleExecution");
		System.out.println(moduleExecution.toString());
		try {
			kafkaTemplate.send(moduleExecution.getModuleName() + "_TOPIC", mapper.writeValueAsString(moduleExecution));
			// kafkaTemplate.send(new ProducerRecord<Integer,
			// String>(moduleExecution.getModuleName()+"_TOPIC",
			// mapper.writeValueAsString(moduleExecution)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	private void configureKafkaProducer() {
		// Properties props = new Properties();
		// props.put("bootstrap.servers", "192.168.88.90:9092");
		//// props.put("client.id", "SYNC_PRODUCER");
		// props.put("key.serializer",
		// "org.apache.kafka.common.serialization.IntegerSerializer");
		// props.put("value.serializer",
		// "org.apache.kafka.common.serialization.StringSerializer");
		// producer = new KafkaProducer<>(props);

		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.RETRIES_CONFIG, 0);
		configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		configProps.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		configProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		ProducerFactory factory = new DefaultKafkaProducerFactory<>(configProps);
		kafkaTemplate = new KafkaTemplate<>(factory, true);
	}
}
