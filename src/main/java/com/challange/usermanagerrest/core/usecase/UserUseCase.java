package com.challange.usermanagerrest.core.usecase;

import com.challange.usermanagerrest.core.model.User;
import com.challange.usermanagerrest.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserUseCase {

    @Autowired
    private UserService service;
    @Autowired
    private CpfValidator cpfValidator;
    public User validateAndSave(User user) {
        cpfValidator.validate(user.getCpf());
        return service.saveUser(user);
    }

    public User validateAndFind(String cpf) {
        cpfValidator.validate(cpf);
        return service.getUserByCpf(cpf);
    }

}
