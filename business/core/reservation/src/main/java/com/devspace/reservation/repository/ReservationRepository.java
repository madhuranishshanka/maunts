package com.devspace.reservation.repository;

import com.devspace.persistence.repository.CrudRepository;
import com.devspace.reservation.domain.Reservation;
import com.devspace.reservation.repository.model.ReservationSearchCriteria;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 8/26/12
 * Time: 8:51 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ReservationRepository extends CrudRepository<Reservation> {

    List<Reservation> findReservations(ReservationSearchCriteria criteria);
}
