package com.profectus.invoices.controller.helper;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.profectus.common.constants.Constants.Strings;
import com.profectus.common.utils.StringUtils;
import com.profectus.invoices.enums.CashMethod;
import com.profectus.invoices.enums.InvoiceType;
import com.profectus.invoices.model.InvoiceModel;

@Component
public class InvoicesControllerHelperImpl implements InvoicesControllerHelper {

	private static final Logger LOGGER = Logger
			.getLogger(InvoicesControllerHelperImpl.class);

	public InvoiceModel doEntityToModelConvertion(InvoiceModel invoiceEntity) {
//		InvoiceModel invoice = new InvoiceModel();
//		invoice.setInvoiceNumber(invoiceEntity.getInvoiceNumber());
		invoiceEntity.setInvoiceType(getInvoiceTypeLabel(invoiceEntity
				.getInvoiceType()));

//		invoiceEntity.setInvoiceDate(invoiceEntity.getInvoiceDate());
//		invoiceEntity.setTotalAmount(invoiceEntity.getTotalAmount());
//		invoiceEntity.setNetAmount(invoiceEntity.getNetAmount());
		String cashMethod = invoiceEntity.getCashMethod();
		if (!StringUtils.isEmpty(cashMethod)) {
			invoiceEntity.setCashMethod(getCashMethodLabel(cashMethod));
		} else if (Strings.LABEL_INVOICE_TYPE_CASH.equalsIgnoreCase(invoiceEntity.getInvoiceType())) {
			// Data error! Invoice type is cash, however,
			// no cash method provided.
			LOGGER.error("Data ERROR! Invoice number: " + invoiceEntity.getInvoiceNumber() + " has invoice type Cash without a cash method!");
		}
		invoiceEntity.setSecurityFee(invoiceEntity.getSecurityFee());
		return invoiceEntity;
	}

	public String getCashMethodLabel(final String cashMethod) {
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

	public String getInvoiceTypeLabel(final String invoiceType) {
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
