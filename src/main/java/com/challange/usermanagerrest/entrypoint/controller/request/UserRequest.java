package com.challange.usermanagerrest.entrypoint.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class UserRequest {
    private String name;
    private String cpf;
    private Timestamp birthday;
}
