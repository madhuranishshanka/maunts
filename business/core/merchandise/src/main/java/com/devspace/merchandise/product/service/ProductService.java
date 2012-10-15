package com.devspace.merchandise.product.service;

import com.devspace.merchandise.product.domain.Product;
import com.devspace.merchandise.product.exception.ProductNotFoundException;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface ProductService {

    Product createProduct(Product product);

    Product getProductById(long productId) throws ProductNotFoundException;

    Product getProductByXId(String productXId) throws ProductNotFoundException;
}
