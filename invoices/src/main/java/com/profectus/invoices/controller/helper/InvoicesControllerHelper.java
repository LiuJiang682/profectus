package com.profectus.invoices.controller.helper;

import com.profectus.invoices.model.InvoiceModel;

public interface InvoicesControllerHelper {

	public InvoiceModel doEntityToModelConvertion(InvoiceModel invoiceEntity);
	public String getCashMethodLabel(final String cashMethod);
	public String getInvoiceTypeLabel(final String invoiceType);
}
