package com.devspace.reservation.domain;

import com.devspace.persistence.domain.Entity;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 8/26/12
 * Time: 12:01 PM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Entity
public class ReservationStatusHistory extends Entity {

    @Temporal(TemporalType.DATE)
    private Date createdDate;
    private boolean currentStatus;
    private ReservationStatus reservationStatus;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(boolean currentStatus) {
        this.currentStatus = currentStatus;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}
