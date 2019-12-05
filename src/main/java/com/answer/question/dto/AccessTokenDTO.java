package com.answer.question.dto;

import lombok.Data;
import netscape.security.PrivilegeTable;

@Data
public class AccessTokenDTO {

    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_url;
    private String state;


    @Override
    public String toString() {
        return "AccessTokenDTO{" +
                "client_id='" + client_id + '\'' +
                ", client_secret='" + client_secret + '\'' +
                ", code='" + code + '\'' +
                ", redirect_url='" + redirect_url + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
