package com.devspace.aggregator.userdmerchandise.domain;

import com.devspace.aggregator.merchandise.domain.ProductAggregate;
import com.devspace.persistence.domain.Entity;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class UserProductAggregate extends Entity{

    private ProductAggregate productAggregate;
    private long userId;
    private long userPricingPlan;

    public UserProductAggregate(ProductAggregate productAggregate, long userId) {
        this.productAggregate = productAggregate;
        this.userId = userId;
    }

    public void setUserPricingPlan(long userPricingPlan) {
        this.userPricingPlan = userPricingPlan;
    }

    public ProductAggregate getProductAggregate() {
        return productAggregate;
    }

    public long getUserId() {
        return userId;
    }

    public long getUserPricingPlan() {
        return userPricingPlan;
    }
}
