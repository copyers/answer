package com.answer.question.enums;

import com.answer.question.model.Notification;

public enum NotificationTypeEnum {
    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(2,"回复了评论");

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    private int type;
    private String name;

    NotificationTypeEnum(int type,String name){
        this.type = type;
        this.name = name;
    }
    public static String nameOftype(int type){
        for (NotificationTypeEnum notificationTypeEnum:NotificationTypeEnum.values()){
          if (notificationTypeEnum.getType() == type){
              return notificationTypeEnum.getName();
          }
        }
        return "";
    }

}
