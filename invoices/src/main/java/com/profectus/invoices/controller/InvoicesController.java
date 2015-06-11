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
import com.profectus.invoices.controller.helper.InvoicesControllerHelper;
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
				InvoiceModel invoice = InvoicesControllerHelper.doEntityToModelConvertion(invoiceEntity);
				invoices.add(invoice);
			} catch (Exception e) {
				LOGGER.error("Query failed.", e);
			}
		}
		return new ModelAndView("result", "invoices", invoices);
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
			InvoiceModel invoice = InvoicesControllerHelper.doEntityToModelConvertion(entity);
			invoices.add(invoice);
		}

		return new ModelAndView("result", "invoices", invoices);
	}
}
