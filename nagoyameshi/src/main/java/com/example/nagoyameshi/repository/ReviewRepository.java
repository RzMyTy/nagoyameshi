package com.example.nagoyameshi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.Member;
import com.example.nagoyameshi.entity.Restaurant;
import com.example.nagoyameshi.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	public Page<Review> findByMemberOrderByCreatedAtDesc(Member member, Pageable pageable);
	public Review findByRestaurantAndMember(Restaurant restaurant, Member member);
	public List<Review> findTop6ByRestaurantOrderByCreatedAtDesc(Restaurant restaurant);
}
