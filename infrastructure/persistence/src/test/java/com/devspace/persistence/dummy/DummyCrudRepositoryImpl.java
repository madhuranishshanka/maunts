package com.devspace.persistence.dummy;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Repository("dummyCrudRepository")
public class DummyCrudRepositoryImpl extends com.devspace.persistence.repository.impl.CrudRepositoryImpl<DummyEntityBean> {

    @PersistenceContext(name = "testPersistence")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
    @Override
    public Class<DummyEntityBean> getClassType() {
        return DummyEntityBean.class;
    }

}
