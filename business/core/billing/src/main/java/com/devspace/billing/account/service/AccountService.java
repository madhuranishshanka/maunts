package com.devspace.billing.account.service;

import com.devspace.billing.account.domain.Account;
import com.devspace.billing.account.domain.PaymentInstrument;
import com.devspace.billing.account.exceptioin.AccountNotFoundException;
import com.devspace.billing.common.domain.Amount;
import com.devspace.commons.common.exception.MissingMandatoryParamException;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface AccountService {

    Account createAccount(long ownerId, Account.Category category,PaymentInstrument paymentInstrument) throws MissingMandatoryParamException;

    Account getAccountById(long accountId) throws AccountNotFoundException;

    void transfer(long formAccountId,long toAccountId, Amount amount) ;

    Amount getBalance(long accountId);
}
