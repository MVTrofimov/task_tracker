package com.example.task_tracker.model.task;

import com.example.task_tracker.entity.TaskStatus;
import com.example.task_tracker.entity.User;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Data
public class TaskResponse {

    private String id;
    private String name;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
    private TaskStatus status;
    private User author;
    private User assignee;
    private Set<User> observers;

}
