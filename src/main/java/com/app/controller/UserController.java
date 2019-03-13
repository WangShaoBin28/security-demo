package com.app.controller;

import com.app.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package com.app
 * @ClassName UserController
 * @Author shaobin.wang
 * @Date 2019/03/13 18:04
 * @Version 1.0
 * @Description:
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getUser")
    @PreAuthorize("hasAuthority('getUser')")
    public User getUser(Integer id) {
        return new User(id, "小石头", "不告诉你!");
    }

    @GetMapping("/addUser")
    @PreAuthorize("hasRole('ADMIN')")
    public User addUser(Integer id) {
        return new User(id, "小石头", "不告诉你!");
    }

    @GetMapping("/queryUser")
    @PreAuthorize("hasAuthority('queryUser')")
    public User queryUser(Integer id) {
        return new User(id, "小石头", "不告诉你!");
    }

}
