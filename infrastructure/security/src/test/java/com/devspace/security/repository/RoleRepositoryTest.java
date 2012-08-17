package com.devspace.security.repository;

import com.devspace.multitenancy.domain.TenantContext;
import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.security.domain.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:security-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class RoleRepositoryTest {

    @Resource(name = "roleRepository")
    private RoleRepository roleRepository;

    @Test
    public void testFindByUserName() {
        TenantContext.setTenant("tenant1");

        String roleName = "guest";
        Role guestRole = new Role();
        guestRole.setName(roleName);
        guestRole.setDescription("role description");
        roleRepository.save(guestRole);

        Role persistedRole = null;
        try {
            persistedRole = roleRepository.findByName(roleName);
        } catch (EntityNotFoundException e) {
            fail();
        }
        assertNotNull(persistedRole);
        assertTrue(persistedRole.equals(guestRole));

        try {
            roleRepository.delete(persistedRole.getId());
        } catch (EntityNotFoundException e) {
            fail();
        }
    }
}
