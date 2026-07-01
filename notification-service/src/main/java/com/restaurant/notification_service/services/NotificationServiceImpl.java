package com.restaurant.notification_service.services;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.api.notification.Notification;
import com.restaurant.api.notification.NotificationService;
import com.restaurant.notification_service.persistence.NotificationEntity;
import com.restaurant.notification_service.persistence.NotificationRepository;
import com.restaurant.util.exceptions.InvalidInputException;
import com.restaurant.util.exceptions.NotFoundException;
import com.restaurant.util.http.ServiceUtil;

@RestController
@RequestMapping("/notifications")
public class NotificationServiceImpl implements NotificationService {

	private static final Logger LOG = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private final ServiceUtil serviceUtil;
    private final NotificationRepository repository;
    private final NotificationMapper mapper;

    public NotificationServiceImpl(NotificationRepository repository,
                                   NotificationMapper mapper,
                                   ServiceUtil serviceUtil) {
        this.repository = repository;
        this.mapper = mapper;
        this.serviceUtil = serviceUtil;
    }

    @Override
    public Notification getNotification(Integer id) {

        LOG.debug("getNotification: {}", id);

        if (id == null || id < 1) {
            throw new InvalidInputException("Nevalidan ID: " + id);
        }

        NotificationEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Notification ne postoji: " + id));

        Notification notification = mapper.entityToApi(entity);
        notification.setServiceAddress(serviceUtil.getServiceAddress());

        return notification;
    }

    @Override
    public List<Notification> getNotifications() {

        LOG.debug("getNotifications");

        List<Notification> list =
                mapper.entityListToApiList(repository.findAll());

        list.forEach(n ->
                n.setServiceAddress(serviceUtil.getServiceAddress()));

        return list;
    }

    @Override
    public List<Notification> getNotificationsByEmail(String email) {

        LOG.debug("getNotificationsByEmail: {}", email);

        if (email == null || email.trim().isEmpty()) {
            throw new InvalidInputException("Email ne sme biti prazan");
        }

        List<Notification> list =
                mapper.entityListToApiList(
                        repository.findByRecipientEmail(email));

        list.forEach(n ->
                n.setServiceAddress(serviceUtil.getServiceAddress()));

        return list;
    }

    @Override
    public Notification createNotification(@RequestBody Notification body) {

        LOG.debug("createNotification: {}", body.getRecipientEmail());

        if (body.getRecipientEmail() == null ||
            body.getRecipientEmail().trim().isEmpty()) {
            throw new InvalidInputException("Email je obavezan");
        }

        try {

            NotificationEntity entity = mapper.apiToEntity(body);

            if (entity.getSentAt() == null) {
                entity.setSentAt(LocalDateTime.now());
            }

            NotificationEntity saved = repository.save(entity);

            Notification notification = mapper.entityToApi(saved);
            notification.setServiceAddress(serviceUtil.getServiceAddress());

            return notification;

        } catch (DataIntegrityViolationException ex) {
            throw new InvalidInputException("Greška pri kreiranju notification-a");
        }
    }

    @Override
    public Notification updateNotification(Integer id, @RequestBody Notification body) {

        LOG.debug("updateNotification: {}", id);

        if (id == null || id < 1) {
            throw new InvalidInputException("Nevalidan ID: " + id);
        }

        NotificationEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Notification ne postoji: " + id));

        if (body.getRecipientEmail() != null) {
            entity.setRecipientEmail(body.getRecipientEmail());
        }

        if (body.getSubject() != null) {
            entity.setSubject(body.getSubject());
        }

        if (body.getMessage() != null) {
            entity.setMessage(body.getMessage());
        }

        if (body.getSentAt() != null) {
            entity.setSentAt(body.getSentAt());
        }

        Notification updated =
                mapper.entityToApi(repository.save(entity));

        updated.setServiceAddress(serviceUtil.getServiceAddress());

        return updated;
    }

    @Override
    public Void deleteNotification(Integer id) {

        LOG.debug("deleteNotification: {}", id);

        if (id == null || id < 1) {
            throw new InvalidInputException("Nevalidan ID: " + id);
        }

        NotificationEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Notification ne postoji: " + id));

        repository.delete(entity);

        return null;
    }
}
