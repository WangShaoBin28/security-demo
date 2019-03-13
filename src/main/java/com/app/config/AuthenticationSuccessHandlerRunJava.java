package com.app.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Package com.app.config
 * @ClassName AuthenticationSuccessHandlerRunJava
 * @Author shaobin.wang
 * @Date 2019/03/13 17:56
 * @Version 1.0
 * @Description:
 **/
@Component
public class AuthenticationSuccessHandlerRunJava implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        authentication.getDetails();
        if(authentication instanceof UsernamePasswordAuthenticationToken){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) authentication;
            Object principal = usernamePasswordAuthenticationToken.getPrincipal();
            if(principal instanceof User){
                User user = (User) principal;
                // 设置到cookie 中 该用户的 登录账号
                response.addCookie(new Cookie("user",user.getUsername()));
                response.sendRedirect("/system/index.html");
            }
        }

    }

}