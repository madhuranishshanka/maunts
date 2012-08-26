package com.devspace.reservation.repository.impl;

import com.devspace.persistence.domain.Entity;
import com.devspace.reservation.domain.Country;
import com.devspace.reservation.domain.Room;
import com.devspace.reservation.repository.CountryRepository;
import com.devspace.reservation.repository.RoomRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 8/26/12
 * Time: 9:32 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("countryRepository")
public class CountryRepositoryImpl<T extends Entity> extends BaseRepositoryImpl<T> implements CountryRepository<T> {

    public Class<T> getClassType() {
        return (Class<T>) Country.class;
    }
}
