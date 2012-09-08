package com.devspace.reservation.repository.impl;

import com.devspace.persistence.domain.Entity;
import com.devspace.reservation.domain.RoomType;
import com.devspace.reservation.repository.RoomTypeRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 8/26/12
 * Time: 5:31 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("roomTypeRepository")
public class RoomTypeRepositoryImpl<T extends Entity> extends BaseRepositoryImpl<T> implements RoomTypeRepository<T> {

    public Class<T> getClassType() {
        return (Class<T>) RoomType.class;
    }

}
