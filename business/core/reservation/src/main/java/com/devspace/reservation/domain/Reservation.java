package com.devspace.reservation.domain;

import com.devspace.persistence.domain.Entity;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;


/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 8/26/12
 * Time: 8:00 AM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Entity
public class Reservation extends Entity{

    private String number;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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

    public Reservation() {
    }

    public Reservation(String number, Room room, Guest guest, Long billingAccountId, Date checkInDate) {
        this(number, room, guest, billingAccountId, checkInDate, null);
    }

    public Reservation(String number, Room room, Guest guest, Long billingAccountId, Date checkInDate,
                       Date checkOutDate) {
        super();
        this.number = number;
        this.room = room;
        this.guest = guest;
        this.billingAccountId = billingAccountId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        ReservationStatus reservationStatus = new ReservationStatus();
        reservationStatus.setChangedDate(Calendar.getInstance().getTime());
        reservationStatus.setStatus(ReservationStatus.Status.RESERVED);
        this.reservationStatus = reservationStatus;
    }

    public String getNumber() {
        return number;
    }

    public Room getRoom() {
        return room;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public Guest getGuest() {
        return guest;
    }

    public Long getBillingAccountId() {
        return billingAccountId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
