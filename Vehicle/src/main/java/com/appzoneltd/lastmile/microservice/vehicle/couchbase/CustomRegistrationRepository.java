package com.appzoneltd.lastmile.microservice.vehicle.couchbase;

import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alaa.nabil on 9/17/2017.
 */
@Repository
public class CustomRegistrationRepository {
    private final CouchbaseTemplate couchbaseTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public CustomRegistrationRepository(CouchbaseTemplate couchbaseTemplate) {
        this.couchbaseTemplate = couchbaseTemplate;
        objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<RegistrationModel> findAllByHubIds(List<Long> hubIds) {
        final String statement = "SELECT * FROM vehicle_tracking WHERE hubId IN " + hubIds;
        final N1qlQueryResult n1qlQueryRows = couchbaseTemplate.queryN1QL(N1qlQuery.simple(statement));
        return extractResult(n1qlQueryRows);
    }

    private List<RegistrationModel> extractResult(N1qlQueryResult n1qlQueryRows) {
        return n1qlQueryRows.allRows().isEmpty() ? new ArrayList<>()
                : n1qlQueryRows.allRows().stream().map(n1qlQueryRow -> {
            RegistrationModel registrationModel = null;
            try {
                registrationModel = objectMapper.readValue(n1qlQueryRow.value().getObject("vehicle_tracking").toString(), RegistrationModel.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return registrationModel;
        }).collect(Collectors.toList());
    }

    public List<RegistrationModel> findAll() {
        final N1qlQueryResult n1qlQueryRows = couchbaseTemplate.queryN1QL(N1qlQuery.simple("SELECT * FROM vehicle_tracking"));
        return extractResult(n1qlQueryRows);
    }
}
