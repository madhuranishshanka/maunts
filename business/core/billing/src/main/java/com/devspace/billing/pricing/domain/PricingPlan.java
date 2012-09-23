package com.devspace.billing.pricing.domain;

import com.devspace.persistence.domain.Entity;
import com.devspace.billing.pricing.exception.InvalidPriceTierException;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.*;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class PricingPlan extends Entity {

    @Transient
    private static final int INDEX_INCREMENT = 1;
    @Transient
    private static final int STARTING_INDEX = 1;
    @Transient
    private static final int STARTING_UNIT_VALUE = 0;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<PriceTier> priceTiers = new HashSet<PriceTier>();
    private Type type;
    private boolean bulkPricing;

    public enum Type {
        SALES_FEE, SHIPPING_FEE
    }

    public PricingPlan() {
    }

    public PricingPlan(Set<PriceTier> priceTiers, Type type) throws InvalidPriceTierException {
        validatePriceTies(priceTiers);
        this.type = type;
        this.priceTiers = priceTiers;
    }

    public void addPricingPlan(PriceTier priceTiers) throws InvalidPriceTierException {
        Set<PriceTier> allPriceTiers = new HashSet<PriceTier>(this.priceTiers);
        allPriceTiers.add(priceTiers);
        validatePriceTies(allPriceTiers);
        this.priceTiers.add(priceTiers);
    }

    private void validatePriceTies(Set<PriceTier> priceTiers) throws InvalidPriceTierException {
        Map<Integer, Double> indexStartingUnit = new HashMap<Integer, Double>();
        Map<Integer, Double> indexEndingUnit = new HashMap<Integer, Double>();

        Set<Integer> indexes = new TreeSet<Integer>();

        for (PriceTier priceTier : priceTiers) {
            Integer index = priceTier.getIndex();
            if (indexes.contains(index)) {
                throw new InvalidPriceTierException("Tier index already exist");
            } else {
                indexes.add(index);
                indexStartingUnit.put(index, priceTier.getStartingUnit());
                indexEndingUnit.put(index, priceTier.getEndingUnit());
            }
        }

        int currentIndex = STARTING_INDEX;
        for (Integer index : indexes) {
            if (index != currentIndex) {
                throw new InvalidPriceTierException("Missing tier index");
            }
            double currentStartingUnit = indexStartingUnit.get(currentIndex);
            double currentEndingUnit = indexEndingUnit.get(currentIndex);

            if (index > STARTING_INDEX) {
                double prevStartingUnit = indexStartingUnit.get(currentIndex - INDEX_INCREMENT);
                double prevEndingUnit = indexEndingUnit.get(currentIndex - INDEX_INCREMENT);


                if (index == STARTING_INDEX + INDEX_INCREMENT && prevStartingUnit == currentStartingUnit) {
                    bulkPricing = true;
                }

                if (bulkPricing) {
                    if (prevStartingUnit != currentStartingUnit) {
                        throw new InvalidPriceTierException("Inconsistent bulk pricing, " +
                                "all starting units should start from same  value");
                    }

                    if (prevEndingUnit >= currentEndingUnit) {
                        throw new InvalidPriceTierException("Inconsistent bulk pricing, ending units are not ordered");
                    }
                } else {
                    if (prevEndingUnit + INDEX_INCREMENT != currentStartingUnit) {
                        throw new InvalidPriceTierException("Inconsistent tier pricing, units are not ordered");
                    }
                }
            } else{
                if(currentStartingUnit != STARTING_UNIT_VALUE){
                    throw new InvalidPriceTierException("Incorrect starting unit");
                }
            }
            currentIndex++;
        }

    }


    public Set<PriceTier> getPriceTiers() {
        return Collections.unmodifiableSet(priceTiers);
    }

    public Type getType() {
        return type;
    }

    public boolean isBulkPricing() {
        return bulkPricing;
    }
}
