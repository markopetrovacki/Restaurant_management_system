package com.restaurant.review_service.services;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.api.review.Review;
import com.restaurant.api.review.ReviewService;
import com.restaurant.review_service.persistence.ReviewEntity;
import com.restaurant.review_service.persistence.ReviewRepository;
import com.restaurant.util.exceptions.InvalidInputException;
import com.restaurant.util.exceptions.NotFoundException;
import com.restaurant.util.http.ServiceUtil;

@RestController("/reviews")
public class ReviewServiceImpl implements ReviewService {

	private static final Logger LOG = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private final ServiceUtil serviceUtil;
    private final ReviewRepository repository;
    private final ReviewMapper mapper;

    public ReviewServiceImpl(
            ReviewRepository repository,
            ReviewMapper mapper,
            ServiceUtil serviceUtil) {

        this.repository = repository;
        this.mapper = mapper;
        this.serviceUtil = serviceUtil;
    }

    @Override
    public Review getReview(Integer id) {

        LOG.debug("getReview: traži recenziju sa ID: {}", id);

        if (id == null || id < 1) {
            throw new InvalidInputException("Nevalidan ID recenzije: " + id);
        }

        ReviewEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recenzija nije pronađena: " + id));

        Review review = mapper.entityToApi(entity);
        review.setServiceAddress(serviceUtil.getServiceAddress());

        return review;
    }

    @Override
    public List<Review> getReviews() {

        LOG.debug("getReviews: vraća sve recenzije");

        List<Review> reviews = mapper.entityListToApiList(repository.findAll());

        reviews.forEach(r -> r.setServiceAddress(serviceUtil.getServiceAddress()));

        return reviews;
    }

    @Override
    public List<Review> getReviewsByRestaurant(Integer restaurantId) {

        LOG.debug("getReviewsByRestaurant: restaurantId={}", restaurantId);

        if (restaurantId == null || restaurantId < 1) {
            throw new InvalidInputException("Nevalidan ID restorana: " + restaurantId);
        }

        List<Review> reviews = mapper.entityListToApiList(repository.findByRestaurantId(restaurantId));

        reviews.forEach(r -> r.setServiceAddress(serviceUtil.getServiceAddress()));

        return reviews;
    }

    @Override
    public List<Review> getReviewsByUser(String userEmail) {

        LOG.debug("getReviewsByUser: {}", userEmail);

        if (userEmail == null || userEmail.trim().isEmpty()) {
            throw new InvalidInputException("Email korisnika ne sme biti prazan.");
        }

        List<Review> reviews = mapper.entityListToApiList(repository.findByUserEmail(userEmail));

        reviews.forEach(r -> r.setServiceAddress(serviceUtil.getServiceAddress()));

        return reviews;
    }

    @Override
    public Review createReview(Review body) {

        LOG.debug("createReview: korisnik {}", body.getUserEmail());

        if (body.getUserEmail() == null || body.getUserEmail().trim().isEmpty()) {
            throw new InvalidInputException("Email korisnika je obavezan.");
        }

        if (body.getRestaurantId() == null || body.getRestaurantId() < 1) {
            throw new InvalidInputException("Nevalidan ID restorana.");
        }

        if (body.getRating() == null || body.getRating() < 1 || body.getRating() > 5) {
            throw new InvalidInputException("Ocena mora biti između 1 i 5.");
        }

        try {

            ReviewEntity entity = mapper.apiToEntity(body);

            if (entity.getReviewDate() == null) {
                entity.setReviewDate(LocalDate.now());
            }

            ReviewEntity saved = repository.save(entity);

            Review review = mapper.entityToApi(saved);
            review.setServiceAddress(serviceUtil.getServiceAddress());

            return review;

        } catch (DataIntegrityViolationException ex) {
            throw new InvalidInputException("Greška prilikom čuvanja recenzije.");
        }
    }

    @Override
    public Review updateReview(Integer id, Review body) {

        LOG.debug("updateReview: ažuriranje recenzije {}", id);

        if (id == null || id < 1) {
            throw new InvalidInputException("Nevalidan ID recenzije: " + id);
        }

        ReviewEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recenzija ne postoji: " + id));

        if (body.getUserEmail() != null) {
            entity.setUserEmail(body.getUserEmail());
        }

        if (body.getRestaurantId() != null) {
            entity.setRestaurantId(body.getRestaurantId());
        }

        if (body.getRating() != null) {
            entity.setRating(body.getRating());
        }

        if (body.getComment() != null) {
            entity.setComment(body.getComment());
        }

        if (body.getReviewDate() != null) {
            entity.setReviewDate(body.getReviewDate());
        }

        try {

            Review review = mapper.entityToApi(repository.save(entity));
            review.setServiceAddress(serviceUtil.getServiceAddress());

            return review;

        } catch (DataIntegrityViolationException ex) {
            throw new InvalidInputException("Greška prilikom ažuriranja recenzije.");
        }
    }

    @Override
    public Void deleteReview(Integer id) {

        LOG.debug("deleteReview: pokušaj brisanja recenzije {}", id);

        if (id == null || id < 1) {
            throw new InvalidInputException("Nevalidan ID recenzije: " + id);
        }

        ReviewEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recenzija ne postoji: " + id));

        repository.delete(entity);

        return null;
    }
}
