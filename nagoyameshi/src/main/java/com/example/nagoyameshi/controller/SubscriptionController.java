package com.example.nagoyameshi.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.entity.Member;
import com.example.nagoyameshi.repository.MemberRepository;
import com.example.nagoyameshi.security.UserDetailsImpl;
import com.example.nagoyameshi.service.MemberService;
import com.example.nagoyameshi.service.StripeService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/subscription")
public class SubscriptionController {
	private StripeService stripeService;
	private MemberRepository memberRepository;
	private MemberService memberService;
	
	public SubscriptionController(StripeService stripeService, MemberRepository memberRepository, MemberService memberService) {
		this.stripeService = stripeService;
		this.memberRepository = memberRepository;
		this.memberService = memberService;
	}
	
	@GetMapping
	public String index(Model model, HttpServletRequest httpServletRequest) {
		String sessionId = stripeService.careateStripeSession(httpServletRequest);
		
		model.addAttribute("sessionId", sessionId);
		
		return "subscription/index";
	}
	
	@GetMapping("/cancel")
	public String cancel() {
		return "subscription/cancel";
	}
	
	@PostMapping("/create")
	public String create(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, RedirectAttributes redirectAttributes) {
		Member member = memberRepository.getReferenceById(userDetailsImpl.getMember().getId());
		memberService.subscriptionCreate(member);
		
		redirectAttributes.addFlashAttribute("successMessage", "有料会員登録が完了しました。");
		
		return "redirect:/";
	}
	
	@PostMapping("/delete")
	public String delete(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, RedirectAttributes redirectAttributes) {
		Member member = memberRepository.getReferenceById(userDetailsImpl.getMember().getId());
		memberService.subscriptionCancel(member);
		
		redirectAttributes.addFlashAttribute("successMessage", "サブスクリプションを解約しました。");
	
		return "redirect:/";
	}
	
}
