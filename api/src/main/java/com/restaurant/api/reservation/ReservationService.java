package com.restaurant.api.reservation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface ReservationService {

	@GetMapping(value = "/{id}", produces = "application/json")
    Reservation getReservation(@PathVariable("id") Integer id);

    @GetMapping(value = "/number/{reservationNumber}", produces = "application/json")
    Reservation getReservationByReservationNumber(
            @PathVariable("reservationNumber") Integer reservationNumber);

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    Reservation createReservation(@RequestBody Reservation body);

    @DeleteMapping(value = "/{id}")
    Void deleteReservation(@PathVariable("id") Integer id);

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    Reservation updateReservation(@PathVariable("id") Integer id,
                                  @RequestBody Reservation body);
	
}
