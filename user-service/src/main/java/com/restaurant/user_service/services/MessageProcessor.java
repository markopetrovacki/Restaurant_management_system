package com.restaurant.user_service.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import com.restaurant.api.user.User;       
import com.restaurant.api.event.Event;  

import com.restaurant.api.user.UserService;

@Configuration
public class MessageProcessor {

	private static final Logger LOG = LoggerFactory.getLogger(MessageProcessor.class);

    private final UserService userService;

    public MessageProcessor(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public Consumer<Event<Integer, User>> userMessageProcessor() {
        return event -> {
            LOG.info("Procesiranje poruke kreirane u {}...", event.getEventCreatedAt());

            switch (event.getEventType()) {

	            case CREATE:
	                userService.createUser(event.getData());
	                break;
	
	            case DELETE:
	                userService.deleteUser(event.getKey());
	                break;
	
	            default:
	                throw new IllegalArgumentException("Unknown event type");
          
            }
            LOG.info("Procesiranje poruke završeno!");
        };
    }
	
}
