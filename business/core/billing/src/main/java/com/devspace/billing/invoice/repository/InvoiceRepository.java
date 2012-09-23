package com.devspace.billing.invoice.repository;

import com.devspace.billing.invoice.domain.Invoice;
import com.devspace.persistence.repository.CrudRepository;

import java.util.List;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
public interface InvoiceRepository extends CrudRepository<Invoice>{

    List<Invoice> findInvoicesByUserId(long userId);
}
