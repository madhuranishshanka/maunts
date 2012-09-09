package com.devspace.reservation.repository;

import com.devspace.multitenancy.domain.TenantContext;
import com.devspace.persistence.domain.Entity;
import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.reservation.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
public class GuestTest {

    @Resource(name = "roomRepository")
    private RoomRepository roomRepository;
    @Resource(name = "roomTypeRepository")
    private RoomTypeRepository roomTypeRepository;
    @Resource(name = "countryRepository")
    private CountryRepository countryRepository;
    @Resource(name = "reservationRepository")
    private ReservationRepository reservationRepository;
    @Resource(name = "guestRepository")
    private GuestRepository guestRepository;

    @Test
    public void testGuestCrud() {

        TenantContext.setTenant("Tenant2");


        Entity persistedGuest = null;

        Country country=new Country();
        country.setCountryName("Sri Lanka");
        country.setActiveStatus(ActiveStatus.ACTIVE);
        countryRepository.save(country);

        Address address=new Address();
        address.setNumber("100");
        address.setStreet("Galle road");
        address.setCity("Colombo");
        address.setState("western");
        address.setCountry(country);

        Guest guest=new Guest();
        guest.setFirstName("Madhura");
        guest.setLastName("Nishshanka");
        guest.setActiveStatus(ActiveStatus.ACTIVE);
        guest.setEmail("madhura@gmail.com");
        guest.setPhoneNumber("01122345345");
        guest.setAddress(address);
        guest.setPassportNumber("1234242V");

        guestRepository.save(guest);

        try {
            persistedGuest = guestRepository.findById(guest.getId());
            assertNotNull(persistedGuest);
            assertTrue("Un-matching guest ", guest.equals(persistedGuest));
        } catch (EntityNotFoundException e) {
            assertFalse("Exception " + e.getMessage(), true);
        }



    }
}
