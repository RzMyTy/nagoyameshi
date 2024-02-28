package com.example.nagoyameshi.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyameshi.entity.Member;
import com.example.nagoyameshi.entity.Reservation;
import com.example.nagoyameshi.entity.Restaurant;
import com.example.nagoyameshi.form.ReservationRegisterForm;
import com.example.nagoyameshi.repository.MemberRepository;
import com.example.nagoyameshi.repository.ReservationRepository;
import com.example.nagoyameshi.repository.RestaurantRepository;

@Service
public class ReservationService {
	private final ReservationRepository reservationRepository;
	private final RestaurantRepository restaurantRepository;
	private final MemberRepository memberRepository;
	
	public ReservationService(ReservationRepository reservationRepository, RestaurantRepository restaurantRepository, MemberRepository memberRepository) {
		this.reservationRepository = reservationRepository;
		this.restaurantRepository = restaurantRepository;
		this.memberRepository = memberRepository;
	}
	
	 @Transactional
	 public void create(ReservationRegisterForm reservationRegisterForm) {
		 Reservation reservation = new Reservation();
		 Restaurant restaurant = restaurantRepository.getReferenceById(reservationRegisterForm.getRestaurantId());
		 Member member = memberRepository.getReferenceById(reservationRegisterForm.getMemberId());
		 LocalDate fromReservedDate = LocalDate.parse(reservationRegisterForm.getFromReservedDate());
		 
		 
		 reservation.setRestaurant(restaurant);
		 reservation.setMember(member);
		 reservation.setFromReservedDate(fromReservedDate);
		 reservation.setReservedTime(reservationRegisterForm.getReservedTime());
		 reservation.setNumberOfPeople(reservationRegisterForm.getNumberOfPeople());
		 
		 reservationRepository.save(reservation);
	 }
	
	//予約人数が席数以下かチェックする
	public boolean isWithinCapacity(Integer numberOfPeople, Integer seatingCapacity) {
		return numberOfPeople <= seatingCapacity;
	}
}
