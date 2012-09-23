package com.devspace.reservation.service;

import com.devspace.reservation.domain.Guest;
import com.devspace.reservation.domain.Reservation;
import com.devspace.reservation.domain.RoomType;
import com.devspace.reservation.exception.RoomAlreadyReservedException;
import com.devspace.reservation.exception.RoomNotFoundException;

import java.util.Date;
import java.util.List;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface ReservationService {

    void makeReservation(String reservationNumber, Guest guest, String roomNumber, Date fromDate, Date toDate,
                         long billingAccountId) throws RoomAlreadyReservedException, RoomNotFoundException;

    void makeReservation(String reservationNumber, Guest guest, RoomType roomType, int numberOfRooms, Date fromDate,
                         Date toDate, long billingAccountId);

    List<Reservation> getActiveReservations(String roomNumber, Date startingFrom, Date toDate);
}