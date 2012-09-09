package com.devspace.reservation.repository.impl;

import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.reservation.domain.Room;
import com.devspace.reservation.repository.RoomRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Repository("roomRepository")
public class RoomRepositoryImpl extends BaseRepositoryImpl<Room> implements RoomRepository {

    @Override
    public Class<Room> getClassType() {
        return Room.class;
    }

    public Room findByNumber(String number) throws EntityNotFoundException {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Room> criteriaQuery = criteriaBuilder.createQuery(Room.class);

        Root<Room> room = criteriaQuery.from(Room.class);
        Predicate numberPredicate = criteriaBuilder.equal(room.get("number"), number);
        CriteriaQuery<Room> select = criteriaQuery.select(room).where(numberPredicate);

        TypedQuery<Room> query = getEntityManager().createQuery(select);
        Room singleResult = query.getSingleResult();

        if (singleResult == null) {
            throw new EntityNotFoundException(Room.class, "No room found for the given room number: " + number);
        }
        return singleResult;
    }
}
