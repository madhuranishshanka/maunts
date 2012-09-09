package com.devspace.reservation.domain;

import com.devspace.persistence.domain.Entity;

import javax.persistence.*;
import java.util.Date;


/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 8/26/12
 * Time: 8:00 AM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Entity
public class Reservation extends Entity {

    private String number;
    @OneToOne(cascade = CascadeType.MERGE)
    private Room room;
    @OneToOne(cascade = {CascadeType.ALL})
    private ReservationStatus reservationStatus;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Guest guest;
    private Long billingAccountId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkInDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkOutDate;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Long getBillingAccountId() {
        return billingAccountId;
    }

    public void setBillingAccountId(Long billingAccountId) {
        this.billingAccountId = billingAccountId;
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
}
