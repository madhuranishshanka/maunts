package com.devspace.billing.payment.exceptioin;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public class InvalidPaymentItemException extends Exception{

    public InvalidPaymentItemException(String message) {
        super(message);
    }
}
