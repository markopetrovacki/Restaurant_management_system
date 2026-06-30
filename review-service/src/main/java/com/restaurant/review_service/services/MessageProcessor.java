package com.restaurant.review_service.services;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.restaurant.api.event.Event;
import com.restaurant.api.review.Review;
import com.restaurant.api.review.ReviewService;


@Configuration
public class MessageProcessor {

	private static final Logger LOG = LoggerFactory.getLogger(MessageProcessor.class);

    private final ReviewService reviewService;

    public MessageProcessor(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    
    @Bean
    public Consumer<Event<Integer, Review>> reviewMessageProcessor() {
        return event -> {
            LOG.info("Procesiranje poruke kreirane u {}...", event.getEventCreatedAt());

            switch (event.getEventType()) {

	            case CREATE:
	                reviewService.createReview(event.getData());
	                break;
	
	            case DELETE:
	                reviewService.deleteReview(event.getKey());
	                break;
	
	            default:
	                throw new IllegalArgumentException("Unknown event type");
          
            }
            LOG.info("Procesiranje poruke završeno!");
        };
    }
}
