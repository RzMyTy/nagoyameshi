package com.example.nagoyameshi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.entity.Member;
import com.example.nagoyameshi.entity.Restaurant;
import com.example.nagoyameshi.entity.Review;
import com.example.nagoyameshi.form.ReviewEditForm;
import com.example.nagoyameshi.form.ReviewRegisterForm;
import com.example.nagoyameshi.repository.RestaurantRepository;
import com.example.nagoyameshi.repository.ReviewRepository;
import com.example.nagoyameshi.security.UserDetailsImpl;
import com.example.nagoyameshi.service.ReviewService;

@Controller
public class ReviewController {
	private final RestaurantRepository restaurantRepository;
	private final ReviewRepository reviewRepository;
	private final ReviewService reviewService;
	
	public ReviewController(RestaurantRepository restaurantRepository, ReviewRepository reviewRepository, ReviewService reviewService) {
		this.restaurantRepository = restaurantRepository;
		this.reviewRepository = reviewRepository;
		this.reviewService = reviewService;
	}
	
	@GetMapping("/reviews")
	public String review(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, Model model) {
		Restaurant restaurant = new Restaurant();
		Member member = userDetailsImpl.getMember();
		Page<Review> reviewPage = reviewRepository.findByMemberOrderByCreatedAtDesc(member, pageable);
		
		model.addAttribute("restaurant", restaurant);
		model.addAttribute("reviewRegisterForm", new ReviewRegisterForm());
		model.addAttribute("reviewPage", reviewPage);
		
		return "reviews/index";
	}
	
	@GetMapping("/restaurants/{id}/review")
	public String review(@PathVariable(name = "id") Integer id, Model model) {
		Restaurant restaurant = restaurantRepository.getReferenceById(id);
		
		model.addAttribute("restaurant", restaurant);
		model.addAttribute("reviewRegisterForm", new ReviewRegisterForm());
		
		return "restaurants/review";
	}
	
	@PostMapping("/restaurants/{restaurantId}/review/create")
	public String create(@PathVariable(name = "restaurantId") Integer restaurantId, 
			             @ModelAttribute ReviewRegisterForm reviewRegisterForm, BindingResult bindingResult,  
			             @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, RedirectAttributes redirectAttributes, 
			             Model model) {
		Restaurant restaurant = restaurantRepository.getReferenceById(restaurantId);
		Member member = userDetailsImpl.getMember();
				
		if (bindingResult.hasErrors()) {            
			model.addAttribute("restaurant", restaurant);            
            model.addAttribute("errorMessage", "予約内容に不備があります。"); 
            return "restaurants/show";
        }
		
		reviewService.create(restaurant, member, reviewRegisterForm);
		redirectAttributes.addFlashAttribute("successMessage", "レビューを投稿しました。");
		
		return "redirect:/restaurants/{restaurantId}";
	}
	
	@GetMapping("/restaurants/{restaurantId}/review/{reviewId}/edit")
	public String edit(@PathVariable(name = "restaurantId") Integer restaurantId, 
			           @PathVariable(name = "reviewId") Integer reviewId, 
			           Model model) {
		Restaurant restaurant = restaurantRepository.getReferenceById(restaurantId);
		Review review = reviewRepository.getReferenceById(reviewId);
		
		ReviewEditForm reviewEditForm = new ReviewEditForm(review.getId(), review.getScore(), review.getContent());
		
		model.addAttribute("restaurant", restaurant);
		model.addAttribute("review",review);
		model.addAttribute("reviewEditForm", reviewEditForm);
		
		return "reviews/edit";
	} 
	
	@PostMapping("/restaurants/{restaurantId}/review/{reviewId}/update")
	public String update(@PathVariable(name = "restaurantId") Integer restaurantId, 
			             @PathVariable(name = "reviewId") Integer reviewId, 
			             @ModelAttribute @Validated ReviewEditForm reviewEditForm, 
			             BindingResult bindingResult, RedirectAttributes redirectAttributes, 
			             Model model) {
		
		Restaurant restaurant = restaurantRepository.getReferenceById(restaurantId);
		Review review = reviewRepository.getReferenceById(reviewId);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("restaurant", restaurant);
			model.addAttribute("review",review);
			
			return "reviews/edit";
		}
		
		reviewService.update(reviewEditForm);
		redirectAttributes.addFlashAttribute("successMessage", "レビューを編集しました。");
		
		return "redirect:/restaurants/{restaurantId}";
	}
	
	@PostMapping("/restaurants/{restaurantId}/review/{reviewId}/delete")
	public String delete(@PathVariable(name = "reviewId") Integer reviewId, RedirectAttributes redirectAttributes) {
		reviewRepository.deleteById(reviewId);
		
		redirectAttributes.addFlashAttribute("successMessage", "レビューを削除しました。");
		
		return "redirect:/restaurants/{restaurantId}";
	}
}

