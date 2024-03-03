package com.example.task_tracker.mapper;

import com.example.task_tracker.entity.User;
import com.example.task_tracker.model.user.UserResponse;
import com.example.task_tracker.model.user.UserUpsertRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User requestToUser(UserUpsertRequest request);

    UserResponse userToUserResponse(User user);

}
