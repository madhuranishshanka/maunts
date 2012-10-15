package com.devspace.order.request.exception;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public class OrderNotFoundException extends Exception{

    public OrderNotFoundException(String message) {
        super(message);
    }
}
