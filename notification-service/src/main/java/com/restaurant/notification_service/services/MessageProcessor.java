package com.restaurant.notification_service.services;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.restaurant.api.event.Event;
import com.restaurant.api.notification.Notification;
import com.restaurant.api.notification.NotificationService;


@Configuration
public class MessageProcessor {

	private static final Logger LOG = LoggerFactory.getLogger(MessageProcessor.class);

    private final NotificationService notificationService;

    public MessageProcessor(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    
    @Bean
    public Consumer<Event<Integer, Notification>> notificationMessageProcessor() {
        return event -> {
            LOG.info("Procesiranje poruke kreirane u {}...", event.getEventCreatedAt());

            switch (event.getEventType()) {

	            case CREATE:
	                notificationService.createNotification(event.getData());
	                break;
	
	            case DELETE:
	                notificationService.deleteNotification(event.getKey());
	                break;
	
	            default:
	                throw new IllegalArgumentException("Unknown event type");
          
            }
            LOG.info("Procesiranje poruke završeno!");
        };
    }

}
