package com.devspace.partner.service.impl;

import com.devspace.partner.domain.*;
import com.devspace.partner.exception.*;
import com.devspace.partner.repository.PartnerRepository;
import com.devspace.partner.service.PartnerService;
import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.commons.common.exception.MissingMandatoryParamException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Service("partnerService")
@Transactional(propagation = Propagation.REQUIRED)
public class PartnerServiceImpl implements PartnerService {

    @Resource(name = "partnerRepository")
    private PartnerRepository partnerRepository;

    public Partner createPartner(String externalId, String name, String description, List<String> phoneNumbers)
            throws MissingMandatoryParamException, PartnerCreationException {

        if (externalId == null || StringUtils.isEmpty(externalId)) {
            throw new MissingMandatoryParamException("Missing external Id");
        }

        Partner partner = new Partner();
        partner.setExternalId(externalId);
        partner.setName(name);
        partner.setDescription(description);
        try {
            partner.activate();
        } catch (AlreadyInactiveStateException e) {
            // This should never be reach
            throw new PartnerCreationException(e.getMessage());
        }

        if (phoneNumbers != null && phoneNumbers.size() > 0) {
            for (String phoneNumber : phoneNumbers) {
                partner.getPhoneNumbers().add(new PhoneNumber(phoneNumber));
            }
        }
        partnerRepository.save(partner);

        return partner;
    }

    public Partner addAddressToPartner(long partnerId, Address address) throws PartnerNotFoundException {
        try {
            Partner partner = partnerRepository.findById(partnerId);
            partner.setAddress(address);
            partnerRepository.update(partner);
            partnerRepository.update(partner);
            return partner;
        } catch (EntityNotFoundException e) {
            throw new PartnerNotFoundException("Partner not found for the id " + partnerId);
        }
    }

    public Partner addBillingAccountToPartner(long partnerId, long billingAccountId) throws PartnerNotFoundException {
        try {
            Partner partner = partnerRepository.findById(partnerId);
            partner.getBillingAccounts().add(new PartnerBillingAccount(billingAccountId));
            partnerRepository.update(partner);
            return partner;
        } catch (EntityNotFoundException e) {
            throw new PartnerNotFoundException("Partner not found for the id " + partnerId);
        }
    }

    public Partner addLoginAccountToPartner(long partnerId, long loggingAccountId) throws PartnerNotFoundException {
        try {
            Partner partner = partnerRepository.findById(partnerId);
            partner.getLoginAccounts().add(new PartnerLoginAccount(loggingAccountId));
            partnerRepository.update(partner);
            return partner;
        } catch (EntityNotFoundException e) {
            throw new PartnerNotFoundException("Partner not found for the id " + partnerId);
        }
    }

    public Partner addPhoneNumberToPartner(long partnerId, String phoneNumber) throws PartnerNotFoundException {
        try {
            Partner partner = partnerRepository.findById(partnerId);
            partner.getPhoneNumbers().add(new PhoneNumber(phoneNumber));
            partnerRepository.update(partner);
            return partner;
        } catch (EntityNotFoundException e) {
            throw new PartnerNotFoundException("Partner not found for the id " + partnerId);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Partner getPartnerById(long partnerId) throws PartnerNotFoundException {
        try {
            return partnerRepository.findById(partnerId);
        } catch (EntityNotFoundException e) {
            throw new PartnerNotFoundException("Partner not found for the id " + partnerId);
        }
    }

    public void activatePartner(long partnerId) throws PartnerNotFoundException, AlreadyInactiveStateException {
        try {
            Partner partner = partnerRepository.findById(partnerId);
            partner.activate();
            partnerRepository.update(partner);
        } catch (EntityNotFoundException e) {
            throw new PartnerNotFoundException("Partner not found for the id " + partnerId);
        }
    }

    public void inactivatePartner(long partnerId) throws PartnerNotFoundException, AlreadyInactiveStateException {
        try {
            Partner partner = partnerRepository.findById(partnerId);
            partner.inactivate();
            partnerRepository.update(partner);
        } catch (EntityNotFoundException e) {
            throw new PartnerNotFoundException("Partner not found for the id " + partnerId);
        }
    }
}
