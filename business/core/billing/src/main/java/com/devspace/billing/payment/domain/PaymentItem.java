package com.devspace.billing.payment.domain;

import com.devspace.billing.accont.domain.Account;
import com.devspace.billing.invoice.domain.InvoiceItem;
import com.devspace.commons.common.domain.Amount;
import com.devspace.persistence.domain.Entity;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@javax.persistence.Entity
public class PaymentItem extends Entity {

    private InvoiceItem invoiceItem;
    private Amount paymentAmount;
    private Account userAccount;
    private Account partnerAccount;
    private Type type;

    public enum Type{
        SALES, REFUND, DISCOUNT
    }

    public PaymentItem() {
    }

    public PaymentItem(InvoiceItem invoiceItem, Amount paymentAmount, Account userAccount, Account partnerAccount,
                       Type type) {
        this.invoiceItem = invoiceItem;
        this.paymentAmount = paymentAmount;
        this.userAccount = userAccount;
        this.partnerAccount = partnerAccount;
        this.type = type;
    }

    public InvoiceItem getInvoiceItem() {
        return invoiceItem;
    }

    public Amount getPaymentAmount() {
        return paymentAmount;
    }

    public Account getUserAccount() {
        return userAccount;
    }

    public Account getPartnerAccount() {
        return partnerAccount;
    }

    public Type getType() {
        return type;
    }
}
