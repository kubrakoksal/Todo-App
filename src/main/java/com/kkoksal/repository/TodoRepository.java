package com.kkoksal.repository;

import com.kkoksal.model.TodoItem;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends CouchbaseRepository<TodoItem, String> {

    List<TodoItem> findAllByUserId(String userId);

    Optional<TodoItem> findByUserIdAndId(String userId, String id);

    void deleteByUserIdAndId(String userId, String id);

}
