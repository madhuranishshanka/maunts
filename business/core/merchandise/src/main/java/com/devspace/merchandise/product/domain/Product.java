package com.devspace.merchandise.product.domain;

import com.devspace.commons.common.exception.MissingMandatoryParamException;
import com.devspace.merchandise.product.exception.InvalidProductException;
import com.devspace.persistence.domain.Entity;
import org.apache.commons.lang.StringUtils;

import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class Product extends Entity {

    private static final int NEGATIVE_VALUE = -1;
    private String externalId;
    private String name;
    private String description;
    private long pricingPlanId = NEGATIVE_VALUE;
    private String code;
    @OneToMany
    private Set<Product> childProducts = new HashSet<Product>();

    public Product() {
    }

    public Product(String externalId, String name) {
        this.externalId = externalId;
        this.name = name;
    }

    public Product(String externalId, String name, String description, String code, Set<Product> childProducts,
                   long pricingPlanId) throws MissingMandatoryParamException, InvalidProductException {
        validateProduct(externalId);
        validateChildProducts(childProducts);
        this.externalId = externalId;
        this.name = name;
        this.description = description;
        this.code = code;
        this.childProducts = childProducts;
        this.pricingPlanId = pricingPlanId;
    }

    public void addChildProduct(Product childProduct) throws MissingMandatoryParamException, InvalidProductException {
        Set<Product> childProducts = new HashSet<Product>();
        childProducts.add(childProduct);
        validateChildProducts(childProducts);
        childProducts.add(childProduct);
    }

    private void validateProduct(String externalId) throws MissingMandatoryParamException {
        if(externalId == null && StringUtils.isEmpty(externalId) ){
            throw new MissingMandatoryParamException("Missing product external id");
        }
    }

    private void validateChildProducts(Set<Product> childProducts) throws MissingMandatoryParamException,
            InvalidProductException {

        if (pricingPlanId == NEGATIVE_VALUE) {
            for (Product childProduct : childProducts) {
                if (childProduct.getPricingPlanId() == NEGATIVE_VALUE) {
                    throw new MissingMandatoryParamException("Missing pricing plan in child product");
                }
            }
        } else {
            for (Product childProduct : childProducts) {
                if (childProduct.getPricingPlanId() != NEGATIVE_VALUE) {
                    throw new InvalidProductException("Can not have child pricing plan when parent has one");
                }
            }
        }
    }

    public String getExternalId() {
        return externalId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getPricingPlanId() {
        return pricingPlanId;
    }

    public String getCode() {
        return code;
    }

    public Set<Product> getChildProducts() {
        return Collections.unmodifiableSet(childProducts);
    }
}
