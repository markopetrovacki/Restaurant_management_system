package com.restaurant.api.composite.restaurant;

public class ReviewSummary {

	private final Integer id;
    private final String userEmail;
    private final Integer rating;
    private final String comment;

    public ReviewSummary(Integer id, String userEmail, Integer rating, String comment) {
        this.id = id;
        this.userEmail = userEmail;
        this.rating = rating;
        this.comment = comment;
    }

	public Integer getId() {
		return id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public Integer getRating() {
		return rating;
	}

	public String getComment() {
		return comment;
	}
    
    
}
