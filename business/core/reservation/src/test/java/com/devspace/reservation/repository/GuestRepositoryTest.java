package com.devspace.reservation.repository;

import com.devspace.multitenancy.domain.TenantContext;
import com.devspace.persistence.domain.Entity;
import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.reservation.domain.ActiveStatus;
import com.devspace.reservation.domain.Address;
import com.devspace.reservation.domain.Country;
import com.devspace.reservation.domain.Guest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 8/26/12
 * Time: 11:33 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:reservation-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class GuestRepositoryTest {

    @Resource(name = "countryRepository")
    private CountryRepository countryRepository;
    @Resource(name = "guestRepository")
    private GuestRepository guestRepository;

    @Rollback
    @Test
    public void testGuestRepository() {

        TenantContext.setTenant("Tenant2");
        Entity persistedGuest = null;

        Country country = createDummyCountry();
        countryRepository.save(country);

        Address address = createDummyAddress(country);
        Guest guest = createDummyGuest(address);
        guestRepository.save(guest);

        try {
            persistedGuest = guestRepository.findById(guest.getId());
            assertNotNull(persistedGuest);
            assertTrue("Un-matching guest ", guest.equals(persistedGuest));
        } catch (EntityNotFoundException e) {
            assertFalse("Exception " + e.getMessage(), true);
        }

    }

    private Country createDummyCountry() {
        Country country = new Country();
        country.setCountryName("Sri Lanka");
        country.setActiveStatus(ActiveStatus.ACTIVE);
        return country;
    }

    private Address createDummyAddress(Country country) {
        Address address = new Address();
        address.setNumber("100");
        address.setStreet("Galle road");
        address.setCity("Colombo");
        address.setState("western");
        address.setCountry(country);
        return address;
    }

    private Guest createDummyGuest(Address address) {
        Guest guest = new Guest();
        guest.setFirstName("Madhura");
        guest.setLastName("Nishshanka");
        guest.setActiveStatus(ActiveStatus.ACTIVE);
        guest.setEmail("madhura@gmail.com");
        guest.setPhoneNumber("01122345345");
        guest.setAddress(address);
        guest.setPassportNumber("1234242V");
        return guest;
    }
}
