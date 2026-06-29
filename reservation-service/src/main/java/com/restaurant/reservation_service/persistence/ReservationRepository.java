package com.restaurant.reservation_service.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer>{

	Optional<ReservationEntity> findById (Integer id);
	Optional<ReservationEntity> findByUserEmail (String userEmail);
	Optional<ReservationEntity> findByReservationNumber(Integer reservationNumber);
	
}
