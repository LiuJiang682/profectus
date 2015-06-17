package com.profectus.invoices.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.profectus.invoices.dao.InvoiceDAO;
import com.profectus.invoices.entity.Invoice;
import com.profectus.invoices.model.InvoiceModel;

@Service
public class InvoicesServices {

	@Autowired
	private InvoiceDAO invoiceDao;

	@Transactional
	public InvoiceModel find(long invoiceNumber) {
		Invoice invoice = invoiceDao.find(invoiceNumber);
		return new InvoiceModel(invoice);
	}
	
	@Transactional
	public List<InvoiceModel> findByInvoiceType(String invoiceType, int pageSize) {
		List<Invoice> entities = invoiceDao.findByInvoiceType(invoiceType, pageSize);
		List<InvoiceModel> invoices = new ArrayList<>();
		for (Invoice entity : entities) {
			InvoiceModel invoice = new InvoiceModel(entity);
			invoices.add(invoice);
		}
		
		return invoices;
	}
}
