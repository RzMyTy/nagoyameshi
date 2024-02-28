package com.example.nagoyameshi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	public Page<Restaurant> findByNameLike(String keyword, Pageable pageable);
	public Page<Restaurant> findByHighestPriceLessThanEqual(Integer highestPrice, Pageable pageable);
	public Page<Restaurant> findByCategoryType(Integer categoryType, Pageable pageable);
	
	public List<Restaurant> findTop10ByOrderByCreatedAtDesc();
}