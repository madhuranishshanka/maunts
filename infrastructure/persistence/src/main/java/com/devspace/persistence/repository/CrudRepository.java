package com.devspace.persistence.repository;

import com.devspace.persistence.domain.Entity;
import com.devspace.persistence.exception.EntityNotFoundException;

import java.util.List;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface CrudRepository<T extends Entity> {

    void save(T entity);

    T update(T entity);

    T findById(long id) throws EntityNotFoundException;

    void delete(long id) throws EntityNotFoundException;

    public List<T> findAll();
}
