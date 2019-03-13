package com.app.config;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @Package com.app.config
 * @ClassName UserDetailsServiceRunJava
 * @Author shaobin.wang
 * @Date 2019/03/13 17:52
 * @Version 1.0
 * @Description:
 **/
@Slf4j
@Component
public class UserDetailsServiceRunJava implements UserDetailsService {
    private final String ROLE = "ROLE_";

    /**
     * 密码加密
     */
    @Autowired
    private
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 查询出用户角色信息
        String password = username;

        // 加密后的密码
        String encodePassword = passwordEncoder.encode(password);

        // 账户是否启用 激活的概念 如果用户已启用，则设置为 true
        boolean enable = true;
        // 账户是否过期 如果帐户尚未过期，则设置为 true
        boolean accountNonExpired = true;
        // 密码是否过期 如果凭据未过期，则设置为 true
        boolean credentialsNonExpired = true;
        // 账户为被锁定 如果帐户未锁定，则设置为 true
        boolean accountNonLocked = true;
        // 权限集合 如果他们提供了正确的用户名和密码并且启用了用户，则应授予调用者权限。不是空的。
        ArrayList<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        // 默认添加一个角色 为admin
        grantedAuthorityList.add(new SimpleGrantedAuthority(ROLE + "ADMIN"));
        // 添加一个权限为 queryUser
        grantedAuthorityList.add(new SimpleGrantedAuthority("queryUser"));
        // 简单版本 默认为true  避免这些 概念性的设置
//        new User(username, encodePassword, grantedAuthorityList);
        User user = new User(username, encodePassword, enable, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorityList);
        log.info("登录认证:" + JSONUtil.toJsonPrettyStr(user));
        return user;
    }
}
