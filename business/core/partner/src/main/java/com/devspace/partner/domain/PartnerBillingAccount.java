package com.devspace.partner.domain;

import com.devspace.persistence.domain.Entity;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class PartnerBillingAccount extends Entity {

    private long billingAccountId;

    public PartnerBillingAccount() {
    }

    public PartnerBillingAccount(long billingAccountId) {
        this.billingAccountId = billingAccountId;
    }

    public long getBillingAccountId() {
        return billingAccountId;
    }

    public void setBillingAccountId(long billingAccountId) {
        this.billingAccountId = billingAccountId;
    }
}
