package com.devspace.reservation.repository;

import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.persistence.repository.CrudRepository;
import com.devspace.reservation.domain.RoomType;

/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 8/26/12
 * Time: 5:29 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RoomTypeRepository extends CrudRepository<RoomType> {

    RoomType findByName(String name) throws EntityNotFoundException;
}
