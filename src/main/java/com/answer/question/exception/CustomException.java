package com.answer.question.exception;

public class CustomException extends RuntimeException {
    private String message;

    public CustomException(ICustomizeErrorCode errorCode) {
        message = errorCode.getMessage();
    }

    public CustomException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
