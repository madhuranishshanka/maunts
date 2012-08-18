package com.devspace.reservation.repository;

import com.devspace.multitenancy.domain.TenantContext;
import com.devspace.persistence.domain.Entity;
import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.reservation.domain.Room;
import com.devspace.reservation.domain.RoomStatus;
import com.devspace.reservation.domain.RoomType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:reservation-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class RoomRepositoryTest {


    @Resource(name = "roomRepository")
    private RoomRepository roomRepository;

    @Test
    public void testRoomCrud() {
        TenantContext.setTenant("Tenant1");
        Entity persistedRoom = null;
        RoomType roomType = new RoomType();
        roomType.setExternalId("Economy");
        roomType.setName("Economy class");

        Room room = new Room();
        room.setRoomNumber("number1");
        room.setRoomStatus(RoomStatus.AVAILABLE);
        room.setRoomType(roomType);

        roomRepository.save(room);
        try {
            persistedRoom = roomRepository.findById(room.getId());
            assertNotNull(persistedRoom);
            assertTrue("Un-matching rooms ", room.equals(persistedRoom));
        } catch (EntityNotFoundException e) {
            assertFalse("Exception " + e.getMessage(), true);
        }

        room.setRoomStatus(RoomStatus.NOT_AVAILABLE);
        room.getRoomType().setName("Updated Economy class");

        roomRepository.update(room);

        try {
            persistedRoom = roomRepository.findById(room.getId());
            assertNotNull(persistedRoom);
            assertTrue("Un-matching rooms ", room.equals(persistedRoom));
        } catch (EntityNotFoundException e) {
            assertFalse("Exception " + e.getMessage(), true);
        }


        try {
            roomRepository.delete(room.getId());
            persistedRoom = roomRepository.findById(room.getId());
            fail("Room object found");
        } catch (EntityNotFoundException e) {
        }
    }
}
