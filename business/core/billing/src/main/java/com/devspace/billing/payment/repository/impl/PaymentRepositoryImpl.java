package com.devspace.billing.payment.repository.impl;

import com.devspace.billing.common.repository.impl.BaseRepositoryImpl;
import com.devspace.billing.payment.domain.Payment;
import com.devspace.billing.payment.repository.PaymentRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Repository("paymentRepository")
public class PaymentRepositoryImpl extends BaseRepositoryImpl<Payment> implements PaymentRepository{

    @Override
    public Class<Payment> getClassType() {
        return Payment.class;
    }

}
