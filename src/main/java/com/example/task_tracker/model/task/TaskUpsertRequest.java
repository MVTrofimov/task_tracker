package com.example.task_tracker.model.task;

import com.example.task_tracker.entity.TaskStatus;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.Set;

@Data
public class TaskUpsertRequest {

    private String name;
    private String description;
    private String status;
    private String authorId;
    private String assigneeId;
    @Field("observerIds")
    private Set<String> observerIds;

}
