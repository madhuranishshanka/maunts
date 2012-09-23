package com.devspace.reservation.service.impl;

import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.reservation.domain.Room;
import com.devspace.reservation.domain.RoomStatus;
import com.devspace.reservation.domain.RoomType;
import com.devspace.reservation.exception.RoomNotFoundException;
import com.devspace.reservation.exception.RoomTypeNotFoundException;
import com.devspace.reservation.repository.RoomRepository;
import com.devspace.reservation.repository.RoomTypeRepository;
import com.devspace.reservation.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 9/2/12
 * Time: 8:21 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("roomService")
@Transactional(propagation = Propagation.REQUIRED)
public class RoomServiceImpl implements RoomService {

    @Resource(name = "roomTypeRepository")
    private RoomTypeRepository roomTypeRepository;
    @Resource(name = "roomRepository")
    private RoomRepository roomRepository;

    public List<String> createRooms(int startingRoomNumber, int numberOfRooms, String roomNumberSuffix,
                                    String roomNumberPrefix, int range, String roomTypeName,
                                    RoomStatus roomStatus) throws RoomTypeNotFoundException {

        RoomType roomType = getRoomTypeByName(roomTypeName);
        List<String> roomNumbers = new ArrayList<String>();
        int roomNumber = startingRoomNumber;

        for (int i = 0; i < numberOfRooms; i++) {
            String roomNumberAsString = roomNumberPrefix + roomNumber + roomNumberSuffix;
            roomNumber = roomNumber + range;
            Room room = new Room(roomNumberAsString, roomStatus, roomType);
            roomRepository.save(room);
            roomNumbers.add(room.getNumber());
        }
        return roomNumbers;
    }

    public Room createRoom(String roomNumber, String roomTypeName, RoomStatus roomStatus) throws RoomTypeNotFoundException {
        RoomType roomType = getRoomTypeByName(roomTypeName);
        Room room = new Room(roomNumber, roomStatus,roomType);
        roomRepository.save(room);
        return room;
    }

    public RoomType createRoomType(RoomType roomType) {
        roomTypeRepository.save(roomType);
        return roomType;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public RoomType getRoomTypeByName(String roomTypeName) throws RoomTypeNotFoundException {
        try {
            return roomTypeRepository.findByName(roomTypeName);
        } catch (EntityNotFoundException e) {
            throw new RoomTypeNotFoundException(e.getMessage());
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RoomType> getAllRoomTypes() {
        return roomTypeRepository.findAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Room getRoomByRoomNumber(String roomNumber) throws RoomNotFoundException {
        try {
            return roomRepository.findByNumber(roomNumber);
        } catch (EntityNotFoundException e) {
            throw new RoomNotFoundException(e.getMessage());
        }
    }

    public List<Room> getAvailableRooms(Date fromDate, Date toData) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Room> getAvailableRooms(RoomType romType, Date fromDate, Date toData) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }


}
