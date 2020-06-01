package com.nintynine.memorypond.controller.api;

import com.nintynine.memorypond.model.projection.CommentProjection;
import com.nintynine.memorypond.model.projection.PostBoardProjection;
import com.nintynine.memorypond.model.projection.PostPageProjection;
import com.nintynine.memorypond.model.Post;
import com.nintynine.memorypond.model.request.PostRequest;
import com.nintynine.memorypond.service.CommentService;
import com.nintynine.memorypond.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    public Page<PostPageProjection> getPosts(Pageable pageable){
        return postService.getPostList(pageable);
    }

    @GetMapping("/{postId}")
    public PostBoardProjection getPosts(@PathVariable("postId") int postId){
        return postService.getPost(postId);
    }

    @GetMapping("/{postId}/comments")
    public List<CommentProjection> getCommentsByPost(@PathVariable("postId") int postId){
        return commentService.getCommentsByPost(postId);
    }

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostRequest postRequest) throws URISyntaxException {
        Post post = postService.createPost(postRequest);
        if(post == null){
            return ResponseEntity.badRequest().body("{\"msg\":\"failed to create\"}");
        }
        URI url = new URI("/api/posts");
        post.setMember(null);
        return ResponseEntity.created(url).body(post);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity removePost(@PathVariable("postId") int postId) throws URISyntaxException {
        if(postService.deletePost(postId)){
            return ResponseEntity.ok().body("{\"deleted\":\"success\"}");
        }
        return ResponseEntity.badRequest().body("{\"deleted\":\"failed\"}");
    }
}
