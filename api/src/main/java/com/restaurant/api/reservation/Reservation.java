package com.restaurant.api.reservation;

import java.time.LocalDateTime;

public class Reservation {

	
	private Integer id;
    private String userEmail;
    private Integer restaurantId;
    private Integer reservationNumber; 
    private LocalDateTime reservationTime;
    private String serviceAddress;
    
    public Reservation() {
    	
    }
    
    public Reservation(Integer id, String userEmail, Integer restaurantId, Integer reservationNumber, LocalDateTime reservationTime, String serviceAddress ) {
    	this.id = id;
    	this.userEmail = userEmail;
    	this.restaurantId = restaurantId;
    	this.reservationNumber = reservationNumber;
    	this.reservationTime = reservationTime;
    	this.serviceAddress = serviceAddress;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Integer getReservationNumber() {
		return reservationNumber;
	}

	public void setReservationNumber(Integer reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

	public LocalDateTime getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(LocalDateTime reservationTime) {
		this.reservationTime = reservationTime;
	}

	public String getServiceAddress() {
		return serviceAddress;
	}

	public void setServiceAddress(String serviceAddress) {
		this.serviceAddress = serviceAddress;
	}
}
