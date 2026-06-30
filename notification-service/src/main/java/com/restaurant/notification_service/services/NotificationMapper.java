package com.restaurant.notification_service.services;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.restaurant.api.notification.Notification;
import com.restaurant.api.review.Review;
import com.restaurant.notification_service.persistence.NotificationEntity;


@Mapper(componentModel = "spring")
public interface NotificationMapper {

	@Mapping(target = "serviceAddress", ignore = true)
    Notification entityToApi(NotificationEntity entity);

	List<Notification> entityListToApiList(List<NotificationEntity> entities);
	
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    NotificationEntity apiToEntity(Notification api);
}
