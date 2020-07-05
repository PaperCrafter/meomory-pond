package com.nintynine.memorypond.service;

import com.nintynine.memorypond.MemorypondApplication;
import com.nintynine.memorypond.exception.DuplicatedUserException;
import com.nintynine.memorypond.exception.UserNotFoundException;
import com.nintynine.memorypond.model.Member;
import com.nintynine.memorypond.repository.MemberRepsitory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    @NonNull
    private final MemberRepsitory memberRepsitory;

    @Transactional(readOnly = true)
    public Member getMember(String username){
        Member member = memberRepsitory.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException("No user existed named " + username));
        return member;
    }

    @Transactional
    public Member createMember(Member member){
        String currentDateTime = LocalDateTime.now().toString();
        member.setCreateAt(currentDateTime);
        member.setUpdateAt(currentDateTime);
        member.setHasVisited(false);
        Optional<Member> check = memberRepsitory.findByUsername(member.getUsername());

        if(memberRepsitory.findByUsername(member.getUsername()).get() != null)
            throw new DuplicatedUserException();

        Member createdMember = memberRepsitory.save(member);
        return createdMember;
    }

    @Transactional
    public Member setHasVisited(Member member){
        member.setHasVisited(true);
        return memberRepsitory.save(member);
    }
}
