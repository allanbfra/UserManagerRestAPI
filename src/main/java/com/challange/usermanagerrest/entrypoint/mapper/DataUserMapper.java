package com.challange.usermanagerrest.entrypoint.mapper;

import com.challange.usermanagerrest.core.model.User;
import com.challange.usermanagerrest.entrypoint.controller.request.UserRequest;
import com.challange.usermanagerrest.entrypoint.controller.response.DataModelResponse;
import com.challange.usermanagerrest.entrypoint.controller.response.UserDataModelResponse;
import org.springframework.stereotype.Component;

@Component
public class DataUserMapper {

    public DataModelResponse<UserDataModelResponse> modelToResponse(User user) {
        return new DataModelResponse<>(
                new UserDataModelResponse(user.getName(), user.getCpf(), user.getBirthday()));
    }

    public User requestToModel(UserRequest request) {
        return new User(request.getName(), request.getCpf(), request.getBirthday());
    }
}
