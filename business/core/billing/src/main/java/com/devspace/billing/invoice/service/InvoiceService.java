package com.devspace.billing.invoice.service;

import com.devspace.billing.invoice.domain.Invoice;
import com.devspace.billing.invoice.domain.InvoiceItem;
import com.devspace.billing.invoice.exception.DuplicateInvoiceItemException;
import com.devspace.billing.invoice.exception.InvoiceNotFoundException;
import com.devspace.commons.common.exception.MissingMandatoryParamException;

import java.util.List;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface InvoiceService {

    Invoice createInvoice(long userId, long orderId, List<InvoiceItem> invoiceItems) throws DuplicateInvoiceItemException, MissingMandatoryParamException;

    void addInvoiceItemToInvoice(long invoiceId, InvoiceItem invoiceItem) throws InvoiceNotFoundException;

    Invoice getInvoiceById(long invoiceId) throws InvoiceNotFoundException;

    List<Invoice> getInvoicesByUserId(long userId);
}
