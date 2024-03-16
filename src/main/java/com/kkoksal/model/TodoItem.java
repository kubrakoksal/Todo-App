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
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;

    @Field
    private String title;

    @Field
    private String content;

    @Field(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Field("user_id")
    private String userId;

}
