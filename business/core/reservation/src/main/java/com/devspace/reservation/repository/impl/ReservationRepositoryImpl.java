package com.devspace.reservation.repository.impl;

import com.devspace.reservation.domain.Reservation;
import com.devspace.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 8/26/12
 * Time: 8:51 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("reservationRepository")
public class ReservationRepositoryImpl extends BaseRepositoryImpl<Reservation> implements ReservationRepository {

    @Override
    public Class<Reservation> getClassType() {
        return Reservation.class;
    }
}
