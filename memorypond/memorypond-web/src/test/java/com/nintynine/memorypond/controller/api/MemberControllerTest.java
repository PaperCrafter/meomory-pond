package com.nintynine.memorypond.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nintynine.memorypond.domain.entity.Member;
import com.nintynine.memorypond.service.MemberService;
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
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class MemberControllerTest {
    @InjectMocks
    private MemberController memberController;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private MemberService memberService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        passwordEncoder = new BCryptPasswordEncoder();
        memberController = new MemberController(passwordEncoder, memberService);
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    public void memberJoinTest() throws Exception {
        Member mockMember = EnhancedRandom.random(Member.class);

        given(memberService.getMember(mockMember.getUsername())).willReturn(null);
        given(memberService.createMember(any(Member.class))).willReturn(mockMember);

        mockMvc.perform(post("/api/members")
                .content(objectMapper.writeValueAsString(mockMember))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.msg").isString());
    }
}
