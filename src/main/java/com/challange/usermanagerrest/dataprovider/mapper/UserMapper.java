package com.challange.usermanagerrest.dataprovider.mapper;

import com.challange.usermanagerrest.core.model.User;
import com.challange.usermanagerrest.dataprovider.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserMapper {

    public User entityToModel(UserEntity entity) {
        return Objects.isNull(entity) ? null : new User(entity.getName(), entity.getCpf(), entity.getBirthday());
    }

    public UserEntity modelToEntity(User model) {
        return Objects.isNull(model) ? null : new UserEntity(model.getName(), model.getCpf(), model.getBirthday());
    }
}
