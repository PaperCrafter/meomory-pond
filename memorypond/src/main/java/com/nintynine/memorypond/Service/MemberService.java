package com.nintynine.memorypond.Service;

import com.nintynine.memorypond.Model.Member;
import com.nintynine.memorypond.Repository.MemberRepsitory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
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
        return memberRepsitory.save(member);
    }
}
