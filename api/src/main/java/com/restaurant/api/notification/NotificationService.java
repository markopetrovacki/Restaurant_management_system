package com.restaurant.api.notification;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface NotificationService {

	@GetMapping(value = "/{id}", produces = "application/json")
    Notification getNotification(@PathVariable("id") Integer id);

    @GetMapping(value = "", produces = "application/json")
    List<Notification> getNotifications();

    @GetMapping(value = "/email/{email}", produces = "application/json")
    List<Notification> getNotificationsByEmail(@PathVariable("email") String email);

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    Notification createNotification(@RequestBody Notification body);

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    Notification updateNotification(@PathVariable("id") Integer id,
                                   @RequestBody Notification body);

    @DeleteMapping(value = "/{id}")
    Void deleteNotification(@PathVariable("id") Integer id);
}
