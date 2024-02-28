package com.example.nagoyameshi.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.nagoyameshi.entity.Authentication;
import com.example.nagoyameshi.entity.Member;
import com.example.nagoyameshi.repository.AuthenticationRepository;
import com.example.nagoyameshi.repository.MemberRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private MemberRepository memberRepository;
	private AuthenticationRepository authenticationRepository;
	
	public UserDetailsServiceImpl(MemberRepository memberRepository, AuthenticationRepository authenticationRepository) {
		this.memberRepository = memberRepository;
		this.authenticationRepository = authenticationRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			Authentication authentication = authenticationRepository.findByEmail(email);
			Member member = authentication.getMember();
			String memberRoleName = member.getRole().getName();
            Collection<GrantedAuthority> authorities = new ArrayList<>();         
            authorities.add(new SimpleGrantedAuthority(memberRoleName));
            return new UserDetailsImpl(authentication, member, authorities);
		} catch (Exception e) {
			throw new UsernameNotFoundException("ユーザーが見つかりませんでした。");
		}
	}
}
