package com.example.task_tracker.controller;

import com.example.task_tracker.mapper.UserMapper;
import com.example.task_tracker.model.user.UserResponse;
import com.example.task_tracker.model.user.UserUpsertRequest;
import com.example.task_tracker.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping("/account")
    public Mono<ResponseEntity<UserResponse>> createUserAccount(@RequestBody UserUpsertRequest request){
        return userService.save(userMapper.requestToUser(request))
                .map(userMapper::userToUserResponse)
                .map(ResponseEntity::ok);
    }
}
