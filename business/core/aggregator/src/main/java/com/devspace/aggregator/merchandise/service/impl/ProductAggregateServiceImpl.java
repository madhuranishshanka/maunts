package com.devspace.aggregator.merchandise.service.impl;

import com.devspace.aggregator.merchandise.domain.ProductAggregate;
import com.devspace.aggregator.merchandise.exception.ProductAggregateNotFoundException;
import com.devspace.aggregator.merchandise.repository.ProductAggregateRepository;
import com.devspace.aggregator.merchandise.service.ProductAggregateService;
import com.devspace.persistence.exception.EntityNotFoundException;

import javax.annotation.Resource;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public class ProductAggregateServiceImpl implements ProductAggregateService {

    @Resource(name = "productAggregateRepository")
    private ProductAggregateRepository productAggregateRepository;

    public ProductAggregate createProductAggregate(ProductAggregate productAggregate) {
        productAggregateRepository.save(productAggregate);
        return productAggregate;
    }

    public ProductAggregate getProductAggregateById(long productAggregateId) throws ProductAggregateNotFoundException {
        try {
            return productAggregateRepository.findById(productAggregateId);
        } catch (EntityNotFoundException e) {
            throw new ProductAggregateNotFoundException("Product aggregate not found for id" + productAggregateId);
        }
    }

    public ProductAggregate getProductAggregateByProductId(long productId) throws
            ProductAggregateNotFoundException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
