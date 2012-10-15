package com.devspace.aggregator.userdmerchandise.repository.impl;

import com.devspace.aggregator.commons.repository.impl.BaseRepositoryImpl;
import com.devspace.aggregator.userdmerchandise.domain.UserProductAggregate;
import com.devspace.aggregator.userdmerchandise.repository.userProductAggregateRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Repository("userProductAggregateRepository")
public class UserProductAggregateRepositoryImpl extends BaseRepositoryImpl<UserProductAggregate> implements
        userProductAggregateRepository {

    @Override
    public Class<UserProductAggregate> getClassType() {
        return UserProductAggregate.class;
    }
}
