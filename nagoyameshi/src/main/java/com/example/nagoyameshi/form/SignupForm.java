package com.example.nagoyameshi.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupForm {
	@NotBlank(message = "氏名を入力してください。")
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name= name;
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
	
	@NotBlank(message = "電話番号を入力してください。")
	private String phoneNumber;
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@NotBlank(message = "メールアドレスを入力してください。") 
	@Email(message = "メールアドレスは正しい形式で入力してください。")
	private String email;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmaik(String email) {
		this.email = email;
	}
	
	 @NotBlank(message = "パスワードを入力してください。")
	 @Length(min = 8, message = "パスワードは8文字以上で入力してください。")
	 private String password;
	 
	 public String getPassword() {
		 return password;
	 }
	 
	 public void setPassword(String password) {
		 this.password = password;
	 }
	 
	 @NotBlank(message = "パスワード（確認用）を入力してください。")
	 private String passwordConfirmation;
}
