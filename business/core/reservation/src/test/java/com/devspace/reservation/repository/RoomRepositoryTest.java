package com.devspace.reservation.repository;

import com.devspace.multitenancy.domain.TenantContext;
import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.reservation.domain.Room;
import com.devspace.reservation.domain.RoomStatus;
import com.devspace.reservation.domain.RoomType;
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
 * Time: 4:52 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:reservation-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class RoomRepositoryTest {

    @Resource(name = "roomRepository")
    private RoomRepository roomRepository;
    @Resource(name = "roomTypeRepository")
    private RoomTypeRepository roomTypeRepository;
    @Resource(name = "reservationRepository")
    private ReservationRepository reservationRepository;

    @Rollback
    @Test
    public void testRoomRepository() {

        TenantContext.setTenant("Tenant2");
        RoomType persistedRoomType = null;
        Room persistedRoom = null;

        RoomType roomType = createDummyRoomType();
        roomTypeRepository.save(roomType);

        try {
            persistedRoomType = roomTypeRepository.findById(roomType.getId());
            assertNotNull(persistedRoomType);
            assertTrue("Un-matching room types ", roomType.equals(persistedRoomType));
        } catch (EntityNotFoundException e) {
            assertFalse("Exception " + e.getMessage(), true);
        }

        Room room = new Room("001", RoomStatus.AVAILABLE, roomType);
        roomRepository.save(room);

        try {
            persistedRoom = roomRepository.findById(roomType.getId());
            assertNotNull(persistedRoom);
            assertTrue("Un-matching rooms ", room.equals(persistedRoom));
        } catch (EntityNotFoundException e) {
            assertFalse("Exception " + e.getMessage(), true);
        }



    }

    private RoomType createDummyRoomType() {
        RoomType roomType = new RoomType();
        roomType.setName("double");
        roomType.setDescription("double roomtype has 1 bed");
        roomType.setImgOne("image1.png");
        roomType.setImgTwo("image2.png");
        roomType.setImgThere("image3.png");
        return roomType;
    }

}
