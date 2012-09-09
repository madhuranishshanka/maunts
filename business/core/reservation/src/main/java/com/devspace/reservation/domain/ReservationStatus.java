package com.devspace.reservation.domain;

import com.devspace.persistence.domain.Entity;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
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
public class ReservationStatus extends Entity {

    private Status status;
    @Temporal(TemporalType.DATE)
    private Date changedDate;
    private String comment;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private ReservationStatus previousReservationStatus;

    public enum Status {
        RESERVED,
        CANCELLED,
        CHECKED_IN,
        CHECKED_OUT;
    }

    public Date getChangedDate() {
        return changedDate;
    }

    public void setChangedDate(Date changedDate) {
        this.changedDate = changedDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ReservationStatus getPreviousReservationStatus() {
        return previousReservationStatus;
    }

    public void setPreviousReservationStatus(ReservationStatus previousReservationStatus) {
        this.previousReservationStatus = previousReservationStatus;
    }
}
