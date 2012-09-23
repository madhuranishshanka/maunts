package com.devspace.billing.accont.domain;

import com.devspace.persistence.domain.Entity;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class Account extends Entity{

    private PaymentInstrument paymentInstrument;

    public Account(PaymentInstrument paymentInstrument) {
        this.paymentInstrument = paymentInstrument;
    }

    public PaymentInstrument getPaymentInstrument() {
        return paymentInstrument;
    }
}
