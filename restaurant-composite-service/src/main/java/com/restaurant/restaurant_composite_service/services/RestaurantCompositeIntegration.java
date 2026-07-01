package com.restaurant.restaurant_composite_service.services;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.api.restaurant.Restaurant;
import com.restaurant.api.restaurant.RestaurantService;
import com.restaurant.api.review.Review;
import com.restaurant.api.review.ReviewService;

@Component
public class RestaurantCompositeIntegration implements RestaurantService, ReviewService {

	private static final Logger LOG = LoggerFactory.getLogger(RestaurantCompositeIntegration.class);
	
	private final WebClient.Builder webClientBuilder;
	private WebClient webClient;
	private final ObjectMapper mapper;
	
	private final String restaurantServiceUrl = "http://restaurant-service/restaurants";
	private final String reviewServiceUrl = "http://review-service/reviews";
	
	@Autowired
	public RestaurantCompositeIntegration(WebClient.Builder webClientBuilder, ObjectMapper mapper) {
		this.webClientBuilder = webClientBuilder;
		this.mapper = mapper;
	}

	@Override
	public Review getReview(Integer id) {
		 URI url = UriComponentsBuilder
		            .fromUriString(reviewServiceUrl + "/{id}")
		            .build(id);

		    LOG.debug("Calling Review Service: {}", url);

		    return getWebClient().get()
		            .uri(url)
		            .retrieve()
		            .bodyToMono(Review.class)
		            .block();
	}

	@Override
	public List<Review> getReviews() {
		 URI url = UriComponentsBuilder
		            .fromUriString(reviewServiceUrl + "")
		            .build()
		            .toUri();

		    LOG.debug("Calling Review Service: {}", url);

		    return getWebClient().get()
		            .uri(url)
		            .retrieve()
		            .bodyToFlux(Review.class)
		            .collectList()
		            .block();
	}

	@Override
	public List<Review> getReviewsByRestaurant(Integer restaurantId) {
		URI url = UriComponentsBuilder
	            .fromUriString(reviewServiceUrl + "/restaurant/{restaurantId}")
	            .build(restaurantId);

	    LOG.debug("Calling Review Service: {}", url);

	    return getWebClient().get()
	            .uri(url)
	            .retrieve()
	            .bodyToFlux(Review.class)
	            .collectList()
	            .block();
	}

	@Override
	public List<Review> getReviewsByUser(String userEmail) {
		URI url = UriComponentsBuilder
	            .fromUriString(reviewServiceUrl + "/user/{userEmail}")
	            .build(userEmail);

	    LOG.debug("Calling Review Service: {}", url);

	    return getWebClient().get()
	            .uri(url)
	            .retrieve()
	            .bodyToFlux(Review.class)
	            .collectList()
	            .block();
	}

	@Override
	public Review createReview(Review body) {
		URI url = UriComponentsBuilder
	            .fromUriString(reviewServiceUrl + "")
	            .build()
	            .toUri();

	    LOG.debug("Calling Review Service: {}", url);

	    return getWebClient().post()
	            .uri(url)
	            .bodyValue(body)
	            .retrieve()
	            .bodyToMono(Review.class)
	            .block();
	}

	@Override
	public Review updateReview(Integer id, Review body) {
		URI url = UriComponentsBuilder
	            .fromUriString(reviewServiceUrl + "/{id}")
	            .build(id);

	    LOG.debug("Calling Review Service: {}", url);

	    return getWebClient().put()
	            .uri(url)
	            .bodyValue(body)
	            .retrieve()
	            .bodyToMono(Review.class)
	            .block();
	}

	@Override
	public Void deleteReview(Integer id) {
		 URI url = UriComponentsBuilder
		            .fromUriString(reviewServiceUrl + "/{id}")
		            .build(id);

		    LOG.debug("Calling Review Service: {}", url);

		    return getWebClient().delete()
		            .uri(url)
		            .retrieve()
		            .bodyToMono(Void.class)
		            .block();
	}

	@Override
	public Restaurant getRestaurant(Integer id) {
		URI url = UriComponentsBuilder
	            .fromUriString(restaurantServiceUrl + "/{id}")
	            .build(id);

	    LOG.debug("Calling Restaurant Service: {}", url);

	    return getWebClient().get()
	            .uri(url)
	            .retrieve()
	            .bodyToMono(Restaurant.class)
	            .block();
	}

	@Override
	public Restaurant getRestaurantByName(String name) {
		URI url = UriComponentsBuilder
	            .fromUriString(restaurantServiceUrl + "/name/{name}")
	            .build(name);

	    LOG.debug("Calling Restaurant Service: {}", url);

	    return getWebClient().get()
	            .uri(url)
	            .retrieve()
	            .bodyToMono(Restaurant.class)
	            .block();
	}

	@Override
	public Restaurant createRestaurant(Restaurant body) {
		URI url = UriComponentsBuilder
	            .fromUriString(restaurantServiceUrl + "")
	            .build()
	            .toUri();

	    LOG.debug("Calling Restaurant Service: {}", url);

	    return getWebClient().post()
	            .uri(url)
	            .bodyValue(body)
	            .retrieve()
	            .bodyToMono(Restaurant.class)
	            .block();
	}

	@Override
	public Void deleteRestaurant(Integer id) {
		 URI url = UriComponentsBuilder
		            .fromUriString(restaurantServiceUrl + "/{id}")
		            .build(id);

		    LOG.debug("Calling Restaurant Service: {}", url);

		    return getWebClient().delete()
		            .uri(url)
		            .retrieve()
		            .bodyToMono(Void.class)
		            .block();
	}

	@Override
	public Restaurant updateRestaurant(Integer id, Restaurant body) {
		URI url = UriComponentsBuilder
	            .fromUriString(restaurantServiceUrl + "/{id}")
	            .build(id);

	    LOG.debug("Calling Restaurant Service: {}", url);

	    return getWebClient().put()
	            .uri(url)
	            .bodyValue(body)
	            .retrieve()
	            .bodyToMono(Restaurant.class)
	            .block();
	}
	
	private WebClient getWebClient() {

	    if (webClient == null) {
	        webClient = webClientBuilder.build();
	    }

	    return webClient;
	}
	
}
