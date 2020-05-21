package com.nintynine.memorypond.controller;

import com.nintynine.memorypond.Service.MemberService;
import com.nintynine.memorypond.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post-created")
public class PostCreatedController {
    private final PostService postService;

    @GetMapping
    public String postCreated(@AuthenticationPrincipal User user,
                              Map<String, Object> model){
        int postCount = postService.countPostsByUsername(user.getUsername());
        if(postCount == 1){
            return "redirect:/tutorial-end";
        }
        return "redirect:/main";
    }
}
