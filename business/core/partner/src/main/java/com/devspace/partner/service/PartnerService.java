package com.devspace.partner.service;

import com.devspace.commons.common.exception.MissingMandatoryParamException;
import com.devspace.partner.domain.Address;
import com.devspace.partner.domain.Partner;
import com.devspace.partner.exception.AlreadyInactiveStateException;
import com.devspace.partner.exception.PartnerCreationException;
import com.devspace.partner.exception.PartnerNotFoundException;

import java.util.List;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface PartnerService {

    Partner createPartner(String externalId, String name, String description, List<String> phoneNumbers)
            throws MissingMandatoryParamException, PartnerCreationException;

    Partner addAddressToPartner(long partnerId, Address address) throws PartnerNotFoundException;

    Partner addBillingAccountToPartner(long partnerId, long billingAccountId) throws PartnerNotFoundException;

    Partner addLoginAccountToPartner(long partnerId, long loggingAccountId) throws PartnerNotFoundException;

    Partner addPhoneNumberToPartner(long partnerId, String phoneNumber) throws PartnerNotFoundException;

    Partner getPartnerById(long partnerId) throws PartnerNotFoundException;

    void activatePartner(long partnerId) throws PartnerNotFoundException, AlreadyInactiveStateException;

    void inactivatePartner(long partnerId) throws PartnerNotFoundException, AlreadyInactiveStateException;
}
