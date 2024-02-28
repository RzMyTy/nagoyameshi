package com.example.nagoyameshi.entity;

import java.sql.Timestamp;
import java.time.LocalDate;

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
@Table(name = "reservations")
@Data
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;
	
	public Restaurant getRestaurant() {
		return restaurant; 
	}
	
	public void setReataurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	public Member getMember() {
		return member;
	}
	
	public void setMember(Member member) {
		this.member = member;
	}
	
	@Column(name = "reserved_date")
	private LocalDate fromReservedDate;
	
	public LocalDate getFromReservedDate() {
		return fromReservedDate;
	}
	
	public void setFromReservedDate(LocalDate fromReservedDate) {
		this.fromReservedDate = fromReservedDate;
	}
	
	@Column(name = "reserved_time")
	private String reservedTime;
	
	public String getReservedTime() {
		return reservedTime;
	}
	
	public void setReservedTime(String reservedTime) {
		this.reservedTime = reservedTime;
	}
	
	@Column(name = "number_of_people")
	private Integer numberOfPeople;
	
	public Integer getNumberOfPeople() {
		return numberOfPeople;
	}
	
	public void setNumberOfPeople(Integer numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}
	
	@Column(name = "created_at", insertable = false, updatable = false)
	private Timestamp createdAt;
	
	@Column(name = "updated_at", insertable = false, updatable = false)
	private Timestamp updatedAt;
}
