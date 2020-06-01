package com.nintynine.memorypond.controller.api;

import com.nintynine.memorypond.model.Member;
import com.nintynine.memorypond.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
        Member memberCheck = memberService.getMember(memberForm.getUsername());
        if(memberCheck != null) {
            return ResponseEntity.badRequest().body("{\"msg\":\"duplicated\"}");
        }
        memberForm.setPassword(passwordEncoder.encode(memberForm.getPassword()));
        memberService.createMember(memberForm);
        URI uri = new URI( "/api/members");
        return ResponseEntity.created(uri).body("{\"msg\":\"created\"}");
    }
}
