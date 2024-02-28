package com.example.nagoyameshi.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationInputForm {
	@NotBlank(message = "予約日を選択してください。")
	private String fromReservedDate;
	
	public String getFromReservedDate() {
		return fromReservedDate;
	}
	
	public void setFromReservedDate(String fromReservedDate) {
		this.fromReservedDate = fromReservedDate;
	}
	
	@NotBlank(message = "予約時間を選択してください。")
	private String reservedTime;
	
	public String getReservedTime() {
		return reservedTime;
	}
	
	public void setReservedTime(String reservedTime) {
		this.reservedTime = reservedTime;
	}
	
	
	@NotNull(message = "予約人数を選択してください。")
	@Min(value = 1, message = "予約人数は1人以上に設定してください。")
	private Integer numberOfPeople;
	
	public Integer getNumberOfPeople() {
		return numberOfPeople;
	}
	
	public void setNumberOfPeople(Integer numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}
}
