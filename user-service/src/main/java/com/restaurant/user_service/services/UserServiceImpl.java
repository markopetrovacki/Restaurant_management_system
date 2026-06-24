package com.restaurant.user_service.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.restaurant.api.user.User;
import com.restaurant.api.user.UserService;
import com.restaurant.user_service.persistence.UserEntity;
import com.restaurant.user_service.persistence.UserRepository;
import com.restaurant.util.exceptions.InvalidInputException;
import com.restaurant.util.exceptions.NotFoundException;
import com.restaurant.util.http.ServiceUtil;



@Service
public class UserServiceImpl implements UserService{

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    
    private final ServiceUtil serviceUtil;
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository repository, UserMapper mapper, ServiceUtil serviceUtil) {
        this.repository = repository;
        this.mapper = mapper;
        this.serviceUtil = serviceUtil;
    }

    @Override
    public User getUser(Integer id) {
        LOG.debug("getUser: traži korisnika sa ID: {}", id);
        
        if (id == null || id < 1) {
            throw new InvalidInputException("Nevalidan ID korisnika: " + id);
        }
        
        UserEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User nije pronađen: " + id));

        User user = mapper.entityToApi(entity);
        user.setServiceAddress(serviceUtil.getServiceAddress());
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        LOG.debug("getUserByEmail: traži korisnika sa email-om: {}", email);
        
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidInputException("Email ne sme biti prazan");
        }
        
        UserEntity entity = repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User nije pronađen: " + email));

        User user = mapper.entityToApi(entity);
        user.setServiceAddress(serviceUtil.getServiceAddress());
        return user;
    }

    @Override
    public User createUser(User body) {
        LOG.debug("createUser: kreiranje novog korisnika za email: {}", body.getEmail());

        if (body.getEmail() == null || body.getEmail().trim().isEmpty()) {
            throw new InvalidInputException("Email je obavezan za kreiranje korisnika");
        }

        try {
            UserEntity entity = mapper.apiToEntity(body);
            UserEntity saved = repository.save(entity);

            User user = mapper.entityToApi(saved);
            user.setServiceAddress(serviceUtil.getServiceAddress());
            return user;

        } catch (DataIntegrityViolationException ex) {
            throw new InvalidInputException("Email već postoji: " + body.getEmail());
        }
        
    }

    @Override
    public Void deleteUser(Integer id) {
        LOG.debug("deleteUser: pokušaj brisanja korisnika sa ID: {}", id);
        
        if (id == null || id < 1) {
            throw new InvalidInputException("Nevalidan ID korisnika: " + id);
        }

        UserEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User ne postoji: " + id));

        repository.delete(entity);
        return null;
    }

    @Override
    public User updateUser(Integer id, User body) {
        LOG.debug("updateUser: ažuriranje korisnika sa ID: {}", id);

        if (id == null || id < 1) {
            throw new InvalidInputException("Nevalidan ID korisnika: " + id);
        }

        UserEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User ne postoji: " + id));

        if (body.getEmail() != null) {
            entity.setEmail(body.getEmail());
        }
        if (body.getPassword() != null) {
            entity.setPassword(body.getPassword());
        }
        if (body.getRole() != null) {
            entity.setRole(body.getRole());
        }

        try {
            User updated = mapper.entityToApi(repository.save(entity));
            updated.setServiceAddress(serviceUtil.getServiceAddress());
            return updated;

        } catch (DataIntegrityViolationException ex) {
            throw new InvalidInputException("Email već postoji: " + body.getEmail());
        }
    }
}
