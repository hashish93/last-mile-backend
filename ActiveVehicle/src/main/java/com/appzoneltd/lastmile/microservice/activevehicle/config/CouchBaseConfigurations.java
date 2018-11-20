package com.appzoneltd.lastmile.microservice.activevehicle.config;

import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.couchbase.repository.support.IndexManager;

import java.util.Arrays;
import java.util.List;

/**
 * @author alaa.nabil
 */
@Configuration
@EnableCouchbaseRepositories
public class CouchBaseConfigurations extends AbstractCouchbaseConfiguration {
    @Value("${spring.couchbase.bootstrap-hosts}")
    private String bootstrapHost;
    @Value("${spring.couchbase.bucket.name}")
    private String bucket;

    @Override
    protected List<String> getBootstrapHosts() {
        return Arrays.asList(bootstrapHost);
    }

    @Override
    protected String getBucketName() {
        return bucket;
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
    public IndexManager indexManager() {
        return new IndexManager(true, true, true);
    }
}
