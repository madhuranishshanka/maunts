package com.devspace.composite.partner.service;

import com.devspace.composite.partner.dto.PartnerCompositeDto;
import com.devspace.composite.partner.dto.PartnerDto;
import com.devspace.partner.exception.MissingMandatoryParamException;
import com.devspace.partner.exception.PartnerCreationException;
import com.devspace.security.exception.RoleNotFoundException;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface PartnerRegistrationService {

    PartnerCompositeDto registerPartner(PartnerDto partnerDto) throws MissingMandatoryParamException, PartnerCreationException,
            RoleNotFoundException;
}
