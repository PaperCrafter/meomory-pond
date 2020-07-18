package com.nintynine.memorypond.controller.api;

import com.nintynine.memorypond.domain.entity.Member;
import com.nintynine.memorypond.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity memeberJoin(@RequestBody Member memberForm) throws URISyntaxException {
        memberForm.setPassword(passwordEncoder.encode(memberForm.getPassword()));
        memberService.createMember(memberForm);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
