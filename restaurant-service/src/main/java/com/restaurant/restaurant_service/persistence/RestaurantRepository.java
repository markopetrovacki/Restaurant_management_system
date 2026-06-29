package com.restaurant.restaurant_service.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Integer> {

	Optional<RestaurantEntity> findById(Integer id);
	Optional<RestaurantEntity> findByName(String name);
	
}
