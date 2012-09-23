package com.devspace.billing.payment.service;

import com.devspace.billing.invoice.domain.Invoice;
import com.devspace.billing.payment.domain.Payment;
import com.devspace.billing.payment.domain.PaymentItem;
import com.devspace.billing.payment.exceptioin.InvalidPaymentItemException;
import com.devspace.billing.payment.exceptioin.PaymentNotFoundException;
import com.devspace.commons.common.exception.MissingMandatoryParamException;
import com.devspace.persistence.exception.EntityNotFoundException;

import java.util.List;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface PaymentService {

    Payment createPayment(long partnerId, Invoice invoice, List<PaymentItem> paymentItems) throws
            InvalidPaymentItemException, MissingMandatoryParamException;

    Payment getPaymentById(long paymentId) throws PaymentNotFoundException;

    void addPaymentItem(long paymentId, PaymentItem paymentItem) throws EntityNotFoundException, InvalidPaymentItemException;
}
