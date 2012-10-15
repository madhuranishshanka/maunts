package com.devspace.partner.domain;

import com.devspace.partner.exception.AlreadyInactiveStateException;
import com.devspace.persistence.domain.Entity;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class Partner extends Entity {

    private String externalId;
    private String name;
    private String description;
    private Address address;
    @OneToOne(cascade = {CascadeType.ALL})
    private ActivationStatus activationStatus;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
    @OneToMany(cascade = {CascadeType.ALL})
    private List<PartnerBillingAccount> billingAccounts = new ArrayList<PartnerBillingAccount>();
    @OneToMany(cascade = {CascadeType.ALL})
    private List<PartnerLoginAccount> loginAccounts = new ArrayList<PartnerLoginAccount>();


    public void activate() throws AlreadyInactiveStateException {

        if (activationStatus != null && activationStatus.getStatus().equals(ActivationStatus.Status.ACTIVE)) {
            throw new AlreadyInactiveStateException("Partner Id"+ getId());
        }
        ActivationStatus newActivationStatus = new ActivationStatus();
        newActivationStatus.setStatus(ActivationStatus.Status.ACTIVE);
        newActivationStatus.setChangedDate(Calendar.getInstance().getTime());
        newActivationStatus.setPreviousActivationStatus(activationStatus);
        activationStatus = newActivationStatus;
    }

    public void inactivate() throws AlreadyInactiveStateException {

        if (activationStatus != null && activationStatus.getStatus().equals(ActivationStatus.Status.INACTIVE)) {
            throw new AlreadyInactiveStateException("Partner Id"+ getId());
        }

        ActivationStatus newActivationStatus = new ActivationStatus();
        newActivationStatus.setStatus(ActivationStatus.Status.INACTIVE);
        newActivationStatus.setChangedDate(Calendar.getInstance().getTime());
        newActivationStatus.setPreviousActivationStatus(activationStatus);
        activationStatus = newActivationStatus;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<PartnerBillingAccount> getBillingAccounts() {
        return billingAccounts;
    }

    public void setBillingAccounts(List<PartnerBillingAccount> billingAccounts) {
        this.billingAccounts = billingAccounts;
    }

    public List<PartnerLoginAccount> getLoginAccounts() {
        return loginAccounts;
    }

    public void setLoginAccounts(List<PartnerLoginAccount> loginAccounts) {
        this.loginAccounts = loginAccounts;
    }

    public ActivationStatus getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(ActivationStatus activationStatus) {
        this.activationStatus = activationStatus;
    }

}
