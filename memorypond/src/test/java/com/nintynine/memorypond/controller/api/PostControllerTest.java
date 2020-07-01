package com.nintynine.memorypond.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nintynine.memorypond.model.enumclass.Role;
import com.nintynine.memorypond.model.request.PostRequest;
import com.nintynine.memorypond.model.response.CommentResponse;
import com.nintynine.memorypond.model.response.PostResponse;
import com.nintynine.memorypond.model.user.CustomUser;
import com.nintynine.memorypond.service.CommentService;
import com.nintynine.memorypond.service.PostService;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class PostControllerTest {
    @InjectMocks
    private PostController postContoller;

    @Mock
    private PostService postService;

    @Mock
    private CommentService commentService;

    private MockMvc mockMvc;

    private CustomUser mockUser;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        //setup user
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        mockUser = new CustomUser(
                1,
                "paper",
                "paper",
                grantedAuthorities);

        HandlerMethodArgumentResolver putAuthenticationPrincipal = new HandlerMethodArgumentResolver() {
            @Override
            public boolean supportsParameter(MethodParameter parameter) {
                return parameter.getParameterType().isAssignableFrom(User.class);
            }

            @Override
            public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                          NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
                return mockUser;
            }
        };

        MockitoAnnotations.initMocks(this);
        postContoller = new PostController(postService, commentService);
        mockMvc = MockMvcBuilders.standaloneSetup(postContoller)
                .setCustomArgumentResolvers(putAuthenticationPrincipal)
                .build();
    }

    @Test
    public void getPostsTest() throws Exception {

        mockMvc.perform(get("/api/members"))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.msg").isString());
    }

    @Test
    public void getPostsByIdTest() throws Exception {
        int mockPostId =  1;
        PostResponse mockPostResponse = EnhancedRandom.random(PostResponse.class);

        given(postService.getPost(mockPostId)).willReturn(mockPostResponse);

        mockMvc.perform(get("/api/posts/" + mockPostId))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content").value(mockPostResponse.getContent()));
    }

    @Test
    public void getCommentsByPostTest() throws Exception {
        int mockPostId =  1;
        List<CommentResponse> commentResponses = EnhancedRandom.randomListOf(10, CommentResponse.class);

        given(commentService.getCommentsByPost(mockPostId)).willReturn(commentResponses);

        mockMvc.perform(get("/api/posts/" + mockPostId + "/comments"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isArray());
    }

//    @Test
//    public void createPostTest() throws Exception {
//        PostRequest mockPostRequest = EnhancedRandom.random(PostRequest.class);
//        PostResponse mockPostResponse = EnhancedRandom.random(PostResponse.class);
//
//        given(postService.createPost(any(PostRequest.class), eq(mockUser))).willReturn(mockPostResponse);
//
//        mockMvc.perform(post("/api/posts")
//                .content(objectMapper.writeValueAsString(mockPostRequest))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andDo(print())
//                .andExpect(jsonPath("$.msg").isString());
//    }

    @Test
    public void removePostTest() throws Exception {
        int mockPostId =  1;

        mockMvc.perform(delete("/api/posts/" + mockPostId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}

//
//    @GetMapping
//    public Page<PostResponse> getPosts(Pageable pageable){
//        return postService.getPostList(pageable);
//    }

//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity createPost(@RequestBody PostRequest postRequest,
//                                     @AuthenticationPrincipal CustomUser user) throws URISyntaxException {
//        PostResponse postResponse = postService.createPost(postRequest, user);
//        if(postResponse == null){
//            return ResponseEntity.badRequest().body("{\"msg\":\"failed to create\"}");
//        }
//        URI url = new URI("/api/posts");
//        return ResponseEntity.created(url).body(postResponse);
//    }
