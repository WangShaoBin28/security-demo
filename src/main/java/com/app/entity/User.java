package com.app.entity;

import lombok.Data;

/**
 * @Package com.app.entity
 * @ClassName User
 * @Author shaobin.wang
 * @Date 2019/03/13 18:05
 * @Version 1.0
 * @Description:
 **/
@Data
public class User {
    private Integer id;
    private String username;
    private String password;

    public User() {
    }

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
