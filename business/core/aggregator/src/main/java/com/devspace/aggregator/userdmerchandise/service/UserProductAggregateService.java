package com.devspace.aggregator.userdmerchandise.service;

import com.devspace.aggregator.merchandise.exception.ProductAggregateNotFoundException;
import com.devspace.aggregator.userdmerchandise.domain.UserProductAggregate;
import com.devspace.aggregator.userdmerchandise.exception.UserProductAggregateNotFoundException;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface UserProductAggregateService {

    UserProductAggregate createUserProductAggregate(long productAggregateId, long userId) throws ProductAggregateNotFoundException;

    UserProductAggregate getUserProductAggregate(long userProductAggregateId) throws UserProductAggregateNotFoundException;

}
