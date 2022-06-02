package com.challange.usermanagerrest.entrypoint.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class UserDataModelResponse {
    private String name;
    private String cpf;
    private Timestamp birthday;
}
