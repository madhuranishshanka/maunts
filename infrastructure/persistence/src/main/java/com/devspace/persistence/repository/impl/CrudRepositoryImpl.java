package com.devspace.persistence.repository.impl;

import com.devspace.persistence.domain.Entity;
import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.persistence.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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

    public List<T> findAll() {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getClassType());
        Root<T> from = criteriaQuery.from(getClassType());
        CriteriaQuery<T> select = criteriaQuery.select(from);

        TypedQuery<T> query = getEntityManager().createQuery(select);
        return query.getResultList();
    }

}
