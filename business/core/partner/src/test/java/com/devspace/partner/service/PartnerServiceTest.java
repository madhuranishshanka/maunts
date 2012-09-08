package com.devspace.partner.service;

import com.devspace.partner.domain.*;
import com.devspace.partner.exception.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:partner-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class PartnerServiceTest {

    @Resource(name = "partnerService")
    private PartnerService partnerService;

    @Test
    public void testPartnerOperations() {

        Partner partner = null;
        String externalId = "XID" + System.currentTimeMillis();
        String name = "Partner Name";
        String description = "The description";
        String phoneNumberOne = "123";
        String phoneNumberTwo = "456";
        try {
            partner = partnerService.createPartner(externalId, name, description
                    , Arrays.asList(phoneNumberOne, phoneNumberTwo));
        } catch (MissingMandatoryParamException e) {
            fail();
        } catch (PartnerCreationException e) {
            fail();
        }
        assertNotNull(partner);
        assertEquals(externalId, partner.getExternalId());
        assertEquals(name, partner.getName());
        assertEquals(description, partner.getDescription());
        List<PhoneNumber> phoneNumbers = partner.getPhoneNumbers();
        assertNotNull(phoneNumbers);
        assertTrue(phoneNumbers.size() == 2);

        if (phoneNumbers.get(0).getPhoneNumber().equals(phoneNumberOne)) {
            assertEquals(phoneNumberTwo, phoneNumbers.get(1).getPhoneNumber());
        } else {
            assertEquals(phoneNumberTwo, phoneNumbers.get(0).getPhoneNumber());
            assertEquals(phoneNumberOne, phoneNumbers.get(1).getPhoneNumber());

        }

        String country = "Sri lanka";
        Address address = new Address();
        address.setCountry(country);
        try {
            partner = partnerService.addAddressToPartner(partner.getId(), address);
        } catch (PartnerNotFoundException e) {
            fail();
        }
        assertNotNull(partner);
        assertNotNull(partner.getAddress());
        assertEquals(partner.getAddress().getCountry(), country);

        int loggingAccountId = 100;
        try {
            partner = partnerService.addLoginAccountToPartner(partner.getId(), loggingAccountId);
        } catch (PartnerNotFoundException e) {
            fail();
        }
        assertNotNull(partner);
        List<PartnerLoginAccount> loginAccounts = partner.getLoginAccounts();
        assertNotNull(loginAccounts);
        assertTrue(loginAccounts.size() == 1);
        PartnerLoginAccount partnerLoginAccount = loginAccounts.get(0);
        assertNotNull(partnerLoginAccount);
        assertEquals(loggingAccountId, partnerLoginAccount.getLoginAccountId());

        int billingAccountId = 100;
        try {
            partner = partnerService.addBillingAccountToPartner(partner.getId(), billingAccountId);
        } catch (PartnerNotFoundException e) {
            fail();
        }
        assertNotNull(partner);
        List<PartnerBillingAccount> billingAccounts = partner.getBillingAccounts();
        assertNotNull(billingAccounts);
        assertTrue(billingAccounts.size() == 1);
        PartnerBillingAccount partnerBillingAccount = billingAccounts.get(0);
        assertNotNull(partnerBillingAccount);
        assertEquals(billingAccountId, partnerBillingAccount.getBillingAccountId());

        try {
            partnerService.activatePartner(partner.getId());
            fail();
        } catch (PartnerNotFoundException e) {
            fail();
        } catch (AlreadyInActiveStateException e) {
            assertTrue(true);
        }

        try {
            partnerService.inactivatePartner(partner.getId());
        } catch (PartnerNotFoundException e) {
            fail();
        } catch (AlreadyInactiveStateException e) {
            fail();
        }
    }
}
