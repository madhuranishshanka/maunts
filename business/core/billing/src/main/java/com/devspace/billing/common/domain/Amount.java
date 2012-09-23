package com.devspace.billing.common.domain;

import com.devspace.commons.common.exception.UnitMismatchException;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Embeddable
public class Amount implements Cloneable{
    private BigDecimal value;
    private String currencySymbol;

    public Amount() {
    }

    public Amount(BigDecimal value, String currencySymbol) {
        this.value = value;
        this.currencySymbol = currencySymbol;
    }

    public void add(Amount amount) {
        if (!currencySymbol.equals(amount.getCurrencySymbol())) {
            throw new UnitMismatchException("Two currencies " + currencySymbol + " and " + amount.getCurrencySymbol());
        }

        value = value.add(amount.getValue());
    }

    public void subtract(Amount amount) {
        if (!currencySymbol.equals(amount.getCurrencySymbol())) {
            throw new UnitMismatchException("Two currencies " + currencySymbol + " and " + amount.getCurrencySymbol());
        }
        value = value.subtract(amount.getValue());
    }

    public void multiply(double multiplier) {
        value = value.multiply(new BigDecimal(multiplier));
    }

    public void dived(double divisor) {
        value = value.divide(new BigDecimal(divisor));
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public Object clone(){
        return new Amount(value,currencySymbol);
    }
}
