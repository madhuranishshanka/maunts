package com.devspace.billing.accont.domain;

import com.devspace.persistence.domain.Entity;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class PaymentInstrument extends Entity {

    private Type type;

    public enum Type{
        CASH,CREDIT_CARD,DEBIT_CARD
    }
}
