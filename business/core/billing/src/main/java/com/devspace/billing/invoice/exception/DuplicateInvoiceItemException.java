package com.devspace.billing.invoice.exception;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public class DuplicateInvoiceItemException extends Exception{

    public DuplicateInvoiceItemException(String message) {
        super(message);
    }
}
