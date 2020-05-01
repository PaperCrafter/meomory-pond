package com.nintynine.memorypond.Repository;

import com.nintynine.memorypond.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepsitory extends JpaRepository<Member, Integer> {
    Optional<Member> findByUsername(String username);
}
