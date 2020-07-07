package com.nintynine.memorypond.controller;

import com.nintynine.memorypond.domain.entity.Member;
import com.nintynine.memorypond.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterPageController {
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;

    @GetMapping
    public String getRegisterPage(){
        return "register";
    }

    @PostMapping("/new")
    public String memeberJoin(Member memberForm){
        memberForm.setPassword(passwordEncoder.encode(memberForm.getPassword()));
        memberService.createMember(memberForm);
        return "redirect:/login";
    }
}
