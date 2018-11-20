package com.appzoneltd.lastmile.microservice.opensocket.websocket;

import static com.couchbase.client.java.query.dsl.Expression.x;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.appzoneltd.lastmile.microservice.opensocket.Configurations;
import com.appzoneltd.lastmile.microservice.opensocket.entity.QueryDTO;
import com.appzoneltd.lastmile.microservice.opensocket.entity.QueryModel;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.couchbase.client.java.query.Select;
import com.couchbase.client.java.query.dsl.Expression;
import com.couchbase.client.java.query.dsl.path.AsPath;
import com.couchbase.client.java.query.dsl.path.GroupByPath;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.JksOptions;

/**
 * @author alaa.nabil
 *
 */
public class SearchTopicSocket extends AbstractVerticle {
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchTopicSocket.class);
	private static final io.vertx.core.json.JsonObject COUCHBASE_CONFIG = Configurations.getCouchbaseProperties();
	private static final Long SLEEP_TIME = Configurations.getThreadSleepProperties();

	private final Cluster cluster;
	private final Bucket bucket;
	private final Set<String> sessions;
	private final QueryDTO queryObject;
	private final ExecutorService executorService;

	public SearchTopicSocket(QueryDTO queryObject) {
		this.queryObject = queryObject;
		this.executorService = Executors.newSingleThreadExecutor();
		this.cluster = CouchbaseCluster.create(
				DefaultCouchbaseEnvironment.builder().connectTimeout(Long.MAX_VALUE).build(),
				Arrays.asList(COUCHBASE_CONFIG.getString("node")));
		this.bucket = cluster.openBucket(COUCHBASE_CONFIG.getString("bucket"));
		this.sessions = new HashSet<String>();
	}

	@Override
	public void start() throws Exception {
		vertx.createHttpServer(
//				new HttpServerOptions()
//              .setSsl(true)
//				.setKeyStoreOptions(new JksOptions().setPath("keystore.jks").setPassword("appzone"))
		)
				.websocketHandler(webSocket -> {
					sessions.add(webSocket.textHandlerID());
					executorService.execute(new RunThread(vertx.eventBus()));
					webSocket.closeHandler(close -> {
						sessions.remove(webSocket.textHandlerID());
					});

					if (Configurations.isDevelopment()) {
						Runtime.getRuntime().addShutdownHook(new Thread() {
							public void run() {
								executorService.shutdown();
								bucket.close();
								cluster.disconnect();
								webSocket.close();
							}
						});
					}

				}).listen(queryObject.getPort());
	}

	/**
	 * @author alaa.nabil
	 *
	 */
	private class RunThread implements Runnable {

		private EventBus eventBus;
		private AsPath statement;
		private Expression expression;
		private Map<QueryModel, GroupByPath> queryStatements;

		public RunThread(EventBus eventBus) {
			this.eventBus = eventBus;
			buildQuery();
		}

		/**
		 * 
		 */
		private void buildQuery() {
			queryStatements = new HashMap<>();
			for (QueryModel queryModel : queryObject.getQueryModels()) {
				if (queryModel.getQuery().isOrderData())
					statement = Select.select("vehicleId, jobOrders").fromCurrentBucket();
				if (queryModel.getQuery().isVehicleData())
					statement = Select
							.select("hubId, vehicleId, vehicleStatus, location, driverName, driverNumber, jobOrders, rating, purpose, capacity")
							.fromCurrentBucket();
				if (queryModel.getQuery().getHubId() != null)
					expression = x("hubId").eq(queryModel.getQuery().getHubId());
				if (queryModel.getQuery().getVehicleId() != null)
					expression = expression.and(x("vehicleId").eq(queryModel.getQuery().getVehicleId()));
				if (queryModel.getQuery().getVehicleStatus() != null
						&& !queryModel.getQuery().getVehicleStatus().isEmpty())
					expression = expression
							.and(x("vehicleStatus").in(JsonArray.from(queryModel.getQuery().getVehicleStatus())));
				queryStatements.put(queryModel, statement.where(expression));
			}
		}

		@Override
		public void run() {
			N1qlQueryResult queryResult;
			JsonArray jsonArray;
			while (true) {
				jsonArray = JsonArray.create();
				try {
					for (Entry<QueryModel, GroupByPath> entry : queryStatements.entrySet()) {
						queryResult = bucket.query(N1qlQuery.simple(entry.getValue()));
						if (entry.getKey().getQuery().isCount()) {
							jsonArray.add(countRequired(queryResult, entry.getKey()));
						} else {
							jsonArray.add(dataRequired(queryResult, entry.getKey()));
						}
					}

					sendToClients(jsonArray);

					Thread.sleep(SLEEP_TIME);
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
				}
			}

		}

		private void sendToClients(JsonArray jsonArray) {
			for (String sessionId : sessions) {
				eventBus.send(sessionId, jsonArray.toString());
			}
		}

		private JsonObject dataRequired(N1qlQueryResult queryResult, QueryModel queryModel) {
			JsonObject jsonObject = JsonObject.create().put("queryName", queryModel.getQueryName());
			JsonArray rows = JsonArray.create();
			if (queryModel.getQuery().isVehicleData()) {
				for (N1qlQueryRow row : queryResult) {
					rows.add(row.value());
				}
			} else if (queryModel.getQuery().isOrderData()) {
				JsonArray jobOrders;
				for (N1qlQueryRow row : queryResult) {
					jobOrders = JsonArray.create();
					for (Object object : row.value().getArray("jobOrders")) {
						if (queryModel.getQuery().getOrderType()
								.contains(((JsonObject) object).getString("orderType").toLowerCase())) {
							if (queryModel.getQuery().getOrderStatus() != null
									&& !queryModel.getQuery().getOrderStatus().isEmpty()) {
								if (queryModel.getQuery().getOrderStatus()
										.contains(((JsonObject) object).getString("orderStatus").toLowerCase())) {
									jobOrders.add(object);
								}
							} else {
								jobOrders.add(object);
							}
						}
					}
					if (!jobOrders.isEmpty())
						rows.add(row.value().put("jobOrders", jobOrders));
				}
			}
			return jsonObject.put("result", rows);
		}

		private JsonObject countRequired(N1qlQueryResult queryResult, QueryModel queryModel) {
			int count = 0;
			JsonObject jsonObject = JsonObject.create().put("queryName", queryModel.getQueryName());
			if (queryModel.getQuery().isVehicleData()) {
				count = queryResult.allRows().size();
			} else if (queryModel.getQuery().isOrderData()) {
				for (N1qlQueryRow row : queryResult) {
					for (Object object : row.value().getArray("jobOrders")) {
						if (queryModel.getQuery().getOrderType()
								.contains(((JsonObject) object).getString("orderType").toLowerCase())) {
							if (queryModel.getQuery().getOrderStatus() != null
									&& !queryModel.getQuery().getOrderStatus().isEmpty()) {
								if (queryModel.getQuery().getOrderStatus()
										.contains(((JsonObject) object).getString("orderStatus").toLowerCase()))
									count++;
							} else {
								count++;
							}
						}
					}
				}
			}
			return jsonObject.put("count", count);
		}
	}
}
