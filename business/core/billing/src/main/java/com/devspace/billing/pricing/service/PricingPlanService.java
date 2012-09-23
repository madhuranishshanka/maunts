package com.devspace.billing.pricing.service;

import com.devspace.billing.common.domain.Amount;
import com.devspace.commons.common.exception.MissingMandatoryParamException;
import com.devspace.billing.pricing.domain.PriceTier;
import com.devspace.billing.pricing.domain.PricingPlan;
import com.devspace.billing.pricing.exception.InvalidPriceTierException;
import com.devspace.billing.pricing.exception.PricingPlanNotFoundException;

import java.util.List;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface PricingPlanService {

    PricingPlan createPricingPlan(List<PriceTier> priceTiers, PricingPlan.Type type)
            throws MissingMandatoryParamException, InvalidPriceTierException;

    PricingPlan getPricingPlanById(long pricingPlanId) throws PricingPlanNotFoundException;

    Amount calculatePrice(long pricingPlanId, double quantity) throws PricingPlanNotFoundException;
}
