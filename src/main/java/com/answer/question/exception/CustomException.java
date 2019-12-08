package com.answer.question.exception;

public class CustomException extends RuntimeException {

    private Integer code;
    private String message;


    public CustomException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
