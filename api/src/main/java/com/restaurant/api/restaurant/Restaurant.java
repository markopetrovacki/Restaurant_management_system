package com.restaurant.api.restaurant;

public class Restaurant {

	private Integer id;	
    private String name;
    private String location;
    private String phoneNumber;
    private String serviceAddress;
    
    public Restaurant () {
    	
    }
    
	public Restaurant ( Integer id, String name, String location, String phoneNumber, String serviceAddress ) {
		
		this.id = id;
		this.name = name;
		this.location = location;
		this.phoneNumber = phoneNumber;
		this.serviceAddress = serviceAddress;
    	
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getServiceAddress() {
		return serviceAddress;
	}

	public void setServiceAddress(String serviceAddress) {
		this.serviceAddress = serviceAddress;
	}
	
	

}
