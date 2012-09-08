package com.devspace.reservation.service;

import com.devspace.reservation.domain.RoomType;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 9/2/12
 * Time: 8:20 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RoomService {

    List<RoomType> getAllRoomTypes();

    RoomType createRoomType(RoomType roomType);

    RoomType getRoomTypeByName(String roomTypeName);
}
