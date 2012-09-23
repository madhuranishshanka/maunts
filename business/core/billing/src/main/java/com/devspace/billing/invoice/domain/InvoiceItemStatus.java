package com.devspace.billing.invoice.domain;

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
public class InvoiceItemStatus extends Entity {

    private Status status;
    @Temporal(TemporalType.DATE)
    private Date changedDate;
    private String comment;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private InvoiceItemStatus previousInvoiceItemStatus;

    public enum Status {
        PAID, NOT_PAID
    }

    public InvoiceItemStatus(Status status, Date changedDate, InvoiceItemStatus previousInvoiceItemStatus) {
        this.status = status;
        this.changedDate = changedDate;
        this.previousInvoiceItemStatus = previousInvoiceItemStatus;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Status getStatus() {
        return status;
    }

    public Date getChangedDate() {
        return changedDate;
    }

    public String getComment() {
        return comment;
    }

    public InvoiceItemStatus getPreviousInvoiceItemStatus() {
        return previousInvoiceItemStatus;
    }
}
