package com.devspace.persistence.mock;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Repository("crudRepository")
public class MockCrudRepositoryImpl extends com.devspace.persistence.repository.impl.CrudRepositoryImpl<EntityBean> {

    @PersistenceContext(name = "testPersistence")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }
    public void setEntityManager(EntityManager entityManager){

    }
    @Override
    public Class<EntityBean> getClassType() {
        return EntityBean.class;
    }

}
