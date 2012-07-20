package com.devspace.reservation.repository.impl;

import com.devspace.persistence.domain.Entity;
import com.devspace.reservation.domain.Room;
import com.devspace.reservation.repository.RoomRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Repository("roomRepository")
public class RoomRepositoryImpl<T extends Entity> extends BaseRepositoryImpl<T> implements RoomRepository<T> {

    @Override
    public Class<T> getClassType() {
        return (Class<T>) Room.class;
    }
}
