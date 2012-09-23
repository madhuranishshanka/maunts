package com.devspace.billing.pricing.repository.impl;

import com.devspace.billing.common.repository.impl.BillingBaseRepositoryImpl;
import com.devspace.billing.pricing.domain.PricingPlan;
import com.devspace.billing.pricing.repository.PricingPlanRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Repository("pricingPlanRepository")
public class PricingPlanRepositoryImpl extends BillingBaseRepositoryImpl<PricingPlan> implements PricingPlanRepository{

    @Override
    public Class<PricingPlan> getClassType() {
        return PricingPlan.class;
    }
}
