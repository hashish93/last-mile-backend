package com.appzoneltd.lastmile.microservice.opensocket.websocket;

import static com.couchbase.client.java.query.dsl.Expression.x;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.appzoneltd.lastmile.microservice.opensocket.Configurations;
import com.appzoneltd.lastmile.microservice.opensocket.entity.CustomerQuery;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.Select;
import com.couchbase.client.java.query.dsl.Expression;
import com.couchbase.client.java.query.dsl.path.AsPath;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.JksOptions;

public class CustomerSearchTopicSocket extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchTopicSocket.class);
    private static final io.vertx.core.json.JsonObject COUCHBASE_CONFIG = Configurations.getCouchbaseProperties();
    private static final Long SLEEP_TIME = Configurations.getThreadSleepProperties();

    private final Cluster cluster;
    private final Bucket bucket;
    private final Set<String> sessions;
    private final CustomerQuery queryObject;
    private final ExecutorService executorService;

    public CustomerSearchTopicSocket(CustomerQuery queryObject) {
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
//                new HttpServerOptions()
//                .setSsl(true)
//                .setKeyStoreOptions(new JksOptions().setPath("keystore.jks").setPassword("appzone"))
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
     */
    private class RunThread implements Runnable {

        private EventBus eventBus;
        private AsPath statement;
        private Expression expression;

        public RunThread(EventBus eventBus) {
            this.eventBus = eventBus;
            buildQuery();
        }

        /**
         *
         */
        private void buildQuery() {
            if (queryObject != null)
                statement = Select.select("vehicleId, driverId, driverName, driverNumber, hubId, rating, location")
                        .fromCurrentBucket();
            if (queryObject.getVehicleId() != null)
                expression = x("vehicleId").eq(queryObject.getVehicleId());
            if (queryObject.getHubId() != null)
                expression = expression.and(x("hubId").eq(queryObject.getHubId()));
            if (queryObject.getDriverId() != null)
                expression = expression.and(x("driverId").eq(queryObject.getDriverId()));
            // queryStatements.put(queryModel, statement.where(expression));
        }

        @Override
        public void run() {
            N1qlQueryResult queryResult;
            JsonObject jsonObject = JsonObject.create();
            while (true) {
                try {
                    queryResult = bucket.query(N1qlQuery.simple(statement.where(expression.and("workShiftFrom")
                            .lte(getTimeInMillis()).and("workShiftTo").gte(getTimeInMillis()))));
                    if (queryResult.allRows() != null && !queryResult.allRows().isEmpty())
                        jsonObject = queryResult.allRows().get(0).value();

                    sendToClients(jsonObject);

                    Thread.sleep(SLEEP_TIME);
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }

        }

        private void sendToClients(JsonObject jsonObject) {
            for (String sessionId : sessions) {
                eventBus.send(sessionId, jsonObject.toString());
            }
        }

        private Long getTimeInMillis() {
            Date date = new Date();
            date.setYear(1970);
            date.setMonth(1);
            date.setDate(1);
            date.setSeconds(1);
            return date.toInstant().toEpochMilli();
        }

    }
}
