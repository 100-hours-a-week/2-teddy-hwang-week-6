package com.example.community.security;

import com.example.community.model.Member;
import com.example.community.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Long id = Long.parseLong(userId);
        Member member = memberRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다"));
        return new UserDetailsImpl(member);
    }
}
