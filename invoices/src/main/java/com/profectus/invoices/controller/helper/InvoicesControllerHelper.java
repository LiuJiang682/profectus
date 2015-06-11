package com.profectus.invoices.controller.helper;

import org.apache.log4j.Logger;

import com.profectus.common.constants.Constants.Strings;
import com.profectus.common.utils.StringUtils;
import com.profectus.invoices.entity.Invoice;
import com.profectus.invoices.enums.CashMethod;
import com.profectus.invoices.enums.InvoiceType;
import com.profectus.invoices.model.InvoiceModel;

public class InvoicesControllerHelper {

	private static final Logger LOGGER = Logger
			.getLogger(InvoicesControllerHelper.class);

	// Private constructor
	private InvoicesControllerHelper() {
	}

	public static InvoiceModel doEntityToModelConvertion(Invoice invoiceEntity) {
		InvoiceModel invoice = new InvoiceModel();
		invoice.setInvoiceNumber(invoiceEntity.getInvoiceNumber());
		invoice.setInvoiceType(getInvoiceTypeLabel(invoiceEntity
				.getInvoiceType()));

		invoice.setInvoiceDate(invoiceEntity.getInvoiceDate());
		invoice.setTotalAmount(invoiceEntity.getTotalAmount());
		invoice.setNetAmount(invoiceEntity.getNetAmount());
		String cashMethod = invoiceEntity.getCashMethod();
		if (!StringUtils.isEmpty(cashMethod)) {
			invoice.setCashMethod(getCashMethodLabel(cashMethod));
		} else if (Strings.LABEL_INVOICE_TYPE_CASH.equalsIgnoreCase(invoice.getInvoiceType())) {
			// Data error! Invoice type is cash, however,
			// no cash method provided.
			LOGGER.error("Data ERROR! Invoice number: " + invoice.getInvoiceNumber() + " has invoice type Cash without a cash method!");
		}
		invoice.setSecurityFee(invoiceEntity.getSecurityFee());
		return invoice;
	}

	public static String getCashMethodLabel(final String cashMethod) {
		String invoiceTypeLabel = Strings.EMPTY;
		try {
			switch (CashMethod.fromCode(cashMethod)) {
			case COLLECTED:
				invoiceTypeLabel = CashMethod.COLLECTED.getLable();
				break;
			case SUPPLIED:
				invoiceTypeLabel = CashMethod.SUPPLIED.getLable();
			}
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return invoiceTypeLabel;
	}

	public static String getInvoiceTypeLabel(final String invoiceType) {
		String invoiceTypeLabel = Strings.EMPTY;

		try {
			switch (InvoiceType.fromCode(invoiceType)) {
			case CASH:
				invoiceTypeLabel = InvoiceType.CASH.getLabel();
				break;
			case SECURITY:
				invoiceTypeLabel = InvoiceType.SECURITY.getLabel();
			}
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return invoiceTypeLabel;
	}
}
