package com.devspace.billing.account.exceptioin;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public class AccountNotFoundException extends Exception{

    public AccountNotFoundException(String message) {
        super(message);
    }
}
