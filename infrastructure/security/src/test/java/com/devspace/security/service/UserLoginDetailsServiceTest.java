package com.devspace.security.service;

import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.security.domain.LoginAccount;
import com.devspace.security.domain.Role;
import com.devspace.security.repository.LoginAccountRepository;
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
        Set<Role> roles = new HashSet<Role>();

        Role guest = null;
        LoginAccount loginAccount = null;
        try {
            guest = loginAccountService.createRole("guest", "guest description");
            roles.add(guest);
            loginAccount = loginAccountService.createLoginAccount("userName", "password", roles);
        } catch (EntityNotFoundException e) {
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
        } catch (EntityNotFoundException e) {
            fail(e.getMessage());
        }

        try {
            loginAccountService.deleteRole(guest.getId());
        } catch (EntityNotFoundException e) {
            fail(e.getMessage());
        }

    }
}
