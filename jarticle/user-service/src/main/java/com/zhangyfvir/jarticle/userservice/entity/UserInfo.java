package com.zhangyfvir.jarticle.userservice.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
    private int userID;
    private String userName;
    private String password;
    private String userIcon;
    private String role;
    private boolean enable;
    private String createdBy;
    private String createdDate;
}
