package com.example.nagoyameshi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.entity.Restaurant;
import com.example.nagoyameshi.entity.Review;
import com.example.nagoyameshi.form.ReservationInputForm;
import com.example.nagoyameshi.repository.CategoryRepository;
import com.example.nagoyameshi.repository.RestaurantRepository;
import com.example.nagoyameshi.repository.ReviewRepository;
import com.example.nagoyameshi.service.ReviewService;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {
	private final RestaurantRepository restaurantRepository;
	private final CategoryRepository categoryRepository;
	private final ReviewRepository reviewRepository;
	private final ReviewService reviewService;
	
	public RestaurantController(RestaurantRepository restaurantRepository, CategoryRepository categoryRepository, ReviewRepository reviewRepository, ReviewService reviewService) {
		this.restaurantRepository = restaurantRepository;
		this.categoryRepository = categoryRepository;
		this.reviewRepository = reviewRepository;
		this.reviewService = reviewService;
	}
	
	@GetMapping
	public String index(@RequestParam(name = "keyword", required = false) String keyword,
						@RequestParam(name = "categoryType", required = false) Integer categoryType,
						@RequestParam(name = "highestPrice", required = false) Integer highestPrice,
						@PageableDefault(page = 0, size = 15, sort = "id", direction = Direction.ASC) Pageable pageable,
						Model model) 
	{
		Page<Restaurant> restaurantPage;
		List<Category> categories = categoryRepository.findAll();
		
		if (keyword != null && !keyword.isEmpty()) {
			restaurantPage = restaurantRepository.findByNameLike("%" + keyword + "%", pageable);
		} else  if (categoryType != null) {
			restaurantPage = restaurantRepository.findByCategoryType(categoryType, pageable);
		} else if (highestPrice != null) {
			restaurantPage = restaurantRepository.findByHighestPriceLessThanEqual(highestPrice, pageable);
		} else {
			restaurantPage = restaurantRepository.findAll(pageable);
		}
		
		model.addAttribute("restaurantPage", restaurantPage);
		model.addAttribute("keyword", keyword);
		model.addAttribute("categoryType",categoryType);
		model.addAttribute("highestPrice",highestPrice);
		model.addAttribute("categories", categories);
	
		return "restaurants/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable(name = "id") Integer id, Model model, @PageableDefault(page = 0, size = 15, sort = "restaurant_id", direction = Direction.ASC) Pageable pageable) {
		Restaurant restaurant = restaurantRepository.getReferenceById(id);
		Page<Review> reviewPage = reviewRepository.findAll(pageable);
		Optional<Review> reviewOptional = reviewRepository.findById(id);
		
		if (reviewOptional.isPresent()) {
	        Review review = reviewOptional.get();
	        model.addAttribute("review", review);
	    } else {
	        // レビューが見つからない場合の処理
	    }
		
		model.addAttribute("restaurant", restaurant);
		model.addAttribute("reservationInputForm", new ReservationInputForm());
		model.addAttribute("reviewPage", reviewPage);
		
		return "restaurants/show";
	}
}
