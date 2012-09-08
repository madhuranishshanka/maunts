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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
public class RoomTest {

    @Resource(name = "roomRepository")
    private RoomRepository roomRepository;
    @Resource(name = "roomTypeRepository")
    private RoomTypeRepository roomTypeRepository;

    @Test
    public void testRoomCrud() {

        TenantContext.setTenant("Tenant2");
        Entity persistedRoomType = null;

        RoomType roomType = new RoomType();
        roomType.setRoomTypeName("double");
        roomType.setDescription("double roomtype has 1 bed");
        roomType.setRoomTypeImgOne("image1.png");
        roomType.setRoomTypeImgTwo("image2.png");
        roomType.setRoomTypeImgThere("image3.png");

         Room room=new Room();
        room.setRoomNumber("001");
        room.setRoomStatus(RoomStatus.AVAILABLE);
        room.setRoomType(roomType);

        roomTypeRepository.save(roomType);
        roomRepository.save(room);

        try {
            persistedRoomType = roomTypeRepository.findById(roomType.getId());
            assertNotNull(persistedRoomType);
            assertTrue("Un-matching rooms ", roomType.equals(persistedRoomType));
        } catch (EntityNotFoundException e) {
            assertFalse("Exception " + e.getMessage(), true);
        }


    }

}
