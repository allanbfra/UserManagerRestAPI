package com.challange.usermanagerrest.entrypoint.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DataModelResponse<T> {
    public List<T> data;
}
