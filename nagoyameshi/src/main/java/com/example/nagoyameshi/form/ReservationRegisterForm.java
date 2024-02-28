package com.example.nagoyameshi.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationRegisterForm {
	private Integer restaurantId;
	
	public Integer getRestaurantId() {
		return restaurantId;
	}
	
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	
	private Integer memberId;
	
	public Integer getMemberId() {
		return memberId;
	}
	
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
		
	private String fromReservedDate;
	
	public String getFromReservedDate() {
		return fromReservedDate;
	}
	
	public void setFromReservedDate(String fromReservedDate) {
		this.fromReservedDate = fromReservedDate;
	}
	
	private String reservedTime;
	
	public String getReservedTime() {
		return reservedTime;
	}
	
	public void setReservedTime(String reservedTime) {
		this.reservedTime = reservedTime;
	}
	
	private Integer numberOfPeople;
	
	public Integer getNumberOfPeople() {
		return numberOfPeople;
	}
	
	public void setNumberOfPeople(Integer numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}
}
