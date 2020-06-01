package com.nintynine.memorypond.service;

import com.nintynine.memorypond.model.Member;
import com.nintynine.memorypond.repository.MemberRepsitory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    @NonNull
    private final MemberRepsitory memberRepsitory;

    public Member getMember(String username){
        Optional<Member> memberOptional = memberRepsitory.findByUsername(username);
        if(memberOptional.isPresent()){
            return memberOptional.get();
        }
        return null;
    }

    public Member createMember(Member member){
        String currentDateTime = LocalDateTime.now().toString();
        member.setCreateAt(currentDateTime);
        member.setUpdateAt(currentDateTime);
        member.setHasVisited(false);
        return memberRepsitory.save(member);
    }

    public Member setHasVisited(Member member){
        member.setHasVisited(true);
        return memberRepsitory.save(member);
    }
}
