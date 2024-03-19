package com.example.task_tracker.model.user;

import com.example.task_tracker.entity.RoleType;
import lombok.Data;

import java.util.Set;

@Data
public class UserResponse {

    private String id;

    private String name;

    private String email;

    private Set<RoleType> roles;
}
