package com.example.nagoyameshi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.entity.Authentication;
import com.example.nagoyameshi.entity.Member;
import com.example.nagoyameshi.form.MemberEditForm;
import com.example.nagoyameshi.repository.AuthenticationRepository;
import com.example.nagoyameshi.repository.MemberRepository;
import com.example.nagoyameshi.service.MemberService;

@Controller
@RequestMapping("/admin/members")
public class AdminMemberController {
	private final MemberRepository memberRepository;
	private final AuthenticationRepository authenticationRepository;
	private final MemberService memberService;
	
	public AdminMemberController(MemberRepository memberRepository, AuthenticationRepository authenticationRepository, MemberService memberService) {
		this.memberRepository = memberRepository;
		this.authenticationRepository = authenticationRepository;
		this.memberService = memberService;
	}
	
	@GetMapping
	public String index(@RequestParam(name = "keyword", required = false) String keyword, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, Model model) {
		Page<Member> memberPage;
		
		if(keyword != null && !keyword.isEmpty()) {
			memberPage = memberRepository.findByNameLikeOrFuriganaLike("%" + keyword + "%", "%" + keyword + "%", pageable);
		} else {
			memberPage = memberRepository.findAll(pageable);
		}
		
		model.addAttribute("memberPage", memberPage);
		model.addAttribute("keyword", keyword);
		
		return "admin/members/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable(name = "id") Integer id, Model model) {
		Member member = memberRepository.getReferenceById(id);
		Authentication authentication = authenticationRepository.getReferenceById(id);
		
		model.addAttribute("member", member);
		model.addAttribute("authentication", authentication);
		
		return "admin/members/show";
	}
	
	 @PostMapping("/update")
     public String update(@ModelAttribute @Validated MemberEditForm memberEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
         // メールアドレスが変更されており、かつ登録済みであれば、BindingResultオブジェクトにエラー内容を追加する
         if (memberService.isEmailChanged(memberEditForm) && memberService.isEmailRegistered(memberEditForm.getEmail())) {
             FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "すでに登録済みのメールアドレスです。");
             bindingResult.addError(fieldError);                       
         }
         
         if (bindingResult.hasErrors()) {
             return "member/edit";
         }
         
         memberService.update(memberEditForm);
         redirectAttributes.addFlashAttribute("successMessage", "会員情報を編集しました。");
         
         return "redirect:/member";
     }    
}
