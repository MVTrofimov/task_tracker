package com.example.task_tracker.mapper;

import com.example.task_tracker.entity.Task;
import com.example.task_tracker.entity.User;
import com.example.task_tracker.model.task.TaskResponse;
import com.example.task_tracker.model.task.TaskUpsertRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@DecoratedWith(TaskMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

    Task requestToTask(TaskUpsertRequest request);


    TaskResponse taskToTaskResponse(Task task);

}
