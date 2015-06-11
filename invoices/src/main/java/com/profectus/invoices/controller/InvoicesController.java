package com.profectus.invoices.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.profectus.common.constants.Constants.Strings;
import com.profectus.common.utils.StringUtils;
import com.profectus.invoices.entity.Invoice;
import com.profectus.invoices.model.InvoiceModel;
import com.profectus.invoices.services.InvoicesServices;

@Controller
public class InvoicesController {

	private static final Logger LOGGER = Logger
			.getLogger(InvoicesController.class);

	@Autowired
	private InvoicesServices invoicesServices;

	@RequestMapping("/searchInvoiceNumber")
	public ModelAndView searchByInvoiceNumber(HttpServletRequest request,
			HttpServletResponse response) {
		String invoiceNumber = request.getParameter(Strings.INVOICE_NUMBER);
		LOGGER.debug("invoiceNumber: " + invoiceNumber);

		List<InvoiceModel> invoices = new ArrayList<>();
		if (StringUtils.isValidInteger(invoiceNumber)) {
			Long invoiceNo = Long.parseLong(invoiceNumber);
			try {
				Invoice invoiceEntity = invoicesServices.find(invoiceNo);
				InvoiceModel invoice = doEntityToModelConvertion(invoiceEntity);
				invoices.add(invoice);
			} catch (Exception e) {
				LOGGER.error("Query failed.", e);
			}
		}
		return new ModelAndView("result", "invoices", invoices);
	}

	private InvoiceModel doEntityToModelConvertion(Invoice invoiceEntity) {
		InvoiceModel invoice = new InvoiceModel();
		invoice.setInvoiceNumber(invoiceEntity.getInvoiceNumber());
		invoice.setInvoiceType("c".equals(invoiceEntity.getInvoiceType()) ? "Cash"
				: "Security");
		// invoice.setInvoiceDate(doDateToStringConvertion(invoiceEntity.getInvoiceDate()));
		invoice.setInvoiceDate(invoiceEntity.getInvoiceDate());
		invoice.setTotalAmount(invoiceEntity.getTotalAmount());
		invoice.setNetAmount(invoiceEntity.getNetAmount());
		String cashMethod = invoiceEntity.getCashMethod();
		if (!StringUtils.isEmpty(cashMethod)) {
			invoice.setCashMethod("c".equals(cashMethod) ? "Collected"
					: "Supplied");
		}
		invoice.setSecurityFee(invoiceEntity.getSecurityFee());
		return invoice;
	}

	// private String doDateToStringConvertion(String invoiceDate) {
	// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	// return sdf.format(invoiceDate);
	// }

	private List<InvoiceModel> getDummies() {
		List<InvoiceModel> invoices = new ArrayList<>();
		InvoiceModel invoice = new InvoiceModel();
		invoice.setInvoiceNumber(1);
		invoice.setInvoiceType("Cash");
		invoice.setInvoiceDate("27/05/2015");
		invoice.setTotalAmount(new BigDecimal(330));
		invoice.setNetAmount(new BigDecimal(300));
		invoice.setCashMethod("Supplied");
		invoices.add(invoice);
		return invoices;
	}

	@RequestMapping("/searchInvoiceType")
	public ModelAndView searchByInvoiceType(HttpServletRequest request,
			HttpServletResponse response) {
		String invoiceType = request.getParameter("invoiceType");
		String pageSize = request.getParameter("pageSize");
		LOGGER.debug("invoiceType : " + invoiceType + ", pageSize: " + pageSize);

		List<InvoiceModel> invoices = new ArrayList<>();
		List<Invoice> entities = invoicesServices.findByInvoiceType(
				invoiceType, Integer.parseInt(pageSize));
		for (Invoice entity : entities) {
			InvoiceModel invoice = doEntityToModelConvertion(entity);
			invoices.add(invoice);
		}

		return new ModelAndView("result", "invoices", invoices);
	}
}
