package com.devspace.reservation.service.impl;

import com.devspace.reservation.domain.Guest;
import com.devspace.reservation.domain.Reservation;
import com.devspace.reservation.domain.ReservationStatus;
import com.devspace.reservation.domain.Room;
import com.devspace.reservation.exception.RoomAlreadyReservedException;
import com.devspace.reservation.exception.RoomNotFoundException;
import com.devspace.reservation.repository.ReservationRepository;
import com.devspace.reservation.service.ReservationService;
import com.devspace.reservation.service.RoomService;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public class ReservationServiceImpl implements ReservationService {

    @Resource(name = "roomService")
    private RoomService roomService;
    @Resource(name = "reservationRepository")
    private ReservationRepository reservationRepository;

    public void makeReservation(String reservationNumber, Guest guest, String roomNumber, Date startingFrom,
                                Date toDate, long billingAccountId) throws RoomAlreadyReservedException,
            RoomNotFoundException {

        Room room = roomService.getRoomByRoomNumber(roomNumber);
        List<Reservation> activeReservations = getActiveReservations(roomNumber, startingFrom, toDate);
        if (activeReservations.size() > 0) {
            throw new RoomAlreadyReservedException("Room with room number " + roomNumber + " is already reserved");
        }

        ReservationStatus reservationStatus = new ReservationStatus();
        reservationStatus.setChangedDate(Calendar.getInstance().getTime());
        reservationStatus.setStatus(ReservationStatus.Status.RESERVED);

        Reservation reservation = new Reservation();
        reservation.setNumber(reservationNumber);
        reservation.setBillingAccountId(billingAccountId);
        reservation.setCheckInDate(startingFrom);
        reservation.setCheckOutDate(toDate);
        reservation.setGuest(guest);
        reservation.setReservationStatus(reservationStatus);
        reservation.setRoom(room);

        guest.getReservations().add(reservation);

        reservationRepository.save(reservation);
    }

    public List<Reservation> getActiveReservations(String roomNumber, Date startingFrom, Date toDate) {
        return null;
    }
}
