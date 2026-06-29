package com.restaurant.api.restaurant;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface RestaurantService {

	@GetMapping(value = "/{id}", produces = "application/json")
    Restaurant getRestaurant(@PathVariable("id") Integer id);

    @GetMapping(value = "/name/{name}", produces = "application/json")
    Restaurant getRestaurantByName(@PathVariable("name") String name);

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    Restaurant createRestaurant(@RequestBody Restaurant body);

    @DeleteMapping(value = "/{id}")
    Void deleteRestaurant(@PathVariable("id") Integer id);

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    Restaurant updateRestaurant(@PathVariable("id") Integer id,
                                @RequestBody Restaurant body);
    
}
