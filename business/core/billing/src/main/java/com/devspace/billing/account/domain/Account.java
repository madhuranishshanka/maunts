package com.devspace.billing.account.domain;

import com.devspace.commons.common.exception.MissingMandatoryParamException;
import com.devspace.persistence.domain.Entity;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class Account extends Entity {

    private PaymentInstrument paymentInstrument;
    private long ownerId;
    private Category category;

    public enum Category {
        PARTNER, USER
    }

    public Account(PaymentInstrument paymentInstrument, long ownerId,
                   Category category) throws MissingMandatoryParamException {
        validateAccount(paymentInstrument, category);
        this.paymentInstrument = paymentInstrument;
        this.ownerId = ownerId;
        this.category = category;
    }

    private void validateAccount(PaymentInstrument paymentInstrument,
                                 Category category) throws MissingMandatoryParamException {
        if (paymentInstrument == null) {
            throw new MissingMandatoryParamException("Missing paymentInstrument");
        }
        if (category == null) {
            throw new MissingMandatoryParamException("Missing category");
        }
    }


    public Account(PaymentInstrument paymentInstrument) {
        this.paymentInstrument = paymentInstrument;
    }

    public PaymentInstrument getPaymentInstrument() {
        return paymentInstrument;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public Category getCategory() {
        return category;
    }
}
