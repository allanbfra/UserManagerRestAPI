package com.challange.usermanagerrest.dataprovider.mapper;

import com.challange.usermanagerrest.core.model.User;
import com.challange.usermanagerrest.dataprovider.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User entityToModel(UserEntity entity) {
        return new User(entity.getId(), entity.getName(), entity.getCpf(), entity.getBirthday());
    }

    public UserEntity modelToEntity(User model) {
        return new UserEntity(model.getName(), model.getCpf(), model.getBirthday());
    }
}
