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

import java.util.HashSet;
import java.util.Set;
import java.util.Date;
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
public class ReservationTest {

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
    public void testReservationCrud() {

        TenantContext.setTenant("Tenant2");
        Entity persistedRoomType = null;

        RoomType roomType = new RoomType();
        roomType.setRoomTypeName("king");
        roomType.setDescription("king roomtype has 1 bed");
        roomType.setRoomTypeImgOne("image1.png");
        roomType.setRoomTypeImgTwo("image2.png");
        roomType.setRoomTypeImgThere("image3.png");

        Room room=new Room();
        room.setRoomNumber("002");
        room.setRoomStatus(RoomStatus.AVAILABLE);
        room.setRoomType(roomType);

        roomTypeRepository.save(roomType);
        roomRepository.save(room);

        Set<Room> roomsSet=new HashSet<Room>();
        roomsSet.add(room);


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
        guest.setPhoneNo("01122345345");
        guest.setAddress(address);
        guest.setPassportNo("1234242V");

        guestRepository.save(guest);


        Entity persistedReservation = null;

        ReservationStatusHistory reservationStatusHistory=new  ReservationStatusHistory();
        reservationStatusHistory.setCreatedDate(new Date());
        reservationStatusHistory.setCurrentStatus(true);
        reservationStatusHistory.setReservationStatus(ReservationStatus.RESERVED);

        Set<ReservationStatusHistory> reservationStatusHistorySet=new HashSet<ReservationStatusHistory>();

        Reservation reservation=new Reservation();
        reservation.setGuest(guest);
        Date checkInDate=new Date();
        Date checkOutDate=new Date();
        reservation.setCheckInDate(checkInDate);
        reservation.setCheckOutDate(checkOutDate);
        reservation.setPaymentInstrumentId(0112324L);
        reservation.setRooms(roomsSet);
        reservation.setReservationStatusHistory(reservationStatusHistorySet);

        reservationRepository.save(reservation);

        try {
            persistedReservation = reservationRepository.findById(reservation.getId());
            assertNotNull(persistedReservation);
            assertTrue("Un-matching reservation ", reservation.equals(persistedReservation));
        } catch (EntityNotFoundException e) {
            assertFalse("Exception " + e.getMessage(), true);
        }



    }
}
