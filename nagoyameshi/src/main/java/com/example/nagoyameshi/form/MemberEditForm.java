package com.example.nagoyameshi.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberEditForm {
	@NotNull
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotBlank(message = "氏名を入力してください。")
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank(message = "フリガナを入力してください。")
	private String furigana;
	
	public String getFurigana() {
		return furigana;
	}
	
	public void setFurigana(String furigana) {
		this.furigana = furigana;
	}
	
	@NotBlank(message = "郵便番号を入力してください。")
	private String postalCode;
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	@NotBlank(message = "住所を入力してください。")
	private String address;
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@NotBlank(message = "電話番号を入力して下さい。")
	private String phoneNumber;
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@NotBlank(message = "メールアドレスを入力してください。")
	private String email;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
