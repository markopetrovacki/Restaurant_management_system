package com.restaurant.api.composite.restaurant;

public class ServiceAddresses {

	private final String compositeAddress;
	private final String restaurantAddress;
	private final String reviewAddress;
	
	public ServiceAddresses() {
		this.compositeAddress = null;
		this.restaurantAddress = null;
		this.reviewAddress = null;
	}
	
	public ServiceAddresses(String compositeAddress, String restaurantAddress, String reviewAddress) {
		this.compositeAddress = compositeAddress;
		this.restaurantAddress = restaurantAddress;
		this.reviewAddress = reviewAddress;
	}

	public String getCompositeAddress() {
		return compositeAddress;
	}

	public String getRestaurantAddress() {
		return restaurantAddress;
	}

	public String getReviewAddress() {
		return reviewAddress;
	}
	
	
}
