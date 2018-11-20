package com.appzoneltd.lastmile.microservice.pushnotification.config;

import com.appzoneltd.lastmile.microservice.pushnotification.dao.CustomerFirebaseToken;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;
import org.springframework.data.couchbase.repository.support.IndexManager;

import java.util.Arrays;
import java.util.List;

/**
 * @author alaa.nabil
 */
@Configuration
@EnableCouchbaseRepositories
public class CouchbaseConfigurations extends AbstractCouchbaseConfiguration {
    @Value("${couchbase.host}")
    private String host;
    @Value("${couchbase.bucket}")
    private String vehicleBucket;
    @Value("${couchbase.anotherBucket}")
    private String customerBucket;

    @Override
    protected List<String> getBootstrapHosts() {
        return Arrays.asList(host);
    }

    @Override
    protected String getBucketName() {
        return vehicleBucket;
    }

    @Override
    protected String getBucketPassword() {
        return "";
    }

    @Override
    protected CouchbaseEnvironment getEnvironment() {
        return DefaultCouchbaseEnvironment.builder().connectTimeout(10000L).socketConnectTimeout(5000).build();
    }

    @Override
    protected void configureRepositoryOperationsMapping(RepositoryOperationsMapping mapping) {
        try {
            mapping.mapEntity(CustomerFirebaseToken.class, customerTemplate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public IndexManager indexManager() {
        return new IndexManager(true, true, true);
    }

    @Bean
    public CouchbaseTemplate customerTemplate() throws Exception {
        CouchbaseTemplate template = new CouchbaseTemplate(couchbaseClusterInfo(), customerBucket(),
                mappingCouchbaseConverter(), translationService());
        template.setDefaultConsistency(getDefaultConsistency());
        return template;
    }

    @Bean
    public Bucket customerBucket() throws Exception {
        return couchbaseCluster().openBucket(customerBucket, "");
    }

}
