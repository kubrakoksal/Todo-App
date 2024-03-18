package com.kkoksal.repository;

import com.kkoksal.model.TodoItem;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends CouchbaseRepository<TodoItem, String> {

    Page<TodoItem> findAllByUserId(Pageable pageable, String userId);

    Optional<TodoItem> findByUserIdAndId(String userId, String id);

    void deleteByUserIdAndId(String userId, String id);

}
