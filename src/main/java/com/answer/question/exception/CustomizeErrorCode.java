package com.answer.question.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND(2001,"你找的问题不在了，换个再试试！"),
    TARGET_PARAM_NOT_FOUND(2002,"未选择任何回复！"),
    NO_LOGIN(2003,"当前操作需要登录，请先登录!"),
    SYS_ERROR(2004,"服务器冒烟了，请稍后再试!"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在！"),
    COMMENT_NOT_FOUND(2006,"回复的评论不在了，换个再试试"),
    CONTENT_IS_EMPTY(2007,"输入内容不能为空！"),
    READ_NOTIFICATION_FAIL(2008,"读错了！"),
    NOTIFICATION_NOT_FOUND(2009,"回复不见了！");

    private Integer code;

    @Override
    public Integer getCode() {
        return code;
    }

    private String message;

    CustomizeErrorCode(Integer code,String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
