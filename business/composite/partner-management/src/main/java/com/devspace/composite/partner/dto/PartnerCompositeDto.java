package com.devspace.composite.partner.dto;

import com.devspace.partner.domain.Partner;
import com.devspace.security.domain.LoginAccount;

import java.util.Set;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public class PartnerCompositeDto {
    private Partner partner;
    private Set<LoginAccount> loginAccounts;

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public Set<LoginAccount> getLoginAccounts() {
        return loginAccounts;
    }

    public void setLoginAccounts(Set<LoginAccount> loginAccounts) {
        this.loginAccounts = loginAccounts;
    }
}
