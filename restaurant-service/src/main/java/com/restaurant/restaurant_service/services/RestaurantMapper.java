package com.restaurant.restaurant_service.services;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.restaurant.api.restaurant.Restaurant;
import com.restaurant.api.user.User;
import com.restaurant.restaurant_service.persistence.RestaurantEntity;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

	@Mapping(target = "serviceAddress", ignore = true)
    Restaurant entityToApi(RestaurantEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    RestaurantEntity apiToEntity(Restaurant api);
	
}
