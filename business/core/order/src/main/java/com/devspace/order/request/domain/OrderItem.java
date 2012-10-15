package com.devspace.order.request.domain;

import com.devspace.persistence.domain.Entity;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class OrderItem extends Entity{

    private String productExternalId;
    private double quantity;
    private String paymentInstrumentExternalId;

    public OrderItem() {
    }

    public OrderItem(double quantity, String productExternalId) {
        this.quantity = quantity;
        this.productExternalId = productExternalId;
    }

    public OrderItem(String productExternalId, double quantity, String paymentInstrumentExternalId) {
        this.productExternalId = productExternalId;
        this.quantity = quantity;
        this.paymentInstrumentExternalId = paymentInstrumentExternalId;
    }

    public String getProductExternalId() {
        return productExternalId;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getPaymentInstrumentExternalId() {
        return paymentInstrumentExternalId;
    }
}
