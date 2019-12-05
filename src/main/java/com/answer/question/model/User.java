package com.answer.question.model;

import lombok.Data;

import java.lang.reflect.ParameterizedType;

@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
