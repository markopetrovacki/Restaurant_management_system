package com.restaurant.reservation_service.persistence;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Version;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservation")
public class ReservationEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Version
	private Integer version;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	
	@Column(name = "user_email")
    private String userEmail;

    @Column(name = "restaurant_id")
    private Integer restaurantId;
    
    @Column(name = "reservation_number")
    private Integer reservationNumber;
    
    @Column(name = "reservation_time")
    private LocalDateTime reservationTime;

    public ReservationEntity() {}

    public ReservationEntity(String userEmail, Integer restaurantId, Integer reservationNumber, LocalDateTime reservationTime) {
        this.userEmail = userEmail;
        this.restaurantId = restaurantId;
        this.reservationNumber = reservationNumber;
        this.reservationTime = reservationTime;
    }

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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
}
