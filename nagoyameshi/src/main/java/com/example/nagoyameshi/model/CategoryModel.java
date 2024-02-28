package com.example.nagoyameshi.model;

public class CategoryModel {
	private Integer id;
		
	private String name;
	
	public CategoryModel(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
}
