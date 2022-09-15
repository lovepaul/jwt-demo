package com.yjc.authdemo.controller;

import com.yjc.authdemo.User;
import com.yjc.authdemo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 */
@RestController
@RequestMapping("user")
public class UserLoginController {

    @Value("${Login.username}")
    private String realUsername;



    @Value("${Login.password}")
    private String realPassword;

    @GetMapping("login")
    public String login(String username, String password) {
        if (username.equals(realUsername) && password.equals(realPassword)) {
            User u = new User();
            u.setId(44444);
            u.setPassword(password);
            u.setUsername(username);
            u.setPhone("13372877826");
            return JwtUtil.getToken(u);
        }
        return "登录失败！账号或者密码不对！";
    }

    @GetMapping("test")
    public String test() {
        return "访问test - API";
    }

}
