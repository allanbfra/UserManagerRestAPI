package com.challange.usermanagerrest.dataprovider.impl;

import com.challange.usermanagerrest.core.model.User;
import com.challange.usermanagerrest.core.service.UserService;
import com.challange.usermanagerrest.dataprovider.entity.UserEntity;
import com.challange.usermanagerrest.dataprovider.mapper.UserMapper;
import com.challange.usermanagerrest.dataprovider.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> listUsers(int pageIndex, int pageSize) {
        List<UserEntity> userList =
                userRepository.findAll(PageRequest
                        .of(pageIndex, pageSize, Sort.by("name").ascending())).toList();

        return userList.stream().map(user -> userMapper.entityToModel(user)).collect(Collectors.toList());
    }

    @Override
    public User getUserByCpf(String cpf) {
        return userMapper.entityToModel(userRepository.findByCpf(cpf));
    }

    @Override
    public User saveUser(User user) {
        return userMapper.entityToModel(userRepository.save(userMapper.modelToEntity(user)));
    }
}
