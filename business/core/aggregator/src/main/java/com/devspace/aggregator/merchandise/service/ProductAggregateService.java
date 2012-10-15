package com.devspace.aggregator.merchandise.service;

import com.devspace.aggregator.merchandise.domain.ProductAggregate;
import com.devspace.aggregator.merchandise.exception.ProductAggregateNotFoundException;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface ProductAggregateService {

    ProductAggregate createProductAggregate(ProductAggregate productAggregate);

    ProductAggregate getProductAggregateById(long productAggregateId) throws ProductAggregateNotFoundException;

    ProductAggregate getProductAggregateByProductId(long productId)throws ProductAggregateNotFoundException;


}
