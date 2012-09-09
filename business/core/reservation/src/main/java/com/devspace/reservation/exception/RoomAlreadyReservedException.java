package com.devspace.reservation.exception;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public class RoomAlreadyReservedException extends Exception{

    public RoomAlreadyReservedException(String message) {
        super(message);
    }
}
