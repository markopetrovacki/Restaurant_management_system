package com.restaurant.reservation_service.service;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.restaurant.api.event.Event;
import com.restaurant.api.reservation.Reservation;
import com.restaurant.api.reservation.ReservationService;

@Configuration
public class MessageProcessor {

	private static final Logger LOG = LoggerFactory.getLogger(MessageProcessor.class);

    private final ReservationService reservationService;

    public MessageProcessor(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    
    @Bean
    public Consumer<Event<Integer, Reservation>> reservationMessageProcessor() {
        return event -> {
            LOG.info("Procesiranje poruke kreirane u {}...", event.getEventCreatedAt());

            switch (event.getEventType()) {

	            case CREATE:
	                reservationService.createReservation(event.getData());
	                break;
	
	            case DELETE:
	                reservationService.deleteReservation(event.getKey());
	                break;
	
	            default:
	                throw new IllegalArgumentException("Unknown event type");
          
            }
            LOG.info("Procesiranje poruke završeno!");
        };
    }
}
