package com.devspace.aggregator.merchandise.repository.impl;

import com.devspace.aggregator.commons.repository.impl.BaseRepositoryImpl;
import com.devspace.aggregator.merchandise.domain.ProductAggregate;
import com.devspace.aggregator.merchandise.repository.ProductAggregateRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Repository("productAggregateRepository")
public class ProductAggregateRepositoryImpl extends BaseRepositoryImpl<ProductAggregate> implements
        ProductAggregateRepository {

    @Override
    public Class<ProductAggregate> getClassType() {
        return ProductAggregate.class;
    }
}
