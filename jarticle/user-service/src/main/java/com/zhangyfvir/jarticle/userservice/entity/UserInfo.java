package com.zhangyfvir.jarticle.userservice.entity;

import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
public class UserInfo implements Serializable {
    private int id;
    private String name;
    private String role;
}
