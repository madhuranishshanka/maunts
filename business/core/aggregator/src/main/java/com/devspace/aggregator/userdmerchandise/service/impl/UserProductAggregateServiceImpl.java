package com.devspace.aggregator.userdmerchandise.service.impl;

import com.devspace.aggregator.merchandise.domain.ProductAggregate;
import com.devspace.aggregator.merchandise.exception.ProductAggregateNotFoundException;
import com.devspace.aggregator.merchandise.service.ProductAggregateService;
import com.devspace.aggregator.userdmerchandise.domain.UserProductAggregate;
import com.devspace.aggregator.userdmerchandise.exception.UserProductAggregateNotFoundException;
import com.devspace.aggregator.userdmerchandise.repository.userProductAggregateRepository;
import com.devspace.aggregator.userdmerchandise.service.UserProductAggregateService;
import com.devspace.persistence.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Service("userProductAggregateService")
public class UserProductAggregateServiceImpl implements UserProductAggregateService {

    @Resource(name = "userProductAggregateRepository")
    private userProductAggregateRepository userProductAggregateRepository;
    @Resource(name = "productAggregateService")
    private ProductAggregateService productAggregateService;

    public UserProductAggregate createUserProductAggregate(long productAggregateId, long userId) throws ProductAggregateNotFoundException {

        ProductAggregate productAggregate = productAggregateService.getProductAggregateById(productAggregateId);

        UserProductAggregate userProductAggregate = new UserProductAggregate(productAggregate, userId);

        userProductAggregateRepository.save(userProductAggregate);
        return userProductAggregate;
    }

    public UserProductAggregate getUserProductAggregate(long userProductAggregateId) throws UserProductAggregateNotFoundException {
        try {
            return userProductAggregateRepository.findById(userProductAggregateId);
        } catch (EntityNotFoundException e) {
            throw new UserProductAggregateNotFoundException("User Product Aggregate not found for the id "
                    + userProductAggregateId);
        }
    }
}
