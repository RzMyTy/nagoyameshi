package com.example.nagoyameshi.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyameshi.entity.Authentication;
import com.example.nagoyameshi.entity.Member;
import com.example.nagoyameshi.entity.Role;
import com.example.nagoyameshi.form.MemberEditForm;
import com.example.nagoyameshi.form.SignupForm;
import com.example.nagoyameshi.repository.AuthenticationRepository;
import com.example.nagoyameshi.repository.MemberRepository;
import com.example.nagoyameshi.repository.RoleRepository;

@Service
public class MemberService {
	private final MemberRepository memberRepository;
	private final RoleRepository roleRepository;
	private final AuthenticationRepository authenticationRepository;
	private final PasswordEncoder passwordEncoder;
	
	public MemberService(MemberRepository memberRepository, AuthenticationRepository authenticationRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.memberRepository = memberRepository;
		this.roleRepository = roleRepository;
		this.authenticationRepository = authenticationRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Transactional
	public Authentication create(SignupForm signupForm) {
		
		// 会員情報
		Member member = new Member();
		Role role = roleRepository.findByName("ROLE_FREE_MEMBER");
		
		member.setName(signupForm.getName());
		member.setFurigana(signupForm.getFurigana());
		member.setPostalCode(signupForm.getPostalCode());
		member.setAddress(signupForm.getAddress());
		member.setPhoneNumber(signupForm.getPhoneNumber());
		member.setRole(role);
		
		Member savedMember =  memberRepository.save(member);
		
		// 認証情報
		Authentication authentication = new Authentication();
		authentication.setEmail(signupForm.getEmail());
		authentication.setPassword(passwordEncoder.encode(signupForm.getPassword()));
		authentication.setMember(savedMember); 
		
	    return  authenticationRepository.save(authentication);
		
	}
	
	@Transactional
	public void update(MemberEditForm memberEditForm) {
		Member member = memberRepository.getReferenceById(memberEditForm.getId());
		Authentication authentication = authenticationRepository.getReferenceById(memberEditForm.getId());
		
		member.setName(memberEditForm.getName());
		member.setFurigana(memberEditForm.getFurigana());
		member.setPostalCode(memberEditForm.getPostalCode());
		member.setAddress(memberEditForm.getAddress());
		member.setPhoneNumber(memberEditForm.getPhoneNumber());
		
		Member savedMember =  memberRepository.save(member);
		
		authentication.setEmail(memberEditForm.getEmail());
		authentication.setMember(savedMember);
		
		authenticationRepository.save(authentication);
	}
	
	@Transactional
	public void subscriptionCreate(Member member) {
		Role role = roleRepository.findByName("ROLE_PAID_MEMBER");
		
		member.setRole(role);
		
		memberRepository.save(member);
	}
	
	@Transactional
	public void subscriptionCancel(Member member) {
		Role role = roleRepository.findByName("ROLE_FREE_MEMBER");
		
		member.setRole(role);
		
		memberRepository.save(member);
	}

	// メールアドレスが登録済みかどうかをチェックする
	public boolean isEmailRegistered(String email) {
		Authentication authentication = authenticationRepository.findByEmail(email);
		return authentication != null;
	}
	
	 // パスワードとパスワード（確認用）の入力値が一致するかどうかをチェックする
	public boolean isSamePassword(String password, String passwordConfirmation) {
		return password.equals(passwordConfirmation);
	}
	
	 // メールアドレスが変更されたかどうかをチェックする
    public boolean isEmailChanged(MemberEditForm userEditForm) {
        Authentication currentUser = authenticationRepository.getReferenceById(userEditForm.getId());
        return !userEditForm.getEmail().equals(currentUser.getEmail());      
    }  
	
}
