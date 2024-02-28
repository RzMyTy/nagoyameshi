package com.example.nagoyameshi.model;

public class AuthenticationModel {
	private Integer id;
	
	private String email;
	
	private String password;
	
	public AuthenticationModel(Integer id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getemail() {
		return this.email;
	}
	
	public String getpassword() {
		return this.password;
	}
}
