package com.devspace.reservation.repository;

import com.devspace.persistence.domain.Entity;
import com.devspace.persistence.repository.CrudRepository;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface RoomRepository<T extends Entity> extends CrudRepository<T>{
}
