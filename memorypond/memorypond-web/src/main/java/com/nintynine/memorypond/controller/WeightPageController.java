package com.nintynine.memorypond.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weight")
public class WeightPageController {
    @GetMapping
    public String getRegisterPage(){
        return "weight";
    }
}
