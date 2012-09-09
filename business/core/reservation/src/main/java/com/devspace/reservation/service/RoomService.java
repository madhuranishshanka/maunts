package com.devspace.reservation.service;

import com.devspace.reservation.domain.Room;
import com.devspace.reservation.domain.RoomStatus;
import com.devspace.reservation.domain.RoomType;
import com.devspace.reservation.exception.RoomNotFoundException;
import com.devspace.reservation.exception.RoomTypeNotFoundException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 9/2/12
 * Time: 8:20 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RoomService {

    List<String> createRooms(int startingRoomNumber,int numberOfRooms,String roomNumberSuffix,
                             String roomNumberPrefix,int range,String roomTypeName,RoomStatus roomStatus) throws RoomTypeNotFoundException;

    Room createRoom(String roomNumber,String roomTypeName,RoomStatus roomStatus) throws RoomTypeNotFoundException;

    RoomType createRoomType(RoomType roomType);

    RoomType getRoomTypeByName(String roomTypeName) throws RoomTypeNotFoundException;

    List<RoomType> getAllRoomTypes();

    Room getRoomByRoomNumber(String roomNumber) throws RoomNotFoundException;

    List<Room> getAllRooms();
}
