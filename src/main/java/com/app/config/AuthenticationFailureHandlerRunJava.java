package com.app.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Package com.app.config
 * @ClassName AuthenticationFailureHandlerRunJava
 * @Author shaobin.wang
 * @Date 2019/03/13 17:57
 * @Version 1.0
 * @Description:
 **/
@Slf4j
@Component
public class AuthenticationFailureHandlerRunJava implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

    }
}
