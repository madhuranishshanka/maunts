package com.devspace.reservation.service.impl;

import com.devspace.reservation.domain.RoomType;
import com.devspace.reservation.repository.RoomTypeRepository;
import com.devspace.reservation.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 9/2/12
 * Time: 8:21 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("roomService")
public class RoomServiceImpl implements RoomService {

    @Resource(name = "roomTypeRepository")
    private RoomTypeRepository roomTypeRepository;

    public List<RoomType> getAllRoomTypes(){
        return roomTypeRepository.findAll();
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public RoomType createRoomType(RoomType roomType) {
        roomTypeRepository.save(roomType);
        return roomType;
    }

    public RoomType getRoomTypeByName(String roomTypeName) {
        throw new UnsupportedOperationException();
    }


}
