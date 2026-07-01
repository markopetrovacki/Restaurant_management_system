package com.restaurant.api.composite.restaurant;

import java.util.List;

public class RestaurantAggregate {

	private final Integer restaurantId;	
    private final String name;
    private final String location;
    private final String phoneNumber;
    private final List<ReviewSummary> review;
    private final ServiceAddresses serviceAddresses;
    
    public RestaurantAggregate( Integer restaurantId, String name, String location, String phoneNumber, List<ReviewSummary> review, ServiceAddresses serviceAddresses) {
    	this.restaurantId = restaurantId;
    	this.name = name;
    	this.location = location;
    	this.phoneNumber = phoneNumber;
    	this.review = review;
    	this.serviceAddresses = serviceAddresses;
    }

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public List<ReviewSummary> getReview() {
		return review;
	}

	public ServiceAddresses getServiceAddresses() {
		return serviceAddresses;
	}
    
    
}
