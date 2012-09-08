package com.devspace.partner.domain;

import com.devspace.persistence.domain.Entity;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class PartnerLoginAccount extends Entity {

    private long loginAccountId;

    public PartnerLoginAccount() {
    }

    public PartnerLoginAccount(long loginAccountId) {
        this.loginAccountId = loginAccountId;
    }

    public long getLoginAccountId() {
        return loginAccountId;
    }

    public void setLoginAccountId(long loginAccountId) {
        this.loginAccountId = loginAccountId;
    }
}
