package com.challange.usermanagerrest.entrypoint.controller.response;

import lombok.AllArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
public class UserDataModelResponse {
    private String name;
    private String cpf;
    private Timestamp birthday;
}
