package com.example.nagoyameshi.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CategoryEditForm {
	@NotNull
	private Integer id;
	
	@NotBlank(message = "カテゴリ名を入力してください。")
	private String name;
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public CategoryEditForm(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
}
