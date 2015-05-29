package com.profectus.invoices.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.profectus.invoices.model.InvoiceModel;

@Controller
public class InvoicesController {

	private static final Logger LOGGER = Logger.getLogger(InvoicesController.class);
	
	@RequestMapping("/searchInvoiceNumber")
	public ModelAndView searchByInvoiceNumber(HttpServletRequest request, HttpServletResponse response) {
		String invoiceNumber = request.getParameter("invoiceNumber");
		LOGGER.debug("invoiceNumber: " + invoiceNumber);
		List<InvoiceModel> invoices = getDummies();
		return new ModelAndView("result", "invoices", invoices);
	}
	
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
	public ModelAndView searchByInvoiceType(HttpServletRequest request, HttpServletResponse response) {
		String invoiceType = request.getParameter("invoiceType");
		String pageSize = request.getParameter("pageSize");
		LOGGER.debug("invoiceType : " + invoiceType + ", pageSize: " + pageSize);
		List<InvoiceModel> invoices = getDummies();
		return new ModelAndView("result", "invoices", invoices);
	}
}