package com.devspace.reservation.domain;

import com.devspace.persistence.domain.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;


/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 8/26/12
 * Time: 8:00 AM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Entity
public class Reservation extends Entity {

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Room> rooms;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<ReservationStatusHistory> reservationStatusHistory;
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Guest guest;
    private Long paymentInstrumentId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkInDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkOutDate;

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Set<ReservationStatusHistory> getReservationStatusHistory() {
        return reservationStatusHistory;
    }

    public void setReservationStatusHistory(Set<ReservationStatusHistory> reservationStatusHistory) {
        this.reservationStatusHistory = reservationStatusHistory;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Long getPaymentInstrumentId() {
        return paymentInstrumentId;
    }

    public void setPaymentInstrumentId(Long paymentInstrumentId) {
        this.paymentInstrumentId = paymentInstrumentId;
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
