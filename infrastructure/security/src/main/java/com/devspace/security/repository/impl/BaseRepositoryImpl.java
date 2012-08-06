package com.devspace.security.repository.impl;

import com.devspace.persistence.domain.Entity;
import com.devspace.persistence.repository.impl.CrudRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public abstract class BaseRepositoryImpl<T extends Entity> extends CrudRepositoryImpl<T> {

    @PersistenceContext(name = "security")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
