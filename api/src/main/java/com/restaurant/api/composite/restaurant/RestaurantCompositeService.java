package com.restaurant.api.composite.restaurant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

@Tag(name = "RestaurantComposite", description = "REST API for composite club information.")
public interface RestaurantCompositeService {

	@GetMapping(value = "/restaurant-composite/{restaurantId}", produces = "application/json")
	Mono<RestaurantAggregate> getRestaurant (@PathVariable ("restaurantId") Integer restaurantId);
}
