package com.restaurant.restaurant_service.services;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.restaurant.api.event.Event;
import com.restaurant.api.restaurant.Restaurant;
import com.restaurant.api.restaurant.RestaurantService;
import com.restaurant.api.user.User;



@Configuration
public class MessageProcessor {

	private static final Logger LOG = LoggerFactory.getLogger(MessageProcessor.class);

    private final RestaurantService restaurantService;

    public MessageProcessor(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }
    
    @Bean
    public Consumer<Event<Integer, Restaurant>> restaurantMessageProcessor() {
        return event -> {
            LOG.info("Procesiranje poruke kreirane u {}...", event.getEventCreatedAt());

            switch (event.getEventType()) {

	            case CREATE:
	                restaurantService.createRestaurant(event.getData());
	                break;
	
	            case DELETE:
	                restaurantService.deleteRestaurant(event.getKey());
	                break;
	
	            default:
	                throw new IllegalArgumentException("Unknown event type");
          
            }
            LOG.info("Procesiranje poruke završeno!");
        };
    }
    
}
