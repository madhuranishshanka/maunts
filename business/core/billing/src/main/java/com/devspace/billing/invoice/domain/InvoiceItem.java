package com.devspace.billing.invoice.domain;

import com.devspace.billing.common.domain.Amount;
import com.devspace.billing.invoice.exception.InvalidInvoiceItemException;
import com.devspace.persistence.domain.Entity;

import java.util.Calendar;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class InvoiceItem extends Entity {

    private static final int MINIMUM_ALLOWED_QUANTITY = 0;
    private long orderItemId;
    private long productId;
    private String productDisplayName;
    private double quantity;
    private Amount unitPrice;
    private Amount grossAmount;
    private Amount netAmount;
    private InvoiceItemStatus status;

    public InvoiceItem() {
    }

    public InvoiceItem(long productId, long orderItemId, String productDisplayName, double quantity, Amount unitPrice,
                       Amount grossAmount, Amount netAmount) throws InvalidInvoiceItemException {
        validateInvoiceItem(unitPrice, grossAmount, netAmount, quantity);
        this.productId = productId;
        this.productDisplayName = productDisplayName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.grossAmount = grossAmount;
        this.netAmount = netAmount;
        this.orderItemId = orderItemId;
        this.status = new InvoiceItemStatus(InvoiceItemStatus.Status.NOT_PAID, Calendar.getInstance().getTime(), null);
    }

    public void markAsPaid() {
        InvoiceItemStatus invoiceItemStatus = new InvoiceItemStatus(InvoiceItemStatus.Status.PAID,
                Calendar.getInstance().getTime(), this.status);
        this.status = invoiceItemStatus;
    }

    private void validateInvoiceItem(Amount unitPrice, Amount grossAmount, Amount netAmount,
                                     double quantity) throws InvalidInvoiceItemException {
        if (unitPrice == null) {
            throw new InvalidInvoiceItemException("Missing unit price");
        }
        if (grossAmount == null) {
            throw new InvalidInvoiceItemException("Missing gross amount");
        }
        if (netAmount == null) {
            throw new InvalidInvoiceItemException("Missing net amount");
        }
        if (quantity <= MINIMUM_ALLOWED_QUANTITY) {
            throw new InvalidInvoiceItemException("Incorrect quantity");
        }

    }

    public long getOrderItemId() {
        return orderItemId;
    }

    public InvoiceItemStatus getStatus() {
        return status;
    }

    public long getProductId() {
        return productId;
    }

    public String getProductDisplayName() {
        return productDisplayName;
    }

    public double getQuantity() {
        return quantity;
    }

    public Amount getUnitPrice() {
        return unitPrice;
    }

    public Amount getGrossAmount() {
        return grossAmount;
    }

    public Amount getNetAmount() {
        return netAmount;
    }
}
