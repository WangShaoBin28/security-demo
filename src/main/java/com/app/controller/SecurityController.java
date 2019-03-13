package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Package com.app.controller
 * @ClassName SecurityController
 * @Author shaobin.wang
 * @Date 2019/03/13 17:58
 * @Version 1.0
 * @Description:
 **/
@Controller
@RequestMapping("/system")
public class SecurityController extends BaseController {


    /**
     * 自定义登录页面
     *
     * @return
     */
    @GetMapping("/login.html")
    public String loginView() {
        return "/login";
    }

    /**
     * 首页
     *
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/index.html")
    public String indexView(HttpServletRequest request, Model model) {
        model.addAttribute("username", getUserName(request));
        return "/index";
    }

    /**
     * 退出前提示
     *
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/logout.html")
    public String logout(HttpServletRequest request, Model model) {
        model.addAttribute("username", getUserName(request));
        return "/logout";
    }

    /**
     * 登录失败
     *
     * @param model
     * @return
     */
    @GetMapping("/logerror")
    public String logerror(Model model) {
        return "/logerror";
    }
}
