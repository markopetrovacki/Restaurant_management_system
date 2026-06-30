package com.restaurant.notification_service.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer>{

	Optional<NotificationEntity> findById (Integer id);
	//Optional<NotificationEntity> findByRecipientEmail (String recipientEmail);
	List<NotificationEntity> findByRecipientEmail (String recipientEmail);
}
