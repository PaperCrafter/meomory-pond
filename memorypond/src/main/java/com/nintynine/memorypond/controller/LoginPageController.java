package com.nintynine.memorypond.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginPageController {
    @GetMapping
    public String getLoginPage(@AuthenticationPrincipal User user){
        if(user != null){
            return "redirect:main";
        }
        return "login";
    }
}
