package com.devspace.reservation.repository.impl;

import com.devspace.reservation.domain.Reservation;
import com.devspace.reservation.domain.ReservationStatus;
import com.devspace.reservation.domain.Room;
import com.devspace.reservation.repository.ReservationRepository;
import com.devspace.reservation.repository.model.ReservationSearchCriteria;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 8/26/12
 * Time: 8:51 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("reservationRepository")
public class ReservationRepositoryImpl extends BaseRepositoryImpl<Reservation> implements ReservationRepository {

    @Override
    public Class<Reservation> getClassType() {
        return Reservation.class;
    }

    public List<Reservation> findReservations(ReservationSearchCriteria criteria) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Reservation> reservationQuery = criteriaBuilder.createQuery(Reservation.class);
        Root<Reservation> reservation = reservationQuery.from(Reservation.class);
        reservationQuery.select(reservation);

        List<Predicate> reservationPredicates = new ArrayList<Predicate>();
        if (criteria.getCheckInDate() != null) {
            Predicate checkInDatePredicate = criteriaBuilder.greaterThanOrEqualTo(reservation.<Date>get("checkInDate"),
                    criteria.getCheckInDate());
            reservationPredicates.add(checkInDatePredicate);
        }

        if (criteria.getCheckOutDate() != null) {
            Predicate checkOutDatePredicate = criteriaBuilder.lessThanOrEqualTo(reservation.<Date>get("checkOutDate"),
                    criteria.getCheckOutDate());
            reservationPredicates.add(checkOutDatePredicate);
        }

        if (criteria.getRoomNumber() != null) {

            Path<Room> roomPath = reservation.get("room");
            Predicate roomNumberPredicate = criteriaBuilder.equal(roomPath.get("number"), criteria.getRoomNumber());
            reservationPredicates.add(roomNumberPredicate);
        }

        if (criteria.getStatus() != null) {

            Path<ReservationStatus> reservationStatusPath = reservation.get("reservationStatus");
            Predicate statusPredicate = criteriaBuilder.equal(reservationStatusPath.get("status"), criteria.getStatus());
            reservationPredicates.add(statusPredicate);
        }

        if (reservationPredicates.size() > 0) {
            reservationQuery.where(reservationPredicates.toArray(new Predicate[reservationPredicates.size()]));
        }

        TypedQuery<Reservation> typedQuery = getEntityManager().createQuery(reservationQuery);
        return typedQuery.getResultList();
    }
}
