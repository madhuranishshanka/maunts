package com.devspace.billing.payment.service.impl;

import com.devspace.billing.invoice.domain.Invoice;
import com.devspace.billing.payment.domain.Payment;
import com.devspace.billing.payment.domain.PaymentItem;
import com.devspace.billing.payment.exceptioin.InvalidPaymentItemException;
import com.devspace.billing.payment.exceptioin.PaymentNotFoundException;
import com.devspace.billing.payment.repository.PaymentRepository;
import com.devspace.billing.payment.service.PaymentService;
import com.devspace.commons.common.exception.MissingMandatoryParamException;
import com.devspace.persistence.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

    @Resource(name = "paymentRepository")
    private PaymentRepository paymentRepository;

    public Payment createPayment(long partnerId, Invoice invoice, List<PaymentItem> paymentItems)
            throws InvalidPaymentItemException, MissingMandatoryParamException {

        Payment payment = new Payment(partnerId, invoice, new HashSet<PaymentItem>(paymentItems));
        paymentRepository.save(payment);

        return payment;
    }

    public Payment getPaymentById(long paymentId) throws PaymentNotFoundException {
        try {
            return paymentRepository.findById(paymentId);
        } catch (EntityNotFoundException e) {
            throw new PaymentNotFoundException("Payment not found for the id " + paymentId);
        }
    }

    public void addPaymentItem(long paymentId, PaymentItem paymentItem) throws EntityNotFoundException,
            InvalidPaymentItemException {

        Payment payment = paymentRepository.findById(paymentId);
        payment.addPaymentItem(paymentItem);
    }

}
