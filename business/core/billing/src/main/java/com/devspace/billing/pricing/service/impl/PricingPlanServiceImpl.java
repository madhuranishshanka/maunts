package com.devspace.billing.pricing.service.impl;

import com.devspace.billing.common.domain.Amount;
import com.devspace.commons.common.exception.MissingMandatoryParamException;
import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.billing.pricing.domain.PriceTier;
import com.devspace.billing.pricing.domain.PricingPlan;
import com.devspace.billing.pricing.exception.InvalidPriceTierException;
import com.devspace.billing.pricing.exception.PricingPlanNotFoundException;
import com.devspace.billing.pricing.repository.PricingPlanRepository;
import com.devspace.billing.pricing.service.PricingPlanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Service("pricingPlanService")
@Transactional(propagation = Propagation.REQUIRED)
public class PricingPlanServiceImpl implements PricingPlanService {

    @Resource
    private PricingPlanRepository pricingPlanRepository;

    public PricingPlan createPricingPlan(List<PriceTier> priceTiers, PricingPlan.Type type) throws
            MissingMandatoryParamException, InvalidPriceTierException {

        if (type == null) {
            throw new MissingMandatoryParamException("Pricing plan type is missing");
        }

        if (priceTiers == null || priceTiers.isEmpty()) {
            throw new MissingMandatoryParamException("Price tiers are missing");
        }

        PricingPlan pricingPlan = new PricingPlan(new HashSet<PriceTier>(priceTiers), type);

        pricingPlanRepository.save(pricingPlan);

        return pricingPlan;
    }

    public PricingPlan getPricingPlanById(long pricingPlanId) throws PricingPlanNotFoundException {
        try {
            return pricingPlanRepository.findById(pricingPlanId);
        } catch (EntityNotFoundException e) {
            throw new PricingPlanNotFoundException("Pricing Plan not found for the id " + pricingPlanId);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Amount calculatePrice(long pricingPlanId, double quantity) throws PricingPlanNotFoundException {

        PricingPlan pricingPlan = null;
        try {
            pricingPlan = pricingPlanRepository.findById(pricingPlanId);
        } catch (EntityNotFoundException e) {
            throw new PricingPlanNotFoundException("Pricing plan not found for the id " + pricingPlanId);
        }

        Set<PriceTier> priceTiers = pricingPlan.getPriceTiers();
        Map<Integer, PriceTier> priceTierMap = new HashMap<Integer, PriceTier>();
        Set<Integer> indexes = new TreeSet<Integer>();

        for (PriceTier priceTier : priceTiers) {
            indexes.add(priceTier.getIndex());
            priceTierMap.put(priceTier.getIndex(), priceTier);
        }

        Amount total = null;
        double prevEndingUnit = 0.0;
        for (Integer index : indexes) {
            PriceTier priceTier = priceTierMap.get(index);
            double startingUnit = priceTier.getStartingUnit();
            double endingUnit = priceTier.getEndingUnit();

            Amount tierPrice = (Amount) priceTier.getPrice().clone();
            if (pricingPlan.isBulkPricing()) {
                if (startingUnit <= quantity && endingUnit >= quantity) {
                    total = tierPrice;
                    total.multiply(quantity);
                    break;
                }
            } else {

                if (quantity > endingUnit) {
                    // the first iteration
                    if (total == null) {
                        tierPrice.multiply(endingUnit);
                        total = tierPrice;
                        prevEndingUnit = endingUnit;
                    } else {
                        tierPrice.multiply(endingUnit - prevEndingUnit);
                        total.add(tierPrice);
                    }
                } else {
                    if (total == null) {
                        tierPrice.multiply(quantity);
                        total = tierPrice;
                    } else {
                        tierPrice.multiply(quantity - prevEndingUnit);
                        total.add(tierPrice);
                    }
                    break;
                }
            }

        }
        return total;
    }


}
