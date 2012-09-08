package com.devspace.composite.partner.dto;

import com.devspace.partner.domain.Address;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public class PartnerDto {
    private String externalId;
    private String name;
    private String description;
    private Address address;
    private List<String> phoneNumbers = new ArrayList<String>();
    private List<LoginAccountDTO> loginAccountDTOs = new ArrayList<LoginAccountDTO>();

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

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<LoginAccountDTO> getLoginAccountDTOs() {
        return loginAccountDTOs;
    }

    public void setLoginAccountDTOs(List<LoginAccountDTO> loginAccountDTOs) {
        this.loginAccountDTOs = loginAccountDTOs;
    }
}
