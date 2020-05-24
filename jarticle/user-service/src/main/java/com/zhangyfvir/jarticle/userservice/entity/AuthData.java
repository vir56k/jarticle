package com.zhangyfvir.jarticle.userservice.entity;

import lombok.Data;

@Data
public class AuthData {
    private String token;
    private User userInfo;

    public AuthData(String token) {
        this.token = token;
    }
}
