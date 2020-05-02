package com.nintynine.memorypond.controller;

import com.nintynine.memorypond.Model.Member;
import com.nintynine.memorypond.Repository.MemberRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainPageController {
    private final MemberRepsitory memberRepsitory;

    @GetMapping
    public String getMainPage(@AuthenticationPrincipal User user,
                              Map<String, Object> model){
        if(user == null){
            return "main";
        }

        model.put("member", user.getUsername());
        return "main";
    }
}
