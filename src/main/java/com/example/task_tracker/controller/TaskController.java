package com.example.task_tracker.controller;
import com.example.task_tracker.mapper.TaskMapper;
import com.example.task_tracker.model.task.IdOfObserver;
import com.example.task_tracker.model.task.TaskResponse;
import com.example.task_tracker.model.task.TaskUpsertRequest;
import com.example.task_tracker.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private final TaskMapper taskMapper;

    @GetMapping
    public Flux<TaskResponse> getAllTasks(){
        return taskService.findAll().map(taskMapper::taskToTaskResponse);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<TaskResponse>> getTaskById(@PathVariable String id){
        return taskService.findById(id)
                .map(taskMapper::taskToTaskResponse)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<TaskResponse>> createTask(@RequestBody TaskUpsertRequest task){
        return taskService.save(taskMapper.requestToTask(task))
                .map(taskMapper::taskToTaskResponse)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<TaskResponse>> updateTask(@PathVariable String id, @RequestBody TaskUpsertRequest request){
        return taskService.update(id, taskMapper.requestToTask(request))
                .map(taskMapper::taskToTaskResponse)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/addObserver/{id}")
    public Mono<ResponseEntity<TaskResponse>> addObserverToTask(@PathVariable String id, @RequestBody IdOfObserver idOfObserver){
        return taskService.addObserver(id, idOfObserver.getId())
                .map(taskMapper::taskToTaskResponse)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteTask(@PathVariable String id){
        return taskService.deleteById(id).then(Mono.just(ResponseEntity.noContent().build()));
    }

}
