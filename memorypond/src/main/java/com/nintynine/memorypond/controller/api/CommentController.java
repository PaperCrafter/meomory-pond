package com.nintynine.memorypond.controller.api;

import com.nintynine.memorypond.model.projection.CommentProjection;
import com.nintynine.memorypond.model.request.CommentRequest;
import com.nintynine.memorypond.model.response.CommentResponse;
import com.nintynine.memorypond.model.user.CustomUser;
import com.nintynine.memorypond.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController{
    public final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse createComment(@RequestBody @Valid CommentRequest commentRequest,
                                         @AuthenticationPrincipal CustomUser user){
        return commentService.createComment(commentRequest, user);
    }
}