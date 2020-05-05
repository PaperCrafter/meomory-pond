package com.nintynine.memorypond.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post/{postId}")
public class PostPageController {
    @GetMapping
    public String getLoginPage(){
        return "post";
    }
}
