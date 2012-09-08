package com.devspace.reservation.domain;

import com.devspace.persistence.domain.Entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 8/26/12
 * Time: 7:24 AM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Entity
public class Country extends Entity {

    private String countryName;
    @Enumerated(EnumType.STRING)
    private ActiveStatus activeStatus;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public ActiveStatus getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(ActiveStatus activeStatus) {
        this.activeStatus = activeStatus;
    }
}
