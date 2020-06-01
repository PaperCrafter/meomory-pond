package com.nintynine.memorypond.service;

import com.nintynine.memorypond.model.enumclass.Role;
import com.nintynine.memorypond.model.Member;
import com.nintynine.memorypond.model.user.CustomUser;
import com.nintynine.memorypond.repository.MemberRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepsitory memberRepsitory;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Member member = memberRepsitory.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (username.equals("admin")) {
            grantedAuthorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            grantedAuthorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }
        return new CustomUser(member.getId() ,member.getUsername(), member.getPassword(), grantedAuthorities);
    }
}
