package com.devspace.billing.invoice.domain;

import com.devspace.billing.payment.domain.Payment;
import com.devspace.billing.common.domain.Amount;
import com.devspace.commons.common.exception.MissingMandatoryParamException;
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
    @OneToMany
    private Set<InvoiceItem> invoiceItems = new HashSet<InvoiceItem>();
    @OneToMany
    private Set<Payment> payments = new HashSet<Payment>();
    private Amount totalGrossAmount;
    private Amount totalNetAmount;

    public Invoice() {
    }

    public Invoice(long userId, long orderId, Date invoiceCreatedDate, Set<InvoiceItem> invoiceItems) throws
            MissingMandatoryParamException {
        validateInvoice(invoiceCreatedDate);
        this.userId = userId;
        this.orderId = orderId;
        this.invoiceCreatedDate = invoiceCreatedDate;
        this.invoiceItems = invoiceItems;
        adjustTotals(Collections.list(Collections.enumeration(invoiceItems)));
    }

    public void addInvoiceItem(InvoiceItem invoiceItem) {
        this.invoiceItems.add(invoiceItem);
        adjustTotals(Arrays.asList(invoiceItem));
    }

    private void validateInvoice(Date invoiceCreatedDate) throws MissingMandatoryParamException {
        if(invoiceCreatedDate == null){
            throw new MissingMandatoryParamException("Missing invoice creation date");
        }
    }

    private void adjustTotals(List<InvoiceItem> invoiceItems) {
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
