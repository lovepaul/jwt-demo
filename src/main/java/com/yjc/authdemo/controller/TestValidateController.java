package com.yjc.authdemo.controller;

import com.yjc.authdemo.TokenValidate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestValidateController {

    @PostMapping("testTokenValidate")
    @TokenValidate
    public String testTokenValidate() {
        return "testTokenValidate";
    }
}
