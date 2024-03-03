package com.example.task_tracker.services;


import com.example.task_tracker.entity.Task;
import com.example.task_tracker.entity.User;
import com.example.task_tracker.repository.TaskRepository;
import com.example.task_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.time.Instant;
import java.util.HashSet;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;


    public Flux<Task> findAll() {
        Flux<Task> taskFlux = taskRepository.findAll();

        return taskFlux.flatMap(
                task -> {
                    Mono<User> author = userRepository.findById(task.getAuthorId());
                    Mono<User> assignee = userRepository.findById(task.getAssigneeId());
                    Flux<User> observers = userRepository.findAllByIdIn(task.getObserverIds());


                    Task readyTask = new Task(
                            task.getId(),
                            task.getName(),
                            task.getDescription(),
                            task.getCreatedAt(),
                            task.getUpdatedAt(),
                            task.getStatus(),
                            task.getAuthorId(),
                            task.getAssigneeId(),
                            task.getObserverIds()
                    );

                    return Mono.zip(author, assignee, observers.collectList()).map(
                            tuple -> {
                                readyTask.setAuthor(tuple.getT1());
                                readyTask.setAssignee(tuple.getT2());
                                readyTask.setObservers(new HashSet<>(tuple.getT3()));
                                return readyTask;
                            }

                    );
                }
        );
    }

    public Mono<Task> findById(String id) {
        return taskRepository.findById(id).flatMap(
                task -> Mono.zip(userRepository.findById(task.getAuthorId()), userRepository.findById(task.getAssigneeId()), userRepository.findAllByIdIn(task.getObserverIds()).collectList()).map(
                        tuple -> {
                            task.setAuthor(tuple.getT1());
                            task.setAssignee(tuple.getT2());
                            task.setObservers(new HashSet<>(tuple.getT3()));
                            return task;
                        }

                )
        );
    }

    public Mono<Task> save(Task task){
        if (task.getId() == null) task.setId(UUID.randomUUID().toString());
        if (task.getCreatedAt() == null) task.setCreatedAt(Instant.now());
        return taskRepository.save(task).flatMap(
                anotherTask -> Mono.zip(userRepository.findById(anotherTask.getAuthorId()), userRepository.findById(anotherTask.getAssigneeId()), userRepository.findAllByIdIn(anotherTask.getObserverIds()).collectList()).map(
                        tuple -> {
                            anotherTask.setAuthor(tuple.getT1());
                            anotherTask.setAssignee(tuple.getT2());
                            anotherTask.setObservers(new HashSet<>(tuple.getT3()));
                            return anotherTask;
                        }

                )
        );
    }

    public Mono<Task> update(String id, Task task){
        return findById(id).flatMap(itemForUpdate -> {
            if (StringUtils.hasText(task.getName())){
                itemForUpdate.setName(task.getName());
            }
            if (StringUtils.hasText(task.getDescription())){
                itemForUpdate.setDescription(task.getDescription());
            }
            if (StringUtils.hasText(task.getDescription())){
                itemForUpdate.setDescription(task.getDescription());
            }
            if (StringUtils.hasText(task.getDescription())){
                itemForUpdate.setDescription(task.getDescription());
            }
            if (StringUtils.hasText(task.getAuthorId())){
                itemForUpdate.setAuthorId(task.getAuthorId());
            }
            if (StringUtils.hasText(task.getAssigneeId())){
                itemForUpdate.setAssigneeId(task.getAssigneeId());
            }
            if (task.getStatus() != null){
                itemForUpdate.setStatus(task.getStatus());
            }
            if (task.getObserverIds() != null){
                itemForUpdate.setObserverIds(task.getObserverIds());
            }
            itemForUpdate.setUpdatedAt(Instant.now());
            return save(itemForUpdate);
        });
    }

    public Mono<Task> addObserver(String id, String idOfObserver){
        return findById(id).flatMap(task -> {
            task.getObserverIds().add(idOfObserver);
            return save(task);
        });
    }

    public Mono<Void> deleteById(String id){
        return taskRepository.deleteById(id);
    }
}
