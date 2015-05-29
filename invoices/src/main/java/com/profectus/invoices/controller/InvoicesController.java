package com.profectus.invoices.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InvoicesController {

	private static final Logger LOGGER = Logger.getLogger(InvoicesController.class);
	
	@RequestMapping("/welcome")
	public ModelAndView helloWorld() {
 
		String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";
		return new ModelAndView("welcome", "message", message);
	}
	
	@RequestMapping("/searchInvoiceNumber")
	public ModelAndView searchByInvoiceNumber(HttpServletRequest request, HttpServletResponse response) {
		String invoiceNumber = request.getParameter("invoiceNumber");
		LOGGER.debug("invoiceNumber: " + invoiceNumber);
		String message = "SearchByInvoiceNumber ";
		return new ModelAndView("welcome", "message", message + invoiceNumber);
	}
	
	@RequestMapping("/searchInvoiceType")
	public ModelAndView searchByInvoiceType() {
		String message = "SearchByInvoiceType";
		return new ModelAndView("welcome", "message", message);
	}
}
