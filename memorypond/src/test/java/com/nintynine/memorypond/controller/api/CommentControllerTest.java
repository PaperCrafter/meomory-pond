package com.nintynine.memorypond.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nintynine.memorypond.Model.Comment;
import com.nintynine.memorypond.Model.Enum.Role;
import com.nintynine.memorypond.Model.Projection.CommentProjection;
import com.nintynine.memorypond.Model.Request.CommentRequest;
import com.nintynine.memorypond.Service.CommentService;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class CommentControllerTest {
    @InjectMocks
    private CommentController commentController;

    @Mock
    private CommentService commentService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private User mockUser;

    @Mock
    private CommentProjection commentProjection;

    @BeforeEach
    public void setup() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        mockUser = new User(
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
        commentController = new CommentController(commentService);
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).setCustomArgumentResolvers(putAuthenticationPrincipal).build();
    }

    @Test
    public void createCommentTest() throws Exception {

        CommentRequest mockCommentRequest = EnhancedRandom.random(CommentRequest.class);

        given(commentService.createComment(any(CommentRequest.class), eq(mockUser))).willReturn(null);

        mockMvc.perform(post("/api/comments")
                .content(objectMapper.writeValueAsString(mockCommentRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
//                .andExpect(jsonPath("$.content").isString());
    }
}