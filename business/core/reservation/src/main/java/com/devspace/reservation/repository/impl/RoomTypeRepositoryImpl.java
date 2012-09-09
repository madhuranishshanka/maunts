package com.devspace.reservation.repository.impl;

import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.reservation.domain.RoomType;
import com.devspace.reservation.repository.RoomTypeRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created with IntelliJ IDEA.
 * User: Naz
 * Date: 8/26/12
 * Time: 5:31 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("roomTypeRepository")
public class RoomTypeRepositoryImpl extends BaseRepositoryImpl<RoomType> implements RoomTypeRepository {

    @Override
    public Class<RoomType> getClassType() {
        return RoomType.class;
    }

    public RoomType findByName(String name) throws EntityNotFoundException {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<RoomType> criteriaQuery = criteriaBuilder.createQuery(RoomType.class);

        Root<RoomType> roomType = criteriaQuery.from(RoomType.class);
        Predicate namePredicate = criteriaBuilder.equal(roomType.get("name"), name);
        CriteriaQuery<RoomType> select = criteriaQuery.select(roomType).where(namePredicate);

        TypedQuery<RoomType> query = getEntityManager().createQuery(select);
        RoomType singleResult = query.getSingleResult();

        if(singleResult == null){
            throw new EntityNotFoundException(RoomType.class,"No room type found for the given room name: "+name);
        }
        return singleResult;
    }
}
