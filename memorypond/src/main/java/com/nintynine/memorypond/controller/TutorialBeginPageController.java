package com.nintynine.memorypond.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tutorial-begin")
public class TutorialBeginPageController {
    @GetMapping
    public String getTutorialBeginPage(){
        return "tutorial-begin";
    }
}