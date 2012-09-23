package com.devspace.billing.invoice.service.impl;

import com.devspace.billing.invoice.domain.Invoice;
import com.devspace.billing.invoice.domain.InvoiceItem;
import com.devspace.billing.invoice.exception.DuplicateInvoiceItemException;
import com.devspace.billing.invoice.exception.InvoiceNotFoundException;
import com.devspace.billing.invoice.repository.InvoiceRepository;
import com.devspace.billing.invoice.service.InvoiceService;
import com.devspace.commons.common.exception.MissingMandatoryParamException;
import com.devspace.persistence.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Service("invoiceService")
public class InvoiceServiceImpl implements InvoiceService {

    @Resource(name = "invoiceRepository")
    private InvoiceRepository invoiceRepository;

    public Invoice createInvoice(long userId, long orderId, List<InvoiceItem> invoiceItems)
            throws DuplicateInvoiceItemException, MissingMandatoryParamException {

        Set<InvoiceItem> invoiceItemSet = new HashSet<InvoiceItem>(invoiceItems);
        if (invoiceItems.size() != invoiceItemSet.size()) {
            throw new DuplicateInvoiceItemException("Duplicate invoice item found");
        }
        Calendar calendar = Calendar.getInstance();
        Invoice invoice = new Invoice(userId,orderId, calendar.getTime(), invoiceItemSet);

        invoiceRepository.save(invoice);

        return invoice;
    }

    public void addInvoiceItemToInvoice(long invoiceId, InvoiceItem invoiceItem) throws InvoiceNotFoundException {
        try {
            Invoice invoice = invoiceRepository.findById(invoiceId);
            invoice.addInvoiceItem(invoiceItem);
        } catch (EntityNotFoundException e) {
            throw new InvoiceNotFoundException("Invoice not found for the id " + invoiceId);
        }
    }

    public Invoice getInvoiceById(long invoiceId) throws InvoiceNotFoundException {
        try {
            return invoiceRepository.findById(invoiceId);
        } catch (EntityNotFoundException e) {
            throw new InvoiceNotFoundException("Invoice not found for the id " + invoiceId);
        }
    }

    public List<Invoice> getInvoicesByUserId(long userId) {
        return invoiceRepository.findInvoicesByUserId(userId);
    }
}
