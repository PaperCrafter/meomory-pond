package com.nintynine.memorypond.controller;

import com.nintynine.memorypond.model.projection.PostBoardProjection;
import com.nintynine.memorypond.model.response.PostResponse;
import com.nintynine.memorypond.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post/{postId}")
public class PostPageController {
    private final PostService postService;

    @GetMapping
    public ModelAndView getPostPage(@PathVariable int postId,
                                    @AuthenticationPrincipal User user){
        PostResponse post = postService.getPost(postId);

        ModelAndView mav = new ModelAndView("post");
        if(user == null) return mav;
        if(user.getUsername().equals(post.getUsername())){
            mav.addObject("canRemove", true);
        }
        return mav;
    }
}
