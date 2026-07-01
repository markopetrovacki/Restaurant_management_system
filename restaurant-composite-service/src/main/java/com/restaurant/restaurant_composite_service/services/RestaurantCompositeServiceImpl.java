package com.restaurant.restaurant_composite_service.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.api.composite.restaurant.RestaurantAggregate;
import com.restaurant.api.composite.restaurant.RestaurantCompositeService;
import com.restaurant.api.composite.restaurant.ReviewSummary;
import com.restaurant.api.composite.restaurant.ServiceAddresses;
import com.restaurant.api.restaurant.Restaurant;
import com.restaurant.api.review.Review;
import com.restaurant.util.http.ServiceUtil;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/composite")
public class RestaurantCompositeServiceImpl implements RestaurantCompositeService{

	private static final Logger LOG = LoggerFactory.getLogger(RestaurantCompositeServiceImpl.class);
	
	private final RestaurantCompositeIntegration integration;
	private final ServiceUtil serviceUtil;
	
	@Autowired
	public RestaurantCompositeServiceImpl(RestaurantCompositeIntegration integration, ServiceUtil serviceUtil) {
		this.integration = integration;
		this.serviceUtil = serviceUtil;
	}

	@Override
	public Mono<RestaurantAggregate> getRestaurant(Integer restaurantId) {
		LOG.debug("Will get composite restaurant with id={}", restaurantId);

	    Restaurant restaurant = integration.getRestaurant(restaurantId);

	    List<Review> reviews = integration.getReviewsByRestaurant(restaurantId);

	    List<ReviewSummary> reviewSummaries = reviews.stream()
	            .map(review -> new ReviewSummary(
	                    review.getId(),
	                    review.getUserEmail(),
	                    review.getRating(),
	                    review.getComment()
	            ))
	            .toList();

	    ServiceAddresses serviceAddresses = new ServiceAddresses(
	            serviceUtil.getServiceAddress(),
	            restaurant.getServiceAddress(),
	            reviews.isEmpty() ? "" : reviews.get(0).getServiceAddress()
	    );

	    RestaurantAggregate aggregate = new RestaurantAggregate(
	            restaurant.getId(),
	            restaurant.getName(),
	            restaurant.getLocation(),
	            restaurant.getPhoneNumber(),
	            reviewSummaries,
	            serviceAddresses
	    );

	    return Mono.just(aggregate);
	}
	
}
