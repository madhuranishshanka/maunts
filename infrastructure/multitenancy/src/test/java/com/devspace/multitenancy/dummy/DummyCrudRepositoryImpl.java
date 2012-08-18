package com.devspace.multitenancy.dummy;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Repository("dummyCrudRepository")
public class DummyCrudRepositoryImpl {

    @PersistenceContext(name = "testMultitenancy")
    private EntityManager entityManager;


    public Class<DummyEntityBean> getClassType() {
        return DummyEntityBean.class;
    }

    public List<DummyEntityBean> findAll() {
        Query query = entityManager.createQuery("select o from DummyEntityBean o");
        List<DummyEntityBean> resultList = query.getResultList();
        return resultList;
    }

    public void save(DummyEntityBean entityBean) {
        entityManager.persist(entityBean);
    }
}
