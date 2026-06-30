package com.restaurant.review_service.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer>{

	Optional<ReviewEntity> findById (Integer id);
	//Optional<ReviewEntity> findByUserEmail (String userEmail);
	//Optional<ReviewEntity> findByRestaurantId (Integer restaurantId);
	List<ReviewEntity> findByRestaurantId(Integer restaurantId);
	List<ReviewEntity> findByUserEmail(String userEmail);
}
