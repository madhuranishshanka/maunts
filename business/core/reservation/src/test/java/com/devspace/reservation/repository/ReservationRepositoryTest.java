package com.devspace.reservation.repository;

import com.devspace.multitenancy.domain.TenantContext;
import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.reservation.domain.*;
import com.devspace.reservation.repository.model.ReservationSearchCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 8/26/12
 * Time: 9:07 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:reservation-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class ReservationRepositoryTest {

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

    @Rollback
    @Test
    public void testReservationRepository() {

        TenantContext.setTenant("Tenant2");

        RoomType roomType = createDymmyRoomType();
        roomTypeRepository.save(roomType);

        Room room = new Room("RM" + System.currentTimeMillis(), RoomStatus.AVAILABLE, roomType);
        roomRepository.save(room);

        Country country = populateDummyCountry();
        countryRepository.save(country);

        Address address = populateDummyAddress(country);
        Guest guest = populateDymmyGuest(address);
        guestRepository.save(guest);

        Reservation persistedReservation = null;

        Calendar calendar = Calendar.getInstance();
        Date checkInTime = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        Date checkOutTime = calendar.getTime();
        Reservation reservation1 = new Reservation("", room, guest, 0112324L, checkInTime, checkOutTime);
        room.reserve(reservation1);
        guest.getReservations().add(reservation1);

        reservationRepository.save(reservation1);

        try {
            persistedReservation = reservationRepository.findById(reservation1.getId());
            assertNotNull(persistedReservation);
            assertTrue("Un-matching reservation ", reservation1.equals(persistedReservation));
        } catch (EntityNotFoundException e) {
            assertFalse("Exception " + e.getMessage(), true);
        }

        ReservationSearchCriteria criteria = new ReservationSearchCriteria();
        criteria.setRoomNumber(reservation1.getRoom().getNumber());
        List<Reservation> reservations = reservationRepository.findReservations(criteria);
        assertNotNull(reservations);
        assertEquals(1, reservations.size());

        calendar.add(Calendar.DAY_OF_MONTH, 3);
        checkInTime = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        checkOutTime = calendar.getTime();
        Reservation reservation2 = new Reservation("", room, guest, 0112324L, checkInTime, checkOutTime);
        room.reserve(reservation2);
        guest.getReservations().add(reservation2);

        reservationRepository.save(reservation2);

        criteria = new ReservationSearchCriteria();
        criteria.setRoomNumber(reservation1.getRoom().getNumber());
        criteria.setCheckInDate(reservation1.getCheckInDate());
        criteria.setCheckOutDate(reservation1.getCheckOutDate());
        reservations = reservationRepository.findReservations(criteria);
        assertNotNull(reservations);
        assertEquals("Only 1 reservation should be returned", 1, reservations.size());
        Reservation reservation = reservations.get(0);
        assertNotNull(reservation);
        assertNotNull(reservation.getRoom());
        assertEquals("Invalid number of reservation for the room",2,reservation.getRoom().getActiveReservations().size());

        criteria = new ReservationSearchCriteria();
        criteria.setRoomNumber(reservation1.getRoom().getNumber());
        criteria.setCheckInDate(reservation1.getCheckInDate());
        criteria.setCheckOutDate(reservation2.getCheckOutDate());
        reservations = reservationRepository.findReservations(criteria);
        assertNotNull(reservations);
        assertEquals("Only 1 reservation should be returned", 2, reservations.size());

        criteria = new ReservationSearchCriteria();
        criteria.setRoomNumber(reservation1.getRoom().getNumber());
        criteria.setCheckInDate(reservation1.getCheckInDate());
        criteria.setCheckOutDate(reservation2.getCheckOutDate());
        criteria.setStatus(ReservationStatus.Status.CANCELLED);
        reservations = reservationRepository.findReservations(criteria);
        assertNotNull(reservations);
        assertEquals("No reservations should be returned", 0, reservations.size());
    }

    private Guest populateDymmyGuest(Address address) {
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

    private RoomType createDymmyRoomType() {
        RoomType roomType = new RoomType();
        roomType.setName("king");
        roomType.setDescription("king roomtype has 1 bed");
        roomType.setImgOne("image1.png");
        roomType.setImgTwo("image2.png");
        roomType.setImgThere("image3.png");
        return roomType;
    }

    private Country populateDummyCountry() {
        Country country = new Country();
        country.setCountryName("Sri Lanka");
        country.setActiveStatus(ActiveStatus.ACTIVE);
        return country;
    }

    private Address populateDummyAddress(Country country) {
        Address address = new Address();
        address.setNumber("100");
        address.setStreet("Galle road");
        address.setCity("Colombo");
        address.setState("western");
        address.setCountry(country);
        return address;
    }


}
