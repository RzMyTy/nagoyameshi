package com.example.nagoyameshi.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.nagoyameshi.entity.Authentication;
import com.example.nagoyameshi.entity.Member;

public class UserDetailsImpl implements UserDetails {
	private final Authentication authentication;
	private final Collection<GrantedAuthority> authorities;
	private final Member member;
	
	public UserDetailsImpl(Authentication authentication, Member member, Collection<GrantedAuthority> authorities) {
		this.authentication = authentication;
		this.authorities = authorities;
		this.member = member;
	}
	
	public Member getMember() {
		return member;
	}
	
	// ハッシュ化済みのパスワードを返す
	@Override
	public String getPassword() {
		return authentication.getPassword();
	}
	

	// ログイン時に利用するユーザー名（メールアドレス）を返す
	@Override
	public String getUsername() {
		return authentication.getEmail();
	}
	
	// ロールのコレクションを返す
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
	
	 // アカウントが期限切れでなければtrueを返す
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
 // ユーザーがロックされていなければtrueを返す
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    // ユーザーのパスワードが期限切れでなければtrueを返す
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
 // ユーザーが有効であればtrueを返す
    @Override
    public boolean isEnabled() {
        return true;
    }
}
