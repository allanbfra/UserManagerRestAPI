package com.challange.usermanagerrest.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String cpf;
    private Timestamp birthday;

    public User(String name, String cpf, Timestamp birthday) {
        this.name = name;
        this.cpf = cpf;
        this.birthday = birthday;
    }
}
