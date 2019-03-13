package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Package com.app.config
 * @ClassName SecurityWebApplicationInitializerRunJava
 * @Author shaobin.wang
 * @Date 2019/03/13 17:54
 * @Version 1.0
 * @Description:
 **/

@Order(99)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, proxyTargetClass = true)
public class SecurityWebApplicationInitializerRunJava extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 退出认证后do....
     */
    @Autowired
    private LogoutSuccessHandlerRunJava logoutSuccessHandler;

    /**
     * 认证成功后的处理
     */
    @Autowired
    private AuthenticationSuccessHandlerRunJava authenticationSuccessHandler;


    /**
     * 失败后的处理
     */
    @Autowired
    private AuthenticationFailureHandlerRunJava authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 禁用 csrf  -- 这里如果开启了 iframe 会出错 详细情况不清楚
                .csrf().disable()
                // 在这里的authorizeRequests 权限访问路径
                .authorizeRequests()
                // localhost:8080/admin
                // 例如 resources 下所有的请求 还有signup  about 的请求 都可以任意访问
                .antMatchers("/resources/**", "/signup ", "/about", "/system/logout", "/system/login.html").permitAll()
                // admin 下所有请求 需要admin 角色才能访问
                .antMatchers("/admin/**").hasRole("ADMIN")
                // db 请求 需要同时拥有 ADMIN 和 DB的角色
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                // 剩下所有的 请求 都必须经过身份认证 也就是 login
                .anyRequest().authenticated()


                .and()
                // 基于表单认证
                .formLogin()
                // 认证地址
                .loginProcessingUrl("/login")
                //  认证页面 需要配置一下
                .loginPage("/system/login.html")
                // 认证时候的 用户名参数
                .usernameParameter("username")
                // 认证时候的 密码参数
                .passwordParameter("password")
                // 认证成功后的处理
                .successHandler(authenticationSuccessHandler)
                // 失败后的处理
                .failureHandler(authenticationFailureHandler)
                // 认证失败转跳的路径
//                .failureForwardUrl("/logerror.html")
                // 认证成功后转跳的页面
//                .successForwardUrl("/index.html")

                .and()
                // 设置退出认证URL
                // 触发注销的URL(默认值为/logout)如果启用了CSRF保护(默认)，则请求也必须是POST。
                .logout().logoutUrl("/logout")
                // 退出认证成功 跳到认证页面 /login?logout
//                .logoutSuccessUrl("/login.html")
                // 这里设置退出认证后的一些操作
                .logoutSuccessHandler(logoutSuccessHandler)
                // 退出时候注销session  true
                .invalidateHttpSession(true)
                // 退出时删除的cookie信息
                .deleteCookies("user");
    }


}
