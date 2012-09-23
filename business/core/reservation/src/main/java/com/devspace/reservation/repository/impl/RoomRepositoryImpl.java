package com.devspace.reservation.repository.impl;

import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.reservation.domain.Reservation;
import com.devspace.reservation.domain.Room;
import com.devspace.reservation.repository.RoomRepository;
import com.devspace.reservation.repository.model.RoomSearchCriteria;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Repository("roomRepository")
public class RoomRepositoryImpl extends BaseRepositoryImpl<Room> implements RoomRepository {

    @Override
    public Class<Room> getClassType() {
        return Room.class;
    }

    public Room findByNumber(String number) throws EntityNotFoundException {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);

        Root<Room> room = criteriaQuery.from(Room.class);
        Predicate numberPredicate = criteriaBuilder.equal(room.get("number"), number);
        CriteriaQuery<Room> select = criteriaQuery.select(room).where(numberPredicate);

        TypedQuery<Room> query = getEntityManager().createQuery(select);
        Room singleResult = query.getSingleResult();

        if (singleResult == null) {
            throw new EntityNotFoundException(Room.class, "No room found for the given room number: " + number);
        }
        return singleResult;
    }

    public List<Room> findRooms(RoomSearchCriteria criteria) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Room> roomQuery = criteriaBuilder.createQuery(Room.class);
        Root<Room> room = roomQuery.from(Room.class);
        roomQuery.select(room);

        List<Predicate> roomPredicates = new ArrayList<Predicate>();
        if (criteria.getCheckInDate() != null) {

            Path<Reservation> reservationPath = room.get("activeReservations");
            Predicate roomNumberPredicate = criteriaBuilder.equal(reservationPath.<Date>get("checkInDate"),
                    criteria.getCheckInDate());
            roomPredicates.add(roomNumberPredicate);

        }

//        if (criteria.getCheckOutDate() != null) {
//            Predicate checkOutDatePredicate = criteriaBuilder.lessThanOrEqualTo(room.<Date>get("checkOutDate"),
//                    criteria.getCheckOutDate());
//            roomPredicates.add(checkOutDatePredicate);
//        }
//
//        if (criteria.getRoomType() != null) {
//
//            Path<Room> roomType = room.get("room");
//            Predicate roomNumberPredicate = criteriaBuilder.equal(roomType.get("number"), criteria.getRoomNumber());
//            roomPredicates.add(roomNumberPredicate);
//        }
//
//        if (criteria.getStatus() != null) {
//
//            Path<ReservationStatus> reservationStatus = room.get("reservationStatus");
//            Predicate statusPredicate = criteriaBuilder.equal(reservationStatus.get("status"), criteria.getStatus());
//            roomPredicates.add(statusPredicate);
//        }
//
//        if (roomPredicates.size() > 0) {
//            roomQuery.where(roomPredicates.toArray(new Predicate[roomPredicates.size()]));
//        }

        TypedQuery<Room> typedQuery = getEntityManager().createQuery(roomQuery);
        return typedQuery.getResultList();
    }
}
