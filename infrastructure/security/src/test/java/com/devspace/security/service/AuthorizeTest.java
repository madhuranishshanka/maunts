package com.devspace.security.service;

import com.devspace.multitenancy.domain.TenantContext;
import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.security.domain.LoginAccount;
import com.devspace.security.domain.Role;
import com.devspace.security.mock.DummyServiceBean;
import com.devspace.security.service.impl.UserLoginDetailsServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-security-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class AuthorizeTest {

    private String userName = "user";
    private String password = "pass";
    private String roleName = "GUEST";
    private LoginAccount loginAccount = null;

    @Resource(name = "dummyServiceBean")
    private DummyServiceBean testBean;

    @Resource(name = "loginAccountService")
    private LoginAccountService loginAccountService;

    @Resource(name = "userLoginDetailsService")
    private UserLoginDetailsServiceImpl userLoginDetailsService;

    private String tenantId = "tenant1";

    @Before
    public void setUp() {

        TenantContext.setTenant(tenantId);
        Set<Role> roles = new HashSet<Role>();
        Role guest = null;
        try {
            guest = loginAccountService.createRole(roleName, "guest description");
            roles.add(guest);
            loginAccount = loginAccountService.createLoginAccount(userName, password, roles);
        } catch (EntityNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @After
    public void tearDown() throws EntityNotFoundException {
        loginAccountService.deleteLoginAccount(loginAccount.getId());
        Set<Role> roles = loginAccount.getRoles();
        loginAccountService.deleteRole(roles.iterator().next().getId());
    }


    @Test
    public void testPreAuthorize() {
        TenantContext.setTenant(tenantId);
        final UserDetails userDetails = userLoginDetailsService.loadUserByUsername(userName);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userName, password, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean securedMethod = testBean.isSecuredMethod();
        assertTrue(securedMethod);
    }
}
