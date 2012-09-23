package com.devspace.billing.pricing.domain;

import com.devspace.billing.common.domain.Amount;
import com.devspace.commons.common.exception.InvalidInputParamException;
import com.devspace.commons.common.exception.MissingMandatoryParamException;
import com.devspace.persistence.domain.Entity;

import javax.persistence.Embedded;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class PriceTier extends Entity {
    private double startingUnit;
    private double endingUnit;
    private int index;
    private PriceModel priceModel;
    @Embedded
    private Amount price;

    public enum PriceModel {
        PER_UNIT, FLAT_FEE
    }

    public PriceTier() {
    }

    public PriceTier(double startingUnit, double endingUnit, int index, PriceModel priceModel,
                     Amount price) throws MissingMandatoryParamException, InvalidInputParamException {
        validatePriceTier(startingUnit, endingUnit, priceModel, price);
        this.startingUnit = startingUnit;
        this.endingUnit = endingUnit;
        this.index = index;
        this.priceModel = priceModel;
        this.price = price;
    }

    private void validatePriceTier(double startingUnit, double endingUnit, PriceModel priceModel,
                                   Amount price) throws MissingMandatoryParamException, InvalidInputParamException {
        if (price == null) {
            throw new MissingMandatoryParamException("Missing price");
        }

        if (priceModel == null) {
            throw new MissingMandatoryParamException("Missing Price Model");
        }


        if (startingUnit == endingUnit) {
            throw new InvalidInputParamException("Invalid starting and ending units");
        }

    }

    public double getStartingUnit() {
        return startingUnit;
    }

    public double getEndingUnit() {
        return endingUnit;
    }

    public int getIndex() {
        return index;
    }

    public PriceModel getPriceModel() {
        return priceModel;
    }

    public Amount getPrice() {
        return price;
    }
}
