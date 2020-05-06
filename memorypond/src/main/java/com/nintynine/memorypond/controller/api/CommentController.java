package com.nintynine.memorypond.controller.api;

import com.nintynine.memorypond.Model.Projection.CommentProjection;
import com.nintynine.memorypond.Model.Request.CommentRequest;
import com.nintynine.memorypond.Repository.CommentRepository;
import com.nintynine.memorypond.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController{
    public final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentProjection createComment(@RequestBody CommentRequest commentRequest,
                                           @AuthenticationPrincipal User user){
        return commentService.createComment(commentRequest, user);
    }
}