package com.devspace.reservation.repository.impl;

import com.devspace.reservation.domain.Country;
import com.devspace.reservation.repository.CountryRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 8/26/12
 * Time: 9:32 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("countryRepository")
public class CountryRepositoryImpl extends BaseRepositoryImpl<Country> implements CountryRepository {

    @Override
    public Class<Country> getClassType() {
        return Country.class;
    }
}
