package com.restaurant.review_service.services;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.restaurant.api.review.Review;
import com.restaurant.review_service.persistence.ReviewEntity;


@Mapper(componentModel = "spring")
public interface ReviewMapper {

	@Mapping(target = "serviceAddress", ignore = true)
    Review entityToApi(ReviewEntity entity);

	List<Review> entityListToApiList(List<ReviewEntity> entities);
	
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    ReviewEntity apiToEntity(Review api);
	
}
