package com.kkoksal.repository;

import com.kkoksal.model.User;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CouchbaseRepository<User, String>{

    Boolean existsByEmail(String email);

    Boolean existsByUserName(String userName);

    Optional<User> findByUserNameOrEmail(String username, String email);
}
