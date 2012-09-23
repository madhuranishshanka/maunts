package com.devspace.reservation.repository;

import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.persistence.repository.CrudRepository;
import com.devspace.reservation.domain.Room;
import com.devspace.reservation.repository.model.RoomSearchCriteria;

import java.util.List;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface RoomRepository extends CrudRepository<Room> {

    Room findByNumber(String number) throws EntityNotFoundException;

    List<Room> findRooms(RoomSearchCriteria criteria);

}
