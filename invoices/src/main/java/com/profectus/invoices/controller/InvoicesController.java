package com.profectus.invoices.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.profectus.common.constants.Constants.Strings;
import com.profectus.common.utils.StringUtils;
import com.profectus.invoices.controller.helper.InvoicesControllerHelper;
import com.profectus.invoices.enums.InvoiceType;
import com.profectus.invoices.model.InvoiceModel;
import com.profectus.invoices.services.InvoicesServices;

@Controller
public class InvoicesController {

	private static final Logger LOGGER = Logger
			.getLogger(InvoicesController.class);

	private static final String DEFAULT_PAGE_SIZE = "10";
	private static final String OBJECT_INVOICES = "invoices";
	private static final String VIEW_RESULT = "result";

	
	@Autowired
	private InvoicesServices invoicesServices;
	
	@Autowired
	private InvoicesControllerHelper invoicesControllerHelper;

	@RequestMapping("/searchInvoiceNumber")
	public ModelAndView searchByInvoiceNumber(HttpServletRequest request,
			HttpServletResponse response) {
		String invoiceNumber = request.getParameter(Strings.PARAM_INVOICE_NUMBER);
		LOGGER.debug("invoiceNumber: " + invoiceNumber);

		List<InvoiceModel> invoices = new ArrayList<>();
		if (StringUtils.isValidInteger(invoiceNumber)) {
			Long invoiceNo = Long.parseLong(invoiceNumber);
			try {
				InvoiceModel invoiceEntity = invoicesServices.find(invoiceNo);
				InvoiceModel invoice = invoicesControllerHelper
						.doEntityToModelConvertion(invoiceEntity);
				invoices.add(invoice);
			} catch (Exception e) {
				LOGGER.error("Query failed.", e);
			}
		}
		return new ModelAndView(VIEW_RESULT, OBJECT_INVOICES, invoices);
	}
	
//	@RequestMapping("/searchInvoiceNumber")
//	public ModelAndView searchByInvoiceNumber(@RequestParam(value = Strings.PARAM_INVOICE_NUMBER) long invoiceNumber) {
//		List<InvoiceModel> invoices = new ArrayList<>();
//		try {
//			InvoiceModel invoiceEntity = invoicesServices.find(invoiceNumber);
//			InvoiceModel invoice = this.invoicesControllerHelper.doEntityToModelConvertion(invoiceEntity);
//			invoices.add(invoice);
//		}
//		catch (Exception e) {
//			LOGGER.error(e.getMessage(), e);
//		}
//		return new ModelAndView(VIEW_RESULT, OBJECT_INVOICES, invoices);
//	}

	@RequestMapping("/searchInvoiceType")
	public ModelAndView searchByInvoiceType(HttpServletRequest request,
			HttpServletResponse response) {
		List<InvoiceModel> invoices = new ArrayList<>();
		String invoiceTypeStr = request.getParameter(Strings.PARAM_INVOICE_TYPE);
		String pageSize = request.getParameter(Strings.PARAM_PAGE_SIZE);
		LOGGER.debug("invoiceType : " + invoiceTypeStr + ", pageSize: "
				+ pageSize);
		if (!StringUtils.isValidInteger(pageSize)) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		InvoiceType invoiceType = null;

		try {
			invoiceType = InvoiceType.fromCode(invoiceTypeStr);
			String invoiceTypeCode = invoiceType.getCode();
			int requestedPageSize = Integer.parseInt(pageSize);
			List<InvoiceModel> entities = invoicesServices.findByInvoiceType(
					invoiceTypeCode, requestedPageSize );
			for (InvoiceModel entity : entities) {
				InvoiceModel invoice = invoicesControllerHelper
						.doEntityToModelConvertion(entity);
				invoices.add(invoice);
			}

		} catch (IllegalArgumentException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (Exception ex) {
			LOGGER.error("Query failed.", ex);
		}

		return new ModelAndView(VIEW_RESULT, OBJECT_INVOICES, invoices);
	}
}
