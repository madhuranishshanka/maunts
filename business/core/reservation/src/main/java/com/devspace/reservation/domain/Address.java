package com.devspace.reservation.domain;

import com.devspace.persistence.domain.Entity;
import javax.persistence.OneToOne;

/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 8/26/12
 * Time: 7:16 AM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Entity
public class Address extends Entity {

    private String number;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    @OneToOne
    private Country country;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
