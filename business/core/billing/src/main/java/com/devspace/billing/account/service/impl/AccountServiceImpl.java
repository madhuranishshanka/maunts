package com.devspace.billing.account.service.impl;

import com.devspace.billing.account.domain.Account;
import com.devspace.billing.account.domain.PaymentInstrument;
import com.devspace.billing.account.exceptioin.AccountNotFoundException;
import com.devspace.billing.account.repository.AccountRepository;
import com.devspace.billing.account.service.AccountService;
import com.devspace.billing.common.domain.Amount;
import com.devspace.commons.common.exception.MissingMandatoryParamException;
import com.devspace.persistence.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Resource(name = "accountRepository")
    private AccountRepository accountRepository;

    public Account createAccount(long ownerId, Account.Category category, PaymentInstrument paymentInstrument) throws
            MissingMandatoryParamException {

        Account account = new Account(paymentInstrument, ownerId, category);
        accountRepository.save(account);

        return account;
    }

    public Account getAccountById(long accountId) throws AccountNotFoundException {
        try {
            return accountRepository.findById(accountId);
        } catch (EntityNotFoundException e) {
            throw new AccountNotFoundException("Account not found for the id " + accountId);
        }
    }

    public void transfer(long formAccountId, long toAccountId, Amount amount) {
        // TODO
    }

    public Amount getBalance(long accountId) {
        // TODO
        return null;
    }
}
