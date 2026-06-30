package com.restaurant.api.review;

import java.time.LocalDate;

public class Review {

	private Integer id;
    private String userEmail;
    private Integer restaurantId;
    private Integer rating; 
    private String comment;
    private LocalDate reviewDate;
    private String serviceAddress;
    
    public Review () {
    	
    }
    
    public Review (Integer id, String userEmail, Integer restaurantId, Integer rating, String comment, LocalDate reviewDate, String serviceAddress) {
    	this.id = id;
    	this.userEmail = userEmail;
    	this.restaurantId = restaurantId;
    	this.rating = rating;
    	this.comment = comment;
    	this.reviewDate = reviewDate;
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

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public LocalDate getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getServiceAddress() {
		return serviceAddress;
	}

	public void setServiceAddress(String serviceAddress) {
		this.serviceAddress = serviceAddress;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
    
    
}
