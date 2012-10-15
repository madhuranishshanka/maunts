package com.devspace.merchandise.product.repository.impl;

import com.devspace.merchandise.product.domain.Product;
import com.devspace.merchandise.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Service("productRepository")
public class ProductRepositoryImpl extends BaseRepositoryImpl<Product> implements ProductRepository {

    @Override
    public Class<Product> getClassType() {
        return Product.class;
    }
}
