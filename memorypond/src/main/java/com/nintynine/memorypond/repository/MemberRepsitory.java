package com.nintynine.memorypond.repository;

import com.nintynine.memorypond.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepsitory extends JpaRepository<Member, Integer> {
    Optional<Member> findByUsername(String username);

    Member save(Member username);
}
