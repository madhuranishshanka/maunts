package com.devspace.persistence.repository.impl;

import com.devspace.persistence.domain.Entity;
import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.persistence.repository.CrudRepository;

import javax.persistence.EntityManager;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public abstract class CrudRepositoryImpl<T extends Entity> implements CrudRepository<T> {

    public abstract EntityManager getEntityManager();

    public abstract Class<T> getClassType();

    public void save(T entity) {
        getEntityManager().persist(entity);
    }

    public T update(T entity) {
        return getEntityManager().merge(entity);
    }

    public T findById(long id) throws EntityNotFoundException {
        T entity = getEntityManager().find(getClassType(), id);
        if (entity != null)
            return entity;
        throw new EntityNotFoundException(getClassType(), "No " + getClassType() + " is found for the primary key :" + id);
    }

    public void delete(long id) throws EntityNotFoundException {
        getEntityManager().remove(findById(id));
    }
}
