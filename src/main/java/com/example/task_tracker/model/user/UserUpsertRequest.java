package com.example.task_tracker.model.user;

import com.example.task_tracker.entity.RoleType;
import lombok.Data;

import java.util.Set;

@Data
public class UserUpsertRequest {

    private String name;

    private String password;

    private String email;

    private Set<RoleType> roles;

}
