package com.nintynine.memorypond.controller.api;

import com.nintynine.memorypond.Model.Post;
import com.nintynine.memorypond.Model.Request.PostRequest;
import com.nintynine.memorypond.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public Page<Post> getPosts(Pageable pageable){
        return postService.getPostList(pageable);
    }

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostRequest postRequest) throws URISyntaxException {
        Post post = postService.createPost(postRequest);
        if(post == null){
            return ResponseEntity.badRequest().body("{\"msg\":\"failed to create\"}");
        }
        URI url = new URI("/api/posts");
        return ResponseEntity.created(url).body("{\"msg\":\"created\"}");
    }
}
