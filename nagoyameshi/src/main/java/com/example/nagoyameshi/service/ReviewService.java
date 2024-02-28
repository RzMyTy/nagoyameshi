package com.example.nagoyameshi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyameshi.entity.Member;
import com.example.nagoyameshi.entity.Restaurant;
import com.example.nagoyameshi.entity.Review;
import com.example.nagoyameshi.form.ReviewEditForm;
import com.example.nagoyameshi.form.ReviewRegisterForm;
import com.example.nagoyameshi.repository.MemberRepository;
import com.example.nagoyameshi.repository.RestaurantRepository;
import com.example.nagoyameshi.repository.ReviewRepository;

@Service
public class ReviewService {
	private final RestaurantRepository restaurantRepository;
	private final ReviewRepository reviewRepository;
	private final MemberRepository memberRepository;
	
	public ReviewService(RestaurantRepository restaurantRepository, ReviewRepository reviewRepository, MemberRepository memberRepository) {
		this.restaurantRepository = restaurantRepository;
		this.reviewRepository = reviewRepository;
		this.memberRepository = memberRepository;
	}
	
	@Transactional
	public void create(Restaurant restaurant, Member member, ReviewRegisterForm reviewRegisterForm) {
		Review review = new Review();

		review.setRestaurant(restaurant);
		review.setMember(member);
		review.setScore(reviewRegisterForm.getScore());
		review.setContent(reviewRegisterForm.getContent());
		
		reviewRepository.save(review);
	}
	
	@Transactional
	public void edit(ReviewEditForm reviewEditForm) {
		Review review = reviewRepository.getReferenceById(reviewEditForm.getId());
		
		review.setScore(reviewEditForm.getScore());
		review.setContent(reviewEditForm.getContent());
		
		reviewRepository.save(review);
	}
	
	@Transactional
	public void update(ReviewEditForm reviewEditForm) {
		Review review = reviewRepository.getReferenceById(reviewEditForm.getId());
		
		review.setScore(reviewEditForm.getScore());
		review.setContent(reviewEditForm.getContent());
		
		reviewRepository.save(review);
	}
	
	public boolean alreadyReviewed(Restaurant restaurant, Member member) {
        return reviewRepository.findByRestaurantAndMember(restaurant, member) != null;
	}
}
