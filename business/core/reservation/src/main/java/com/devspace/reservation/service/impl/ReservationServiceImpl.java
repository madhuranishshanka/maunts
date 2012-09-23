package com.devspace.reservation.service.impl;

import com.devspace.reservation.domain.*;
import com.devspace.reservation.exception.RoomAlreadyReservedException;
import com.devspace.reservation.exception.RoomNotFoundException;
import com.devspace.reservation.repository.ReservationRepository;
import com.devspace.reservation.repository.model.ReservationSearchCriteria;
import com.devspace.reservation.service.ReservationService;
import com.devspace.reservation.service.RoomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Service("reservationService")
public class ReservationServiceImpl implements ReservationService {

    @Resource(name = "roomService")
    private RoomService roomService;
    @Resource(name = "reservationRepository")
    private ReservationRepository reservationRepository;

    public void makeReservation(String reservationNumber, Guest guest, String roomNumber, Date fromDate,Date toDate,
                                long billingAccountId) throws RoomAlreadyReservedException,RoomNotFoundException {

        Room room = roomService.getRoomByRoomNumber(roomNumber);
        List<Reservation> activeReservations = getActiveReservations(roomNumber, fromDate, toDate);
        if (activeReservations.size() > 0) {
            throw new RoomAlreadyReservedException("Room with room number " + roomNumber + " is already reserved");
        }

        Reservation reservation = new Reservation(reservationNumber, room, guest, billingAccountId, fromDate,toDate);
        room.reserve(reservation);
        guest.getReservations().add(reservation);

        reservationRepository.save(reservation);
    }

    public void makeReservation(String reservationNumber, Guest guest, RoomType roomType,int numberOfRooms,
                                Date fromDate, Date toDate, long billingAccountId) {



    }

    public List<Reservation> getActiveReservations(String roomNumber, Date startingFrom, Date toDate) {
        ReservationSearchCriteria criteria = new ReservationSearchCriteria();
        criteria.setRoomNumber(roomNumber);
        criteria.setCheckInDate(startingFrom);
        criteria.setCheckOutDate(toDate);
        criteria.setStatus(ReservationStatus.Status.RESERVED);
        return reservationRepository.findReservations(criteria);
    }
}
