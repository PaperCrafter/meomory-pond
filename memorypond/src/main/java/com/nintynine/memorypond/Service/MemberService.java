package com.nintynine.memorypond.Service;

import com.nintynine.memorypond.Model.Member;
import com.nintynine.memorypond.Repository.MemberRepsitory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    @NonNull
    private final MemberRepsitory userRepsitory;

    public void getMember(){

    }

    public void createMember(Member member){

    }
}
