package com.kkoksal.config;

import com.kkoksal.properties.CouchBaseProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;

@Configuration
@RequiredArgsConstructor
@EnableCouchbaseRepositories(basePackages = {"com.kkoksal.repository"})
public class DatabaseConfiguration extends AbstractCouchbaseConfiguration{

    private final CouchBaseProperties couchBaseProperties;

    @Override
    public String getConnectionString() {
        return couchBaseProperties.getConnectionString();
    }

    @Override
    public String getUserName() {
        return couchBaseProperties.getUsername();
    }

    @Override
    public String getPassword() {
        return couchBaseProperties.getPassword();
    }

    @Override
    public String getBucketName() {
        return couchBaseProperties.getBucket();
    }

    public QueryLookupStrategy.Key getQueryLookupStrategyKey() {
        return QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND;
    }

}
