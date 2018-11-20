package com.appzoneltd.lastmile.microservice.createsearchtopic.service;

import com.appzoneltd.lastmile.microservice.createsearchtopic.dao.QueryDTO;
import com.appzoneltd.lastmile.microservice.utility.Utils;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.Select;
import com.couchbase.client.java.query.dsl.Expression;
import com.couchbase.client.java.query.dsl.path.AsPath;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static com.couchbase.client.java.query.dsl.Expression.x;

/**
 * @author alaa.nabil
 */
@Service
public class CreateSearchTopicService {
    private final static Logger LOGGER = LoggerFactory.getLogger(CreateSearchTopicService.class);
    private final KafkaProducer<String, String> producer;
    private final CouchbaseTemplate couchbaseTemplate;
    private final ObjectMapper mapper;
    @Value("${kafka.topic}")
    private String topic;
    @Value("${server.id}")
    private String serverId;

    @Autowired
    public CreateSearchTopicService(CouchbaseTemplate couchbaseTemplate, KafkaProducer<String, String> producer) {
        this.couchbaseTemplate = couchbaseTemplate;
        this.producer = producer;
        this.mapper = new ObjectMapper();
    }

    /**
     * @param queryObj
     * @return
     * @throws JsonProcessingException
     */
    public QueryDTO createTopic(QueryDTO queryObj) throws JsonProcessingException {
        queryObj.set_ID(Utils.generateUUID());
        this.optimizeQuery(queryObj);
        List<QueryDTO> queryResult = buildQuery(queryObj);
        if (queryResult.isEmpty() || queryResult == null) {
            return createTopicInCouchbase(queryObj);
        } else {
            return queryResult.get(0);
        }
    }

    private void optimizeQuery(QueryDTO queryObj) {
        queryObj.getQueryModels().forEach(queryModel -> {
            List<String> stringList = queryModel.getQuery().getOrderStatus().stream().map(String::toUpperCase)
                    .collect(Collectors.toList());
            stringList.addAll(queryModel.getQuery().getOrderStatus());
            queryModel.getQuery().setOrderStatus(stringList);

            stringList = queryModel.getQuery().getVehicleStatus().stream().map(String::toUpperCase)
                    .collect(Collectors.toList());
            stringList.addAll(queryModel.getQuery().getVehicleStatus());
            queryModel.getQuery().setVehicleStatus(stringList);

            stringList = queryModel.getQuery().getOrderType().stream().map(String::toUpperCase)
                    .collect(Collectors.toList());
            stringList.addAll(queryModel.getQuery().getOrderType());
            queryModel.getQuery().setOrderType(stringList);
        });
    }

    /**
     * @param queryObject
     * @return
     */
    private List<QueryDTO> buildQuery(QueryDTO queryObject) {
        AsPath asPath = Select.select("port,queryModels,serverId").fromCurrentBucket();

        Expression expression = null;
        if (queryObject.getQueryModels() != null && !queryObject.getQueryModels().isEmpty())
            try {
                expression = x("queryModels")
                        .eq(JsonArray.fromJson(mapper.writeValueAsString(queryObject.getQueryModels())));
            } catch (JsonProcessingException e) {
                LOGGER.error(e.getMessage(), e);
            }

        return couchbaseTemplate.findByN1QLProjection(N1qlQuery.simple(asPath.where(expression)), QueryDTO.class);
    }

    private QueryDTO createTopicInCouchbase(QueryDTO queryObj) throws JsonProcessingException {
        int port = getPort();
        queryObj.setPort(port);
        queryObj.setServerId(Integer.parseInt(serverId));
        couchbaseTemplate.insert(queryObj);
        produce(this.topic, mapper.writeValueAsString(queryObj));
        return queryObj;
    }

    /**
     * @param jsonObject
     * @return
     */
    private Future<RecordMetadata> produce(String topic, String jsonObject) {
        return producer.send(new ProducerRecord<String, String>(topic, jsonObject));
    }


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
