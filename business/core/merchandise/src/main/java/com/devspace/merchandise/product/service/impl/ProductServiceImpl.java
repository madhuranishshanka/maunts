package com.devspace.merchandise.product.service.impl;

import com.devspace.merchandise.product.domain.Product;
import com.devspace.merchandise.product.exception.ProductNotFoundException;
import com.devspace.merchandise.product.repository.ProductRepository;
import com.devspace.merchandise.product.service.ProductService;
import com.devspace.persistence.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Resource(name = "productRepository")
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public Product getProductById(long productId) throws ProductNotFoundException {
        try {
            return productRepository.findById(productId);
        } catch (EntityNotFoundException e) {
            throw new ProductNotFoundException("Product not found for the id " + productId);
        }
    }

    public Product getProductByXId(String productXId) throws ProductNotFoundException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
