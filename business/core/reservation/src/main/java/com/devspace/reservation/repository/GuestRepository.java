package com.devspace.reservation.repository;

import com.devspace.persistence.domain.Entity;
import com.devspace.persistence.repository.CrudRepository;

/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 8/26/12
 * Time: 8:31 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GuestRepository<T extends Entity> extends CrudRepository<T> {
}
