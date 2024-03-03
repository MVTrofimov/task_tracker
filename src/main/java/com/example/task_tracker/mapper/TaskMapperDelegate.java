package com.example.task_tracker.mapper;

import com.example.task_tracker.entity.Task;
import com.example.task_tracker.entity.TaskStatus;
import com.example.task_tracker.entity.User;
import com.example.task_tracker.model.task.TaskResponse;
import com.example.task_tracker.model.task.TaskUpsertRequest;
import com.example.task_tracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.HashSet;
import java.util.Set;


public abstract class TaskMapperDelegate implements TaskMapper{

    @Autowired
    public UserService userService;

    @Override
    public Task requestToTask(TaskUpsertRequest request){
        Task task = new Task();

        task.setName(request.getName());
        task.setDescription(request.getDescription());
        task.setAssigneeId(request.getAssigneeId());
        task.setAuthorId(request.getAuthorId());
        task.setObserverIds(request.getObserverIds());

        if(request.getStatus() != null){
            for (TaskStatus t : TaskStatus.values()){
                if (request.getStatus().equals(t.label)){
                    task.setStatus(t);
                    break;
                }
            }
        }

        return task;
    }

    @Override
    public TaskResponse taskToTaskResponse(Task task){
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setName(task.getName());
        response.setDescription(task.getDescription());
        response.setCreatedAt(task.getCreatedAt());
        response.setUpdatedAt(task.getUpdatedAt());
        response.setStatus(task.getStatus());
        response.setAuthor(task.getAuthor());
        response.setAssignee(task.getAssignee());
        response.setObservers(task.getObservers());
        return response;
    }

}
