package com.nintynine.memorypond.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.nintynine.memorypond.Model.Question;
import com.nintynine.memorypond.Service.QuestionService;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

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

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        questionController = new QuestionController(questionService);
        mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();
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