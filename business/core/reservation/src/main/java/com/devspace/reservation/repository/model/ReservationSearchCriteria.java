package com.devspace.reservation.repository.model;

import com.devspace.reservation.domain.ReservationStatus;

import java.util.Date;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public class ReservationSearchCriteria {

    private String roomNumber;
    private Date checkInDate;
    private Date checkOutDate;
    private ReservationStatus.Status status;

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public ReservationStatus.Status getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus.Status status) {
        this.status = status;
    }
}
