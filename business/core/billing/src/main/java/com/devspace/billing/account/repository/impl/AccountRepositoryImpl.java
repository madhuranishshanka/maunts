package com.devspace.billing.account.repository.impl;

import com.devspace.billing.account.domain.Account;
import com.devspace.billing.account.repository.AccountRepository;
import com.devspace.billing.common.repository.impl.BaseRepositoryImpl;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public class AccountRepositoryImpl extends BaseRepositoryImpl<Account> implements AccountRepository {
    @Override
    public Class<Account> getClassType() {
        return Account.class;
    }
}
