package com.nintynine.memorypond.controller;

import com.nintynine.memorypond.Model.Projection.PostBoardProjection;
import com.nintynine.memorypond.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post/{postId}")
public class PostPageController {
    private final PostService postService;

    @GetMapping
    public ModelAndView getPostPage(@PathVariable int postId,
                                    @AuthenticationPrincipal User user){
        PostBoardProjection post = postService.getPost(postId);
        //Map<String, Object> model = new HashMap<String, Object>();

        ModelAndView mav = new ModelAndView("post");
        if(user == null) return mav;
        if(user.getUsername().equals(post.getUsername())){
            mav.addObject("canRemove", true);
        }
        return mav;
    }
}
