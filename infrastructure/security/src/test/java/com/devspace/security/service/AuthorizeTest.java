package com.devspace.security.service;

import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.security.domain.LoginAccount;
import com.devspace.security.domain.Role;
import com.devspace.security.mock.TestBean;
import com.devspace.security.service.impl.UserLoginDetailsServiceImpl;
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

    @Resource(name = "testBean")
    private TestBean testBean;

    @Resource(name = "loginAccountService")
    private LoginAccountService loginAccountService;


    @Resource(name = "userLoginDetailsService")
    private UserLoginDetailsServiceImpl userLoginDetailsService;

    @Test
    public void testPreAuthorize() {
        String userName = "user";
        String password = "pass";
        createLoginAccount("GUEST", userName, password);
        final UserDetails userDetails = userLoginDetailsService.loadUserByUsername(userName);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userName, password,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean securedMethod = testBean.isSecuredMethod();
        assertTrue(securedMethod);
    }

    private LoginAccount createLoginAccount(String roleName, String userName, String password) {
        Set<Role> roles = new HashSet<Role>();

        Role guest = null;
        LoginAccount loginAccount = null;
        try {
            guest = loginAccountService.createRole(roleName, "guest description");
            roles.add(guest);
            loginAccount = loginAccountService.createLoginAccount(userName, password, roles);
        } catch (EntityNotFoundException e) {
            fail(e.getMessage());
        }
        return loginAccount;
    }
}
