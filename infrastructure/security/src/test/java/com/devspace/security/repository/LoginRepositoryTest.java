package com.devspace.security.repository;

import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.security.domain.LoginAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:security-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class LoginRepositoryTest{

    @Resource(name = "loginAccountRepository")
    private LoginAccountRepository loginAccountRepository;


    @Test
    public void testFindByUserName() {
        String userName = "myUserName";
        LoginAccount loginAccount = new LoginAccount();
        loginAccount.setPassword("myPassword");
        loginAccount.setUserName(userName);
        loginAccountRepository.save(loginAccount);

        LoginAccount persistedLoginAccount = null;
        try {
            persistedLoginAccount = loginAccountRepository.findByUserName(userName);
        } catch (EntityNotFoundException e) {
            fail();
        }
        assertNotNull(persistedLoginAccount);
        assertTrue(persistedLoginAccount.equals(loginAccount));

        try {
            loginAccountRepository.delete(persistedLoginAccount.getId());
        } catch (EntityNotFoundException e) {
            fail();
        }
    }
}
