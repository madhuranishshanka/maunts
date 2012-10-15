package com.devspace.aggregator.merchandise.domain;

import com.devspace.persistence.domain.Entity;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class ProductAggregate extends Entity{

    private long productId;
    private long merchantId;
    private long pricingPlanId;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public long getPricingPlanId() {
        return pricingPlanId;
    }

    public void setPricingPlanId(long pricingPlanId) {
        this.pricingPlanId = pricingPlanId;
    }
}
