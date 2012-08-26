package com.devspace.reservation.domain;

import com.devspace.persistence.domain.Entity;
import javax.persistence.OneToOne;

import java.security.PrivateKey;

/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 8/26/12
 * Time: 7:33 AM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Entity
public class Hotel extends Entity {

    private static final long serialVersionUID = 1L;

    private String hotelName;
    @OneToOne
    private Address address;
    private String phoneNo;
    private String fax;
    private String email;
    private String logo;

    public Hotel() {
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
