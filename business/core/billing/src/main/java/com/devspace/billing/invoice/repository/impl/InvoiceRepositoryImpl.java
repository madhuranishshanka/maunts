package com.devspace.billing.invoice.repository.impl;

import com.devspace.billing.common.repository.impl.BillingBaseRepositoryImpl;
import com.devspace.billing.invoice.domain.Invoice;
import com.devspace.billing.invoice.repository.InvoiceRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Madhura Nishshanka
 * @since 1.0
 */
@Repository("invoiceRepository")
public class InvoiceRepositoryImpl extends BillingBaseRepositoryImpl<Invoice> implements InvoiceRepository {

    @Override
    public Class<Invoice> getClassType() {
        return Invoice.class;
    }

    public List<Invoice> findInvoicesByUserId(long userId) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Invoice> query = criteriaBuilder.createQuery(Invoice.class);
        Root<Invoice> from = query.from(Invoice.class);
        CriteriaQuery<Invoice> select = query.select(from);

        Predicate userIdPredicate = criteriaBuilder.equal(from.<Long>get("userId"), userId);
        select.where(userIdPredicate);

        TypedQuery<Invoice> typedQuery = getEntityManager().createQuery(select);
        return typedQuery.getResultList();


    }
}
