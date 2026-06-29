package com.restaurant.reservation_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.api.reservation.Reservation;
import com.restaurant.api.reservation.ReservationService;
import com.restaurant.reservation_service.persistence.ReservationEntity;
import com.restaurant.reservation_service.persistence.ReservationRepository;
import com.restaurant.util.exceptions.InvalidInputException;
import com.restaurant.util.exceptions.NotFoundException;
import com.restaurant.util.http.ServiceUtil;

@RestController("/reservations")
public class ReservationServiceImpl implements ReservationService  {

	private static final Logger LOG = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private final ServiceUtil serviceUtil;
    private final ReservationRepository repository;
    private final ReservationMapper mapper;

    public ReservationServiceImpl(ReservationRepository repository,
                                  ReservationMapper mapper,
                                  ServiceUtil serviceUtil) {

        this.repository = repository;
        this.mapper = mapper;
        this.serviceUtil = serviceUtil;
    }

    @Override
    public Reservation getReservation(Integer id) {

        LOG.debug("getReservation: traži rezervaciju sa ID: {}", id);

        if (id == null || id < 1) {
            throw new InvalidInputException("Nevalidan ID rezervacije: " + id);
        }

        ReservationEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Reservation nije pronađena: " + id));

        Reservation reservation = mapper.entityToApi(entity);
        reservation.setServiceAddress(serviceUtil.getServiceAddress());

        return reservation;
    }

    @Override
    public Reservation getReservationByReservationNumber(Integer reservationNumber) {

        LOG.debug("getReservationByReservationNumber: traži rezervaciju sa brojem: {}", reservationNumber);

        if (reservationNumber == null || reservationNumber < 1) {
            throw new InvalidInputException("Nevalidan broj rezervacije");
        }

        ReservationEntity entity = repository.findByReservationNumber(reservationNumber)
                .orElseThrow(() ->
                        new NotFoundException("Reservation nije pronađena: " + reservationNumber));

        Reservation reservation = mapper.entityToApi(entity);
        reservation.setServiceAddress(serviceUtil.getServiceAddress());

        return reservation;
    }

    @Override
    public Reservation createReservation(Reservation body) {

        LOG.debug("createReservation: kreiranje rezervacije broj: {}",
                body.getReservationNumber());

        if (body.getReservationNumber() == null) {
            throw new InvalidInputException("Broj rezervacije je obavezan");
        }

        try {

            ReservationEntity entity = mapper.apiToEntity(body);
            ReservationEntity saved = repository.save(entity);

            Reservation reservation = mapper.entityToApi(saved);
            reservation.setServiceAddress(serviceUtil.getServiceAddress());

            return reservation;

        } catch (DataIntegrityViolationException ex) {

            throw new InvalidInputException(
                    "Rezervacija već postoji: " + body.getReservationNumber());
        }
    }

    @Override
    public Void deleteReservation(Integer id) {

        LOG.debug("deleteReservation: pokušaj brisanja rezervacije sa ID: {}", id);

        if (id == null || id < 1) {
            throw new InvalidInputException("Nevalidan ID rezervacije: " + id);
        }

        ReservationEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Reservation ne postoji: " + id));

        repository.delete(entity);

        return null;
    }

    @Override
    public Reservation updateReservation(Integer id, Reservation body) {

        LOG.debug("updateReservation: ažuriranje rezervacije sa ID: {}", id);

        if (id == null || id < 1) {
            throw new InvalidInputException("Nevalidan ID rezervacije: " + id);
        }

        ReservationEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Reservation ne postoji: " + id));

        if (body.getUserEmail() != null) {
            entity.setUserEmail(body.getUserEmail());
        }

        if (body.getRestaurantId() != null) {
            entity.setRestaurantId(body.getRestaurantId());
        }

        if (body.getReservationNumber() != null) {
            entity.setReservationNumber(body.getReservationNumber());
        }

        if (body.getReservationTime() != null) {
            entity.setReservationTime(body.getReservationTime());
        }

        try {

            Reservation updated = mapper.entityToApi(repository.save(entity));
            updated.setServiceAddress(serviceUtil.getServiceAddress());

            return updated;

        } catch (DataIntegrityViolationException ex) {

            throw new InvalidInputException(
                    "Rezervacija već postoji: " + body.getReservationNumber());
        }
    }
}
