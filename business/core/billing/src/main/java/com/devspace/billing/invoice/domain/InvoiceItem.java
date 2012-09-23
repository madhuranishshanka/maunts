package com.devspace.billing.invoice.domain;

import com.devspace.commons.common.domain.Amount;
import com.devspace.persistence.domain.Entity;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class InvoiceItem extends Entity{

    private long productId;
    private String productDisplayName;
    private double quantity;
    private Amount unitPrice;
    private Amount grossAmount;
    private Amount netAmount;

    public InvoiceItem(long productId, String productDisplayName, double quantity, Amount unitPrice,
                       Amount grossAmount, Amount netAmount) {
        this.productId = productId;
        this.productDisplayName = productDisplayName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.grossAmount = grossAmount;
        this.netAmount = netAmount;
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
