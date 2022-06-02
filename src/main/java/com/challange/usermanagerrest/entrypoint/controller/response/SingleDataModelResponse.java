package com.challange.usermanagerrest.entrypoint.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SingleDataModelResponse<T> {
    public T data;
}
