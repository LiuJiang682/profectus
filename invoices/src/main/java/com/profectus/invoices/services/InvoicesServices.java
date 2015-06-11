package com.profectus.invoices.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.profectus.invoices.entity.Invoice;

@Service
public class InvoicesServices {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public Invoice find(long invoiceNumber) {
		Query query = em.createNamedQuery("Invoice.findByInvoiceNumber");
//		Query query = em.createQuery("SELECT I FROM com.profectus.invoices.entity.Invoice I where I.invoiceNumber = :invoiceNumber");
		query.setParameter("invoiceNumber", invoiceNumber);
		return (Invoice) query.getSingleResult();
	}
	
	@Transactional
	public List<Invoice> findByInvoiceType(String invoiceType, int pageSize) {
		Query query = em.createNamedQuery("Invoice.findByInvoiceType");
		query.setParameter("invoiceType", invoiceType);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
}
