package com.nintynine.memorypond.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wellcome")
public class WellcomePageController {
    @GetMapping
    public String getRegisterPage(){
        return "wellcome";
    }
}
