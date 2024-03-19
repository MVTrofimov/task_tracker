package com.example.task_tracker.security;

import com.example.task_tracker.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsPasswordService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {

    private final UserService userService;


    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userService.findByUserName(username).map(AppUserPrincipal::new);
    }
}
