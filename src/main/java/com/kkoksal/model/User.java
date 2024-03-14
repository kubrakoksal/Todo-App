package com.kkoksal.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import java.time.LocalDateTime;

@Document
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;

    @Field(name = "first_name")
    private String firstName;

    @Field(name = "last_name")
    private String lastName;

    @Field
    private String email;

    @Field
    private String password;

    @Field(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

}
