package com.restaurant.reservation_service.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.restaurant.api.reservation.Reservation;
import com.restaurant.reservation_service.persistence.ReservationEntity;


@Mapper(componentModel = "spring")
public interface ReservationMapper {

	@Mapping(target = "serviceAddress", ignore = true)
    Reservation entityToApi(ReservationEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    ReservationEntity apiToEntity(Reservation api);
    
}
