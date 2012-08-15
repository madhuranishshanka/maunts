package com.devspace.multitenancy.mock;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Repository("crudRepository")
public class MockCrudRepositoryImpl extends com.devspace.persistence.repository.impl.CrudRepositoryImpl<EntityBean> {

    @PersistenceContext(name = "testMultitenancy")
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

    public List<EntityBean> findAll() {
        Query query = getEntityManager().createQuery("select o from EntityBean o");
        List<EntityBean> resultList = query.getResultList();
        return resultList;
    }
}
