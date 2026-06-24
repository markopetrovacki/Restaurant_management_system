package com.restaurant.user_service.services;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.restaurant.api.user.User;
import com.restaurant.user_service.persistence.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
    @Mapping(target = "serviceAddress", ignore = true)
    User entityToApi(UserEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    UserEntity apiToEntity(User api);
	
}
