package com.nintynine.memorypond.service;

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
        Optional<Member> memberOptional = memberRepsitory.findByUsername(username);
        if(memberOptional.isPresent()){
            return memberOptional.get();
        }
        return null;
    }

    @Transactional
    public Member createMember(Member member){
        String currentDateTime = LocalDateTime.now().toString();
        member.setCreateAt(currentDateTime);
        member.setUpdateAt(currentDateTime);
        member.setHasVisited(false);
        return memberRepsitory.save(member);
    }

    @Transactional
    public Member setHasVisited(Member member){
        member.setHasVisited(true);
        return memberRepsitory.save(member);
    }
}
