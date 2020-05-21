package com.zhangyfvir.jarticle.userservice.entity;

import lombok.Data;

@Data
public class AuthData {
    private String token;
    private UserInfo userInfo;

    public AuthData(String token) {
        this.token = token;
    }
}
