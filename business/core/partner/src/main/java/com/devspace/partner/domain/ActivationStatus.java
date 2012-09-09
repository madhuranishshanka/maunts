package com.devspace.partner.domain;

import com.devspace.persistence.domain.Entity;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */

@javax.persistence.Entity
public class ActivationStatus extends Entity {
    private Status status;
    @Temporal(TemporalType.DATE)
    private Date changedDate;
    private String reason;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private ActivationStatus previousActivationStatus;

    public enum Status {
        ACTIVE, INACTIVE
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getChangedDate() {
        return changedDate;
    }

    public void setChangedDate(Date changedDate) {
        this.changedDate = changedDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ActivationStatus getPreviousActivationStatus() {
        return previousActivationStatus;
    }

    public void setPreviousActivationStatus(ActivationStatus previousActivationStatus) {
        this.previousActivationStatus = previousActivationStatus;
    }
}
