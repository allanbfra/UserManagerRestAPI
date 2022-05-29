package com.challange.usermanagerrest.core.service;

import com.challange.usermanagerrest.core.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> listUsers(int pageIndex, int pageSize);

    User getUserByCpf(String cpf);

    User saveUser(User user);
}
