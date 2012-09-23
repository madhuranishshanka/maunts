package com.devspace.billing.payment.domain;

import com.devspace.billing.account.domain.Account;
import com.devspace.billing.invoice.domain.Invoice;
import com.devspace.billing.payment.exceptioin.InvalidPaymentItemException;
import com.devspace.commons.common.exception.MissingMandatoryParamException;
import com.devspace.persistence.domain.Entity;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class Payment extends Entity {

    private long partnerId;
    @OneToOne
    private Invoice invoice;
    @OneToMany
    private Set<PaymentItem> paymentItems = new HashSet<PaymentItem>();

    public Payment() {
    }

    public Payment(long partnerId, Invoice invoice, Set<PaymentItem> paymentItems) throws
            InvalidPaymentItemException, MissingMandatoryParamException {
        if (invoice == null) {
            throw new MissingMandatoryParamException("Missing invoice");
        }
        this.partnerId = partnerId;
        this.invoice = invoice;
        this.paymentItems = paymentItems;
        validatePaymentItems(paymentItems);
    }

    public void addPaymentItem(PaymentItem paymentItem) throws InvalidPaymentItemException {
        Set<PaymentItem> allPaymentItems = new HashSet<PaymentItem>(this.paymentItems);
        allPaymentItems.add(paymentItem);
        validatePaymentItems(allPaymentItems);
        this.paymentItems.add(paymentItem);
    }

    private void validatePaymentItems(Set<PaymentItem> paymentItems) throws InvalidPaymentItemException {

        Account prevPartnerAccount = null;
        Account prevUserAccount = null;
        boolean prevAccounts = false;
        for (PaymentItem paymentItem : paymentItems) {

            if (!prevAccounts) {
                prevPartnerAccount = paymentItem.getPartnerAccount();
                prevUserAccount = paymentItem.getUserAccount();
                prevAccounts = true;
            }

            if (!prevUserAccount.equals(paymentItem.getUserAccount())) {
                throw new InvalidPaymentItemException("Can not have payment items for different partner accounts");
            }

            if (!prevPartnerAccount.equals(paymentItem.getPartnerAccount())) {
                throw new InvalidPaymentItemException("Can not have payment items for different user accounts");
            }

        }
    }

    public long getPartnerId() {
        return partnerId;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public Set<PaymentItem> getPaymentItems() {
        return Collections.unmodifiableSet(paymentItems);
    }
}
