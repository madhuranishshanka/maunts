package com.devspace.partner.repository.impl;

import com.devspace.partner.domain.Partner;
import com.devspace.partner.repository.PartnerRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Repository("partnerRepository")
public class PartnerRepositoryImpl extends BaseRepositoryImpl<Partner> implements PartnerRepository {

    @Override
    public Class<Partner> getClassType() {
        return Partner.class;
    }
}
