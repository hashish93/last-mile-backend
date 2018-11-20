package com.appzoneltd.lastmile.microservice.pushnotification.dao;

import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

/**
 * @author alaa.nabil
 */
@Repository
public class NotificationTokenRepository {
    private final static String DRIVER_BUCKET = "vehicle_tracking";
    private final static String CUSTOMER_BUCKET = "customer_app_firebase_token";
    private final CouchbaseTemplate couchbaseTemplate;
    private ObjectMapper mapper;

    @Autowired
    public NotificationTokenRepository(CouchbaseTemplate couchbaseTemplate) {
        this.couchbaseTemplate = couchbaseTemplate;
        
        this.mapper = new ObjectMapper();
    }

    public String getDriverFirebaseToken(Object value) {
        List<N1qlQueryRow> rows = couchbaseTemplate
                .queryN1QL(
                        N1qlQuery.simple("SELECT firebaseToken FROM " + DRIVER_BUCKET + " WHERE driverId = " + value))
                .allRows();
        return rows != null && !rows.isEmpty() ? rows.get(0).value().getString("firebaseToken") : null;
    }

    public List<FirebaseTokenObject> CustomerFirebaseTokens(Object value) {
        List<N1qlQueryRow> rows = couchbaseTemplate
                .queryN1QL(
                        N1qlQuery.simple("SELECT firebaseTokens FROM " + CUSTOMER_BUCKET + " WHERE userId = " + value))
                .allRows();
        
        String out =  rows != null && !rows.isEmpty() ? rows.get(0).value().getArray("firebaseTokens").toString() : null;
        
        if (out != null) {
        	System.out.println(out);
        	try {
				List<FirebaseTokenObject> toReturn = mapper.readValue(out, new TypeReference<List<FirebaseTokenObject>>(){}) ;
				return toReturn;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
        }
        return null;
    }

}
