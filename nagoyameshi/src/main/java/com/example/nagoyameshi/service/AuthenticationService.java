//package com.example.nagoyameshi.service;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.example.nagoyameshi.entity.Authentication;
//import com.example.nagoyameshi.form.SignupForm;
//import com.example.nagoyameshi.repository.AuthenticationRepository;
//import com.example.nagoyameshi.repository.MemberRepository;
//
//@Service
//public class AuthenticationService {
//	private final MemberRepository memberRepository;
//	private final AuthenticationRepository authenticationRepository;
//	private final PasswordEncoder passwordEncoder;
//	
//	public AuthenticationService(MemberRepository memberRepository, AuthenticationRepository authenticationRepository, PasswordEncoder passwordEncoder) {
//		this.memberRepository = memberRepository;
//		this.authenticationRepository = authenticationRepository;
//		this.passwordEncoder = passwordEncoder;
//	}
//	
//	@Transactional
//	public Authentication create(SignupForm signupForm) {
//		// 認証情報
//		Authentication authentication = new Authentication();
//		authentication.setEmail(signupForm.getEmail());
//		authentication.setPassword(signupForm.getPassword());
//		
//		return  authenticationRepository.save(authentication);
//	}
//	
//	// メールアドレスが登録済みかどうかをチェックする
//	public boolean isEmailRegistered(String email) {
//		Authentication authentication = authenticationRepository.findByEmail(email);
//		return authentication != null;
//	}
//	
//	 // パスワードとパスワード（確認用）の入力値が一致するかどうかをチェックする
//	public boolean isSamePassword(String password, String passwordConfirmation) {
//		return password.equals(passwordConfirmation);
//	}
//}
