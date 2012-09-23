package com.devspace.billing.invoice.domain;

import com.devspace.billing.payment.domain.Payment;
import com.devspace.commons.common.domain.Amount;
import com.devspace.persistence.domain.Entity;

import javax.persistence.OneToMany;
import java.util.*;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class Invoice extends Entity {

    private long userId;
    private long orderId;
    private Date invoiceCreatedDate;
    private Status status;
    @OneToMany
    private Set<InvoiceItem> invoiceItems = new HashSet<InvoiceItem>();
    @OneToMany
    private Set<Payment> payments = new HashSet<Payment>();
    private Amount totalGrossAmount;
    private Amount totalNetAmount;

    public enum Status {
        PAID, NOT_PAID
    }

    public Invoice() {
    }

    public Invoice(long userId, long orderId, Date invoiceCreatedDate, Status status, Set<InvoiceItem> invoiceItems) {
        this.userId = userId;
        this.orderId = orderId;
        this.invoiceCreatedDate = invoiceCreatedDate;
        this.status = status;
        this.invoiceItems = invoiceItems;
        populateTotals(Collections.list(Collections.enumeration(invoiceItems)));
    }

    public void addInvoiceItem(InvoiceItem invoiceItem) {
        this.invoiceItems.add(invoiceItem);
        populateTotals(Arrays.asList(invoiceItem));
    }

    private void populateTotals(List<InvoiceItem> invoiceItems) {
        for (InvoiceItem invoiceItem : invoiceItems) {

            Amount grossAmount = invoiceItem.getGrossAmount();
            Amount netAmount = invoiceItem.getNetAmount();

            if (totalGrossAmount == null) {
                this.totalGrossAmount = new Amount(grossAmount.getValue(), grossAmount.getCurrencySymbol());
                this.totalNetAmount = new Amount(netAmount.getValue(), netAmount.getCurrencySymbol());
            } else {
                this.totalGrossAmount.add(grossAmount);
                this.totalNetAmount.add(netAmount);
            }
        }
    }

    public long getUserId() {
        return userId;
    }

    public long getOrderId() {
        return orderId;
    }

    public Date getInvoiceCreatedDate() {
        return invoiceCreatedDate;
    }

    public Status getStatus() {
        return status;
    }

    public Set<InvoiceItem> getInvoiceItems() {
        return Collections.unmodifiableSet(invoiceItems);
    }

    public Set<Payment> getPayments() {
        return Collections.unmodifiableSet(payments);
    }

    public Amount getTotalGrossAmount() {
        return totalGrossAmount;
    }

    public Amount getTotalNetAmount() {
        return totalNetAmount;
    }
}
