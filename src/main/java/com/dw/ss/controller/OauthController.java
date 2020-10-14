package com.dw.ss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program:
 * @description: Oauth2.0 测试
 * @author: dingwen
 * @create: 2020/10/14 9:31
 **/
@RestController
public class OauthController {
    @GetMapping("/oauth/o")
    public Object oauth() {
        return "/oauth/o";
    }

    @GetMapping("/login/l")
    public Object login() {
        return "/login/l";
    }

    @GetMapping("/logout")
    public Object logout() {
        return "/logout";
    }
    @GetMapping("/decision")
    public Object decision(){
        return "decision";
    }
    @GetMapping("/govern/g")
    public Object govern(){
        return "govern/g";
    }
    @GetMapping("admin")
    public Object admin(){
        return "admin";
    }
    @GetMapping("/test")
    public Object test(){
        return "test";
    }
}
