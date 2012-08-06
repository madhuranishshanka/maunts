package com.devspace.security.repository.impl;

import com.devspace.persistence.exception.EntityNotFoundException;
import com.devspace.security.domain.LoginAccount;
import com.devspace.security.repository.LoginAccountRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Repository("loginAccountRepository")
public class LoginAccountRepositoryImpl extends BaseRepositoryImpl<LoginAccount> implements LoginAccountRepository {
    @Override
    public Class<LoginAccount> getClassType() {
        return LoginAccount.class;
    }

    public LoginAccount findByUserName(String userName) throws EntityNotFoundException {
        Query query = getEntityManager().createQuery("select o from LoginAccount o where o.userName =:userName");
        query.setParameter("userName", userName);
        LoginAccount loginAccount = (LoginAccount) query.getSingleResult();

        if (loginAccount == null) {
            throw new EntityNotFoundException(LoginAccount.class, "No Login Account found for the given user name" +
                    userName);
        }
        return loginAccount;
    }
}
