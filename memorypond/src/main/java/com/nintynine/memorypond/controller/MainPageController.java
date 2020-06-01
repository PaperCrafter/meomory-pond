package com.nintynine.memorypond.controller;

import com.nintynine.memorypond.model.Member;
import com.nintynine.memorypond.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainPageController {
    private final MemberService memberService;

    @GetMapping
    public String getMainPage(@AuthenticationPrincipal User user,
                              Map<String, Object> model){
        if(user == null){
            return "main";
        }
        model.put("member", user.getUsername());
        Member member = memberService.getMember(user.getUsername());
        if(member.getHasVisited() == false){
            memberService.setHasVisited(member);
            return "redirect:/tutorial-begin";
        }
        return "main";
    }
}
