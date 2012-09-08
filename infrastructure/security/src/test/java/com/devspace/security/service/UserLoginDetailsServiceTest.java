package com.devspace.security.service;

import com.devspace.multitenancy.domain.TenantContext;
import com.devspace.security.domain.LoginAccount;
import com.devspace.security.domain.Role;
import com.devspace.security.exception.LoginAccountNotFound;
import com.devspace.security.exception.RoleNotFoundException;
import com.devspace.security.service.impl.UserLoginDetailsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:security-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class UserLoginDetailsServiceTest {

    @Resource(name = "loginAccountService")
    private LoginAccountService loginAccountService;


    @Resource(name = "userLoginDetailsService")
    private UserLoginDetailsServiceImpl userLoginDetailsService;


    @Test
    public void testFindByUserName() {
        TenantContext.setTenant("tenant1");

        Set<String> roles = new HashSet<String>();

        Role guest = null;
        LoginAccount loginAccount = null;
        try {
            guest = loginAccountService.createRole("guest", "guest description");
            roles.add(guest.getName());
            loginAccount = loginAccountService.createLoginAccount("userName", "password", roles);
        } catch (RoleNotFoundException e) {
            fail(e.getMessage());
        }

        UserDetails userDetails = userLoginDetailsService.loadUserByUsername(loginAccount.getUserName());

        assertNotNull(userDetails);
        assertEquals(loginAccount.getUserName(), userDetails.getUsername());
        assertEquals(loginAccount.getPassword(), userDetails.getPassword());
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        assertNotNull(authorities);
        assertEquals(1, authorities.size());
        assertEquals(authorities.iterator().next().getAuthority(), guest.getName());

        try {
            loginAccountService.deleteLoginAccount(loginAccount.getId());
        } catch (LoginAccountNotFound e) {
            fail(e.getMessage());
        }


        try {
            loginAccountService.deleteRole(guest.getId());
        } catch (RoleNotFoundException e) {
            fail(e.getMessage());
        }

    }
}
