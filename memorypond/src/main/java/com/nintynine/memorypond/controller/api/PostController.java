package com.nintynine.memorypond.controller.api;

import com.nintynine.memorypond.model.request.PostRequest;
import com.nintynine.memorypond.model.response.CommentResponse;
import com.nintynine.memorypond.model.response.PostResponse;
import com.nintynine.memorypond.model.user.CustomUser;
import com.nintynine.memorypond.service.CommentService;
import com.nintynine.memorypond.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    @GetMapping
    public Page<PostResponse> getPosts(Pageable pageable){
        return postService.getPostList(pageable);
    }

    @GetMapping("/{postId}")
    public PostResponse getPost(@PathVariable("postId") int postId){
        return postService.getPost(postId);
    }

    @GetMapping("/{postId}/comments")
    public List<CommentResponse> getCommentsByPost(@PathVariable("postId") int postId){
        return commentService.getCommentsByPost(postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createPost(@RequestBody PostRequest postRequest,
                                     @AuthenticationPrincipal CustomUser user) throws URISyntaxException {
        PostResponse postResponse = postService.createPost(postRequest, user);
        if(postResponse == null){
            return ResponseEntity.badRequest().body("{\"msg\":\"failed to create\"}");
        }
        URI url = new URI("/api/posts");
        return ResponseEntity.created(url).body(postResponse);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity removePost(@PathVariable("postId") int postId){
        postService.deletePost(postId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
