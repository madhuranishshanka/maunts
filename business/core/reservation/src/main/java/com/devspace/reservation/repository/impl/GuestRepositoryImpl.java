package com.devspace.reservation.repository.impl;

import com.devspace.reservation.domain.Guest;
import com.devspace.reservation.repository.GuestRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 8/26/12
 * Time: 8:37 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("guestRepository")
public class GuestRepositoryImpl extends BaseRepositoryImpl<Guest> implements GuestRepository {

    @Override
    public Class<Guest> getClassType() {
        return Guest.class;
    }
}
