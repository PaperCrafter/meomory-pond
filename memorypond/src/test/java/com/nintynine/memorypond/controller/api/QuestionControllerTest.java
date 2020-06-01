package com.nintynine.memorypond.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.nintynine.memorypond.model.enumclass.Role;
import com.nintynine.memorypond.model.Question;
import com.nintynine.memorypond.service.QuestionService;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class QuestionControllerTest {
    @InjectMocks
    private QuestionController questionController;

    @Mock
    private QuestionService questionService;

    private MockMvc mockMvc;

    private User mockUser;

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
        questionController = new QuestionController(questionService);
        mockMvc = MockMvcBuilders.standaloneSetup(questionController).setCustomArgumentResolvers(putAuthenticationPrincipal).build();
    }

    @Test
    public void getQuestionsTest() throws Exception {
        List<Question> givenReservations =
                EnhancedRandom.randomListOf(4, Question.class);

        given(questionService.getQuestionsByCategoryId(1)).willReturn(givenReservations);

        mockMvc
                .perform(get("/api/categories/1/questions").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print()).andExpect(jsonPath("$").isArray());
    }
}