package com.app.controller;

import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Package com.app.controller
 * @ClassName BaseController
 * @Author shaobin.wang
 * @Date 2019/03/13 17:57
 * @Version 1.0
 * @Description:
 **/
public class BaseController {
    /**
     * 拿到当前登陆的用户信息
     *
     * @param request
     * @return
     */
    public String getUserName(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        return ((UserDetails) securityContext.getAuthentication().getPrincipal()).getUsername();
    }
}
