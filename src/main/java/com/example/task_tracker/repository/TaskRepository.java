package com.example.task_tracker.repository;

import com.example.task_tracker.entity.Task;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TaskRepository extends ReactiveMongoRepository<Task, String> {
}
