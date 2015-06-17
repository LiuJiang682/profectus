package com.profectus.invoices.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import com.profectus.common.constants.Constants.Numeral;
import com.profectus.common.constants.Constants.Strings;
import com.profectus.invoices.entity.Invoice;
import com.profectus.invoices.model.InvoiceModel;
import com.profectus.invoices.services.InvoicesServices;

public class InvoicesControllerTest {

	@Mock
	private InvoicesServices mockServices;
	@Mock
	private HttpServletRequest mockRequest;
	@Mock
	private HttpServletResponse mockResponse;
	@Mock
	private Invoice mockInvoice;
	@Mock
	private List<Invoice> mockList;
	@Mock
	private Iterator<Invoice> mockIterator;
	
	@InjectMocks
	private InvoicesController testInstance;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
//	@Test
//	public void testSearchByInvoiceNumber() {
//		when(this.mockRequest.getParameter(Strings.PARAM_INVOICE_NUMBER)).thenReturn("1");
//		when(this.mockServices.find(1l)).thenReturn(mockInvoice);
//		when(this.mockInvoice.getInvoiceNumber()).thenReturn(1l);
//		when(this.mockInvoice.getInvoiceType()).thenReturn("c");
//		when(this.mockInvoice.getInvoiceDate()).thenReturn("11/06/2015");
//		when(this.mockInvoice.getTotalAmount()).thenReturn(new BigDecimal(330));
//		when(this.mockInvoice.getNetAmount()).thenReturn(new BigDecimal(300));
//		when(this.mockInvoice.getCashMethod()).thenReturn("s");
//		ModelAndView mav = this.testInstance.searchByInvoiceNumber(mockRequest, mockResponse);
//		assertNotNull(mav);
//		Map<String, Object> models = mav.getModel();
//		verifyNumberOfInvoices(models, 1);
//		verify(this.mockServices).find(Matchers.anyLong());
//	}
	
	@Test
	public void testSearchByInvoiceNumberNull() {
		when(this.mockRequest.getParameter(Strings.PARAM_INVOICE_NUMBER)).thenReturn(null);
		
		ModelAndView mav = this.testInstance.searchByInvoiceNumber(mockRequest, mockResponse);
		assertNotNull(mav);
		Map<String, Object> models = mav.getModel();
		verifyNumberOfInvoices(models, Numeral.ZERO);
		verify(this.mockServices, never()).find(Matchers.anyLong());
	}
	
	@Test
	public void testSearchByInvoiceNumberEmptyString() {
		when(this.mockRequest.getParameter(Strings.PARAM_INVOICE_NUMBER)).thenReturn("");
		
		ModelAndView mav = this.testInstance.searchByInvoiceNumber(mockRequest, mockResponse);
		assertNotNull(mav);
		Map<String, Object> models = mav.getModel();
		verifyNumberOfInvoices(models, Numeral.ZERO);
		verify(this.mockServices, never()).find(Matchers.anyLong());
	}
	
	@Test
	public void testSearchByInvoiceNumberInvalidString() {
		when(this.mockRequest.getParameter(Strings.PARAM_INVOICE_NUMBER)).thenReturn("abc");
		
		ModelAndView mav = this.testInstance.searchByInvoiceNumber(mockRequest, mockResponse);
		assertNotNull(mav);
		Map<String, Object> models = mav.getModel();
		verifyNumberOfInvoices(models, Numeral.ZERO);
		verify(this.mockServices, never()).find(Matchers.anyLong());
	}
	
