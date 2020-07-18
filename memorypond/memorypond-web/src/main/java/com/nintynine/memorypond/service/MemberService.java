package com.nintynine.memorypond.service;

import com.nintynine.memorypond.domain.entity.Member;
import com.nintynine.memorypond.domain.exception.DuplicatedUserException;
import com.nintynine.memorypond.domain.exception.UserNotFoundException;
import com.nintynine.memorypond.domain.value.Role;
import com.nintynine.memorypond.repository.MemberRepsitory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    @NonNull
    private final MemberRepsitory memberRepsitory;

    @Transactional(readOnly = true)
    public Member getMember(String username){
        Member member = memberRepsitory.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException(username));
        return member;
    }

    @Transactional
    public Member createMember(Member member){
        member.setHasVisited(false);
        member.setRole(Role.MEMBER);
        if(memberRepsitory.findByUsername(member.getUsername()).isPresent())
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
