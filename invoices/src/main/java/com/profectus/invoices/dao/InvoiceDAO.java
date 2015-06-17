package com.profectus.invoices.dao;

import java.util.List;

import com.profectus.invoices.entity.Invoice;

public interface InvoiceDAO {

	public Invoice find(final long invoiceNumber);
	public List<Invoice> findByInvoiceType(final String invoiceType, final int pageSize);
	
}
