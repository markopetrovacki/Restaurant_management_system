package com.restaurant.api.review;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface ReviewService {

	@GetMapping(value = "/{id}", produces = "application/json")
    Review getReview(@PathVariable("id") Integer id);

    @GetMapping(value = "", produces = "application/json")
    List<Review> getReviews();

    @GetMapping(value = "/restaurant/{restaurantId}", produces = "application/json")
    List<Review> getReviewsByRestaurant(@PathVariable("restaurantId") Integer restaurantId);

    @GetMapping(value = "/user/{userEmail}", produces = "application/json")
    List<Review> getReviewsByUser(@PathVariable("userEmail") String userEmail);

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    Review createReview(@RequestBody Review body);

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    Review updateReview(@PathVariable("id") Integer id, @RequestBody Review body);

    @DeleteMapping(value = "/{id}")
    Void deleteReview(@PathVariable("id") Integer id);
    
}
