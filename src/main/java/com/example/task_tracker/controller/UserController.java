package com.example.task_tracker.controller;

import com.example.task_tracker.mapper.UserMapper;
import com.example.task_tracker.model.user.UserResponse;
import com.example.task_tracker.model.user.UserUpsertRequest;
import com.example.task_tracker.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    private final UserMapper userMapper;


    @GetMapping
    public Flux<UserResponse> getAllTasks(){
        return userService.findAll().map(userMapper::userToUserResponse);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserResponse>> getUserById(@PathVariable String id){
        return userService.findById(id)
                .map(userMapper::userToUserResponse)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<UserResponse>> createUser(@RequestBody UserUpsertRequest user){
        return userService.save(userMapper.requestToUser(user))
                .map(userMapper::userToUserResponse)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<UserResponse>> updateUser(@PathVariable String id, @RequestBody UserUpsertRequest request){
        return userService.update(id, userMapper.requestToUser(request))
                .map(userMapper::userToUserResponse)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable String id){
        return userService.deleteById(id).then(Mono.just(ResponseEntity.noContent().build()));
    }


}
