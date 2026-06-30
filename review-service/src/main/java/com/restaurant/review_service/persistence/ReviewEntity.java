package com.restaurant.review_service.persistence;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.data.annotation.Version;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "review_model")
public class ReviewEntity implements Serializable  {

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

    @Column(name = "rating")
    private Integer rating; 
    
    @Column(name = "comment")
    private String comment;
    
    @Column(name = "review_date")
    private LocalDate reviewDate;

    public ReviewEntity() {}

    public ReviewEntity(String userEmail, Integer restaurantId, Integer rating, String comment, LocalDate reviewDate) {
        this.userEmail = userEmail;
        this.restaurantId = restaurantId;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
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

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDate getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}
}
