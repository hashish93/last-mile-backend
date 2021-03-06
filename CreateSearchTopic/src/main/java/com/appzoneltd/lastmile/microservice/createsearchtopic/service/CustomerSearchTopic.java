package com.appzoneltd.lastmile.microservice.createsearchtopic.service;

import static com.couchbase.client.java.query.dsl.Expression.x;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.createsearchtopic.dao.CustomerQuery;
import com.appzoneltd.lastmile.microservice.utility.Utils;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.Select;
import com.couchbase.client.java.query.dsl.Expression;
import com.couchbase.client.java.query.dsl.path.AsPath;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustomerSearchTopic {
    private final static Logger LOGGER = LoggerFactory.getLogger(CreateSearchTopicService.class);

    @Value("${kafka.topic}")
    private String topic;
    @Value("${server.id}")
    private String serverId;
    private final KafkaProducer<String, String> producer;
    private final CouchbaseTemplate couchbaseTemplate;
    private final ObjectMapper mapper;

    @Autowired
    public CustomerSearchTopic(CouchbaseTemplate couchbaseTemplate, KafkaProducer<String, String> producer) {
        this.couchbaseTemplate = couchbaseTemplate;
        this.producer = producer;
        this.mapper = new ObjectMapper();
    }

    /**
     * @param queryObj
     * @return
     * @throws JsonProcessingException
     */
    public CustomerQuery createCustomerTopic(CustomerQuery queryObj) throws JsonProcessingException {
        queryObj.set_ID(Utils.generateUUID());
        List<CustomerQuery> queryResult = buildQuery(queryObj);
        if (queryResult.isEmpty() || queryResult == null) {
            return createTopicInCouchbase(queryObj);
        } else {
            return queryResult.get(0);
        }
    }

    /**
     * @param queryObject
     * @return
     */
    private List<CustomerQuery> buildQuery(CustomerQuery queryObject) {
        AsPath asPath = Select.select("port,vehicleId,hubId,driverId,serverId").fromCurrentBucket();

        Expression expression = x("isCustomerTopic").eq(true);
        if (queryObject.getHubId() != null)
            expression = expression.and(x("hubId").eq(queryObject.getHubId()));

        if (queryObject.getVehicleId() != null)
            expression = expression.and(x("vehicleId").eq(queryObject.getVehicleId()));

        if (queryObject.getDriverId() != null)
            expression = expression.and(x("driverId").eq(queryObject.getDriverId()));

        return couchbaseTemplate.findByN1QLProjection(N1qlQuery.simple(asPath.where(expression)), CustomerQuery.class);
    }

    private CustomerQuery createTopicInCouchbase(CustomerQuery queryObj) throws JsonProcessingException {
        int port = getPort();
        queryObj.setPort(port);
        queryObj.setServerId(Integer.parseInt(serverId));
        couchbaseTemplate.insert(queryObj);
        produce(mapper.writeValueAsString(queryObj));
        return queryObj;
    }

    /**
     * @param jsonObject
     * @return
     */
    private Future<RecordMetadata> produce(String jsonObject) {
        return producer.send(new ProducerRecord<String, String>(this.topic, "CUSTOMER_SEARCH_TOPIC", jsonObject));
    }

    /**
     * @param port
     * @return
     */
    private int getPort() {
        int port = generateRandomPortNumber();
        if (!isAvailable(port))
            port = getPort();

        return port;
    }

    private int generateRandomPortNumber() {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        builder.append(random.nextInt(65535 - 1024) + 1024);
        return Integer.valueOf(builder.toString());
    }

    /**
     * @param port
     */
    private boolean isAvailable(int port) {
        ServerSocket serverSocket = null;
        boolean availability = false;
        try {
            N1qlQueryResult queryObjects = couchbaseTemplate.queryN1QL(N1qlQuery.simple(
                    "SELECT port FROM " + couchbaseTemplate.getCouchbaseBucket().name() + " WHERE port=" + port));
            // findByN1QLProjection(N1qlQuery.simple(Select.select("*").fromCurrentBucket().where(x("port").eq(port))),
            // QueryDTO.class);
            if (queryObjects != null && queryObjects.allRows().isEmpty()) {
                serverSocket = new ServerSocket(port);
                availability = true;
            } else {
                availability = false;
            }
        } catch (IOException e) {
            return false;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    LOGGER.error("Error on Closing : " + e.getMessage());
                }
            }
        }
        return availability;
    }
}
