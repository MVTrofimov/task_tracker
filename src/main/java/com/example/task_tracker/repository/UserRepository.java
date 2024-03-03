package com.example.task_tracker.repository;

import com.example.task_tracker.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Flux<User> findAllByIdIn(Set<String> ids);
}
