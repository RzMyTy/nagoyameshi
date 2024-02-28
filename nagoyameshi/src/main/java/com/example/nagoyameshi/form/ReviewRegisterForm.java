package com.example.nagoyameshi.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewRegisterForm {
	private Integer restaurantId;
	
	private Integer memberId;
	
	@NotNull(message = "評価してください（1～5段階）")
	private Integer score;
	
	@NotBlank(message = "説明文を記載してください。")
	private String content;
}
