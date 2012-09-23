package com.devspace.billing.payment.exceptioin;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public class PaymentNotFoundException extends Exception{

    public PaymentNotFoundException(String message) {
        super(message);
    }
}
