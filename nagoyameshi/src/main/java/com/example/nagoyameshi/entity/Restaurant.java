package com.example.nagoyameshi.entity;

import java.sql.Time;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "restaurants")
@Data
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "category_type")
	private Integer categoryType;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "lowest_price")
	private Integer lowestPrice;
	
	@Column(name = "highest_price")
	private Integer highestPrice;
	
	@Column(name = "postal_code")
	private String postalCode;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "opening_time")
	private Time openingTime;
	
	@Column(name = "closing_time")
	private Time closingTime;
	
	@Column(name = "seating_capacity")
	private Integer seatingCapacity;
	
	@Column(name = "holiday")
	private String holiday;
	
	@Column(name = "created_at", insertable = false, updatable = false)
	private Timestamp createdAt;
	
	@Column(name = "updated_at", insertable = false, updatable = false)
	private Timestamp updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "category_type", insertable=false, updatable=false)
	private Category category;
	
	public Category getCategory() {
		return category;
	}
}
