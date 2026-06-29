package com.restaurant.restaurant_service.persistence;

import java.io.Serializable;

import org.springframework.data.annotation.Version;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurant")
public class RestaurantEntity implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Version
	private Integer version;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	
	@Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;
    
    @Column(name = "phone_number")
    private String phoneNumber;

    public RestaurantEntity() {}

    public RestaurantEntity(String name, String location, String phoneNumber) {
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
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
    
}
