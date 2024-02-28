package com.example.nagoyameshi.form;

import java.sql.Time;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RestaurantRegisterForm {
	@NotBlank(message = "店舗名を入力してください。")
	private String name;
	
	private MultipartFile imageFile;
	
	@NotBlank(message = "説明を入力してください。")
	private String description;
	
	@NotNull(message = "最低価格を入力してください。")
	private Integer lowestPrice;
	
	@NotNull(message = "最高価格を入力してください。")
	private Integer highestPrice;
	
	@NotBlank(message = "郵便番号を入力してください。（ハイフンを含む）")
	private String postalCode;
	
	@NotBlank(message = "住所を入力してください。")
	private String address;
	
	@NotBlank(message = "営業開始時間を入力してください。")
	private Time openingTime;
	
	@NotBlank(message = "営業終了時間を入力してください。")
	private Time closingTime;
	
	@NotNull(message = "座席数を入力してください。")
	@Min(value = 1, message = "座席数は1以上に設定してください")
	private Integer capacity;
	
	@NotBlank(message = "定休日を入力してください。")
	private String holiday;
	
	@NotNull(message = "カテゴリを選択してください。")
	private Integer categoryType;
}