	@Test
	public void testSearchByInvoiceNumberServicesException() {
		when(this.mockRequest.getParameter(Strings.PARAM_INVOICE_NUMBER)).thenReturn("1");
		when(this.mockServices.find(1l)).thenThrow(new RuntimeException("test"));
		ModelAndView mav = this.testInstance.searchByInvoiceNumber(mockRequest, mockResponse);
		assertNotNull(mav);
		Map<String, Object> models = mav.getModel();
		verifyNumberOfInvoices(models, Numeral.ZERO);
		verify(this.mockServices).find(1l);
	}
	
//	@Test
//	public void testSearchByInvoiceType() {
//		when(this.mockRequest.getParameter(Strings.PARAM_INVOICE_TYPE)).thenReturn("C");
//		when(this.mockRequest.getParameter(Strings.PARAM_PAGE_SIZE)).thenReturn("10");
//		when(this.mockServices.findByInvoiceType(Matchers.anyString(), Matchers.anyInt())).thenReturn(mockList);
//		when(this.mockList.iterator()).thenReturn(mockIterator);
//		when(this.mockIterator.hasNext()).thenReturn(true, true, false);
//		when(this.mockIterator.next()).thenReturn(mockInvoice);
//		ModelAndView mav = this.testInstance.searchByInvoiceType(mockRequest, mockResponse);
//		assertNotNull(mav);
//		Map<String, Object> models = mav.getModel();
//		verifyNumberOfInvoices(models, 2);
//		verify(this.mockServices).findByInvoiceType("c", 10);
//	}
//	
//	@Test
//	public void testSearchByInvoiceTypeInvalidPageSize() {
//		when(this.mockRequest.getParameter(Strings.PARAM_INVOICE_TYPE)).thenReturn("C");
//		when(this.mockRequest.getParameter(Strings.PARAM_PAGE_SIZE)).thenReturn("a");
//		when(this.mockServices.findByInvoiceType(Matchers.anyString(), Matchers.anyInt())).thenReturn(mockList);
//		when(this.mockList.iterator()).thenReturn(mockIterator);
//		when(this.mockIterator.hasNext()).thenReturn(true, true, false);
//		when(this.mockIterator.next()).thenReturn(mockInvoice);
//		ModelAndView mav = this.testInstance.searchByInvoiceType(mockRequest, mockResponse);
//		assertNotNull(mav);
//		Map<String, Object> models = mav.getModel();
//		verifyNumberOfInvoices(models, 2);
//		verify(this.mockServices).findByInvoiceType("c", 10);
//	}
	
	@Test
	public void testSearchByInvoiceTypeInvalidInvoiceType() {
		when(this.mockRequest.getParameter(Strings.PARAM_INVOICE_TYPE)).thenReturn("a");
		when(this.mockRequest.getParameter(Strings.PARAM_PAGE_SIZE)).thenReturn("10");
		
		ModelAndView mav = this.testInstance.searchByInvoiceType(mockRequest, mockResponse);
		assertNotNull(mav);
		Map<String, Object> models = mav.getModel();
		verifyNumberOfInvoices(models, Numeral.ZERO);
		verify(this.mockServices, never()).findByInvoiceType(Matchers.anyString(), Matchers.anyInt());
	}
	
	@Test
	public void testSearchByInvoiceTypeServicesException() {
		when(this.mockRequest.getParameter(Strings.PARAM_INVOICE_TYPE)).thenReturn("C");
		when(this.mockServices.findByInvoiceType(Matchers.anyString(), Matchers.anyInt())).thenThrow(new RuntimeException("test"));
		ModelAndView mav = this.testInstance.searchByInvoiceType(mockRequest, mockResponse);
		assertNotNull(mav);
		Map<String, Object> models = mav.getModel();
		verifyNumberOfInvoices(models, Numeral.ZERO);
		verify(this.mockServices).findByInvoiceType(Matchers.anyString(), Matchers.anyInt());
	}

	private void verifyNumberOfInvoices(Map<String, Object> models, final int expectedNumberOfInvoices) {
		Object object = models.get("invoices");
		assertNotNull(object);
		List<InvoiceModel> invoices = (List<InvoiceModel>) object;
		assertTrue(invoices.size() == expectedNumberOfInvoices);
	}
}
