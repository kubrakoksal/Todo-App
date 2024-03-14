package com.kkoksal.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.data.couchbase")
@Getter
@Setter
public class CouchBaseProperties {
    private String connectionString;

    private String username;

    private String password;

    @Value("${spring.data.couchbase.bucket-name}")
    private String bucket;
}
