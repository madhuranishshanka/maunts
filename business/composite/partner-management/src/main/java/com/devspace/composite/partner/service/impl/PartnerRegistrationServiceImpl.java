package com.devspace.composite.partner.service.impl;

import com.devspace.commons.common.exception.MissingMandatoryParamException;
import com.devspace.composite.partner.dto.LoginAccountDTO;
import com.devspace.composite.partner.dto.PartnerCompositeDto;
import com.devspace.composite.partner.dto.PartnerDto;
import com.devspace.composite.partner.service.PartnerRegistrationService;
import com.devspace.partner.domain.Partner;
import com.devspace.partner.exception.PartnerCreationException;
import com.devspace.partner.exception.PartnerNotFoundException;
import com.devspace.partner.service.PartnerService;
import com.devspace.security.domain.LoginAccount;
import com.devspace.security.exception.RoleNotFoundException;
import com.devspace.security.service.LoginAccountService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public class PartnerRegistrationServiceImpl implements PartnerRegistrationService {

    @Autowired
    private PartnerService partnerService;
    @Autowired
    private LoginAccountService loginAccountService;

    public PartnerCompositeDto registerPartner(PartnerDto partnerDto) throws MissingMandatoryParamException,
            PartnerCreationException, RoleNotFoundException {

        PartnerCompositeDto partnerCompositeDto = new PartnerCompositeDto();

        Partner partner = partnerService.createPartner(partnerDto.getExternalId(), partnerDto.getName(),
                partnerDto.getDescription(), partnerDto.getPhoneNumbers());

        try {
            partner = partnerService.addAddressToPartner(partner.getId(), partnerDto.getAddress());
        } catch (PartnerNotFoundException e) {
            // will never reach to here
            throw new PartnerCreationException(e.getMessage());
        }

        for (LoginAccountDTO loginAccountDTO : partnerDto.getLoginAccountDTOs()) {
            LoginAccount loginAccount = loginAccountService.createLoginAccount(loginAccountDTO.getUserName(),
                    loginAccountDTO.getPassword(), loginAccountDTO.getRoleNames());
            try {
                partner = partnerService.addLoginAccountToPartner(partner.getId(), loginAccount.getId());
            } catch (PartnerNotFoundException e) {
                // will never reach to here
                throw new PartnerCreationException(e.getMessage());
            }
            partnerCompositeDto.getLoginAccounts().add(loginAccount);
        }

        partnerCompositeDto.setPartner(partner);
        return partnerCompositeDto;
    }
}
