package com.restaurant.restaurant_service.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.api.restaurant.Restaurant;
import com.restaurant.api.restaurant.RestaurantService;
import com.restaurant.restaurant_service.persistence.RestaurantEntity;
import com.restaurant.restaurant_service.persistence.RestaurantRepository;
import com.restaurant.util.exceptions.InvalidInputException;
import com.restaurant.util.exceptions.NotFoundException;
import com.restaurant.util.http.ServiceUtil;

@RestController("/restaurants")
public class RestaurantServiceImpl implements RestaurantService {

	private static final Logger LOG = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    private final ServiceUtil serviceUtil;
    private final RestaurantRepository repository;
    private final RestaurantMapper mapper;

    public RestaurantServiceImpl(RestaurantRepository repository,
                                 RestaurantMapper mapper,
                                 ServiceUtil serviceUtil) {

        this.repository = repository;
        this.mapper = mapper;
        this.serviceUtil = serviceUtil;
    }

    @Override
    public Restaurant getRestaurant(Integer id) {

        LOG.debug("getRestaurant: traži restoran sa ID: {}", id);

        if (id == null || id < 1) {
            throw new InvalidInputException("Nevalidan ID restorana: " + id);
        }

        RestaurantEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Restaurant nije pronađen: " + id));

        Restaurant restaurant = mapper.entityToApi(entity);
        restaurant.setServiceAddress(serviceUtil.getServiceAddress());

        return restaurant;
    }

    @Override
    public Restaurant getRestaurantByName(String name) {

        LOG.debug("getRestaurantByName: traži restoran sa nazivom: {}", name);

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("Naziv restorana ne sme biti prazan");
        }

        RestaurantEntity entity = repository.findByName(name)
                .orElseThrow(() ->
                        new NotFoundException("Restaurant nije pronađen: " + name));

        Restaurant restaurant = mapper.entityToApi(entity);
        restaurant.setServiceAddress(serviceUtil.getServiceAddress());

        return restaurant;
    }

    @Override
    public Restaurant createRestaurant(Restaurant body) {

        LOG.debug("createRestaurant: kreiranje restorana: {}", body.getName());

        if (body.getName() == null || body.getName().trim().isEmpty()) {
            throw new InvalidInputException("Naziv restorana je obavezan");
        }

        try {

            RestaurantEntity entity = mapper.apiToEntity(body);
            RestaurantEntity saved = repository.save(entity);

            Restaurant restaurant = mapper.entityToApi(saved);
            restaurant.setServiceAddress(serviceUtil.getServiceAddress());

            return restaurant;

        } catch (DataIntegrityViolationException ex) {
            throw new InvalidInputException("Restoran već postoji: " + body.getName());
        }
    }

    @Override
    public Void deleteRestaurant(Integer id) {

        LOG.debug("deleteRestaurant: pokušaj brisanja restorana sa ID: {}", id);

        if (id == null || id < 1) {
            throw new InvalidInputException("Nevalidan ID restorana: " + id);
        }

        RestaurantEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Restaurant ne postoji: " + id));

        repository.delete(entity);

        return null;
    }

    @Override
    public Restaurant updateRestaurant(Integer id, Restaurant body) {

        LOG.debug("updateRestaurant: ažuriranje restorana sa ID: {}", id);

        if (id == null || id < 1) {
            throw new InvalidInputException("Nevalidan ID restorana: " + id);
        }

        RestaurantEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Restaurant ne postoji: " + id));

        if (body.getName() != null) {
            entity.setName(body.getName());
        }

        if (body.getLocation() != null) {
            entity.setLocation(body.getLocation());
        }

        if (body.getPhoneNumber() != null) {
            entity.setPhoneNumber(body.getPhoneNumber());
        }

        try {

            Restaurant updated = mapper.entityToApi(repository.save(entity));
            updated.setServiceAddress(serviceUtil.getServiceAddress());

            return updated;

        } catch (DataIntegrityViolationException ex) {
            throw new InvalidInputException("Restaurant već postoji: " + body.getName());
        }
    }
	    
}
