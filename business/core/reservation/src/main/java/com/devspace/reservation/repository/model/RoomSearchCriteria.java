package com.devspace.reservation.repository.model;

import com.devspace.reservation.domain.RoomType;

import java.util.Date;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public class RoomSearchCriteria {

    private Date checkInDate;
    private Date checkOutDate;
    private RoomType roomType;

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

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
