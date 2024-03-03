package com.example.task_tracker.model.user;

import lombok.Data;

@Data
public class UserUpsertRequest {

    private String name;

    private String email;

}
