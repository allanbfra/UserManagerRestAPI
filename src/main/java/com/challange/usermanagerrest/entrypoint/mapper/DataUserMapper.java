package com.challange.usermanagerrest.entrypoint.mapper;

import com.challange.usermanagerrest.core.model.User;
import com.challange.usermanagerrest.entrypoint.controller.request.UserRequest;
import com.challange.usermanagerrest.entrypoint.controller.response.DataModelResponse;
import com.challange.usermanagerrest.entrypoint.controller.response.SingleDataModelResponse;
import com.challange.usermanagerrest.entrypoint.controller.response.UserDataModelResponse;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DataUserMapper {

    public SingleDataModelResponse<UserDataModelResponse> modelToResponse(User user) {
        return new SingleDataModelResponse<>(
                Objects.isNull(user) ? null :
                        new UserDataModelResponse(user.getName(), user.getCpf(), user.getBirthday()));
    }

    public User requestToModel(UserRequest request) {
        return Objects.isNull(request) ? null :
                new User(request.getName(), request.getCpf(), request.getBirthday());
    }
}
