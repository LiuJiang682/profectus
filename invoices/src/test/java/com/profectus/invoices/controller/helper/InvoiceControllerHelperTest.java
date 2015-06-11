package com.profectus.invoices.controller.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import com.profectus.common.constants.Constants.Strings;
import com.profectus.invoices.entity.Invoice;
import com.profectus.invoices.model.InvoiceModel;

public class InvoiceControllerHelperTest {

	@Test
	public void testGetInvoiceTypeLabel() {
		assertEquals(Strings.EMPTY, InvoicesControllerHelper.getInvoiceTypeLabel(Strings.EMPTY));
		assertEquals(Strings.EMPTY, InvoicesControllerHelper.getInvoiceTypeLabel("g"));
		assertEquals("Cash", InvoicesControllerHelper.getInvoiceTypeLabel("C"));
		assertEquals("Security", InvoicesControllerHelper.getInvoiceTypeLabel("s"));
	}
	
	@Test
	public void testGetCashMethodLabel() {
		assertEquals(Strings.EMPTY, InvoicesControllerHelper.getCashMethodLabel(Strings.EMPTY));
		assertEquals(Strings.EMPTY, InvoicesControllerHelper.getCashMethodLabel("g"));
		assertEquals("Collected", InvoicesControllerHelper.getCashMethodLabel("C"));
		assertEquals("Supplied", InvoicesControllerHelper.getCashMethodLabel("s"));
	}
	
	@Test
	public void testDoEntityToModelConvertion() {
		Invoice invoiceEntity = new Invoice();
		invoiceEntity.setInvoiceNumber(1l);
		invoiceEntity.setInvoiceType("c");
		invoiceEntity.setInvoiceDate("11/06/2015");
		invoiceEntity.setTotalAmount(new BigDecimal(330));
		invoiceEntity.setNetAmount(new BigDecimal(300));
		invoiceEntity.setCashMethod("c");
		InvoiceModel model = InvoicesControllerHelper.doEntityToModelConvertion(invoiceEntity);
		assertNotNull(model);
		assertTrue(model.getInvoiceNumber() == 1l);
		assertEquals("Cash", model.getInvoiceType());
		assertEquals("11/06/2015", model.getInvoiceDate());
		assertEquals(new BigDecimal(330), model.getTotalAmount());
		assertEquals(new BigDecimal(300), model.getNetAmount());
		assertEquals("Collected", model.getCashMethod());
		assertNull(model.getSecurityFee());
	}
	
	@Test
	public void testDoEntityToModelConvertionNoCashMethod() {
		Invoice invoiceEntity = new Invoice();
		invoiceEntity.setInvoiceNumber(1l);
		invoiceEntity.setInvoiceType("c");
		invoiceEntity.setInvoiceDate("11/06/2015");
		invoiceEntity.setTotalAmount(new BigDecimal(330));
		invoiceEntity.setNetAmount(new BigDecimal(300));
		
		InvoiceModel model = InvoicesControllerHelper.doEntityToModelConvertion(invoiceEntity);
		assertNotNull(model);
		assertTrue(model.getInvoiceNumber() == 1l);
		assertEquals("Cash", model.getInvoiceType());
		assertEquals("11/06/2015", model.getInvoiceDate());
		assertEquals(new BigDecimal(330), model.getTotalAmount());
		assertEquals(new BigDecimal(300), model.getNetAmount());
		assertNull(model.getCashMethod());
		assertNull(model.getSecurityFee());
	}
	
	@Test 
	public void testDoEntityToModelConvertionInvalidInvoiceType() {
		Invoice invoiceEntity = new Invoice();
		invoiceEntity.setInvoiceNumber(1l);
		invoiceEntity.setInvoiceType("a");
		invoiceEntity.setInvoiceDate("11/06/2015");
		invoiceEntity.setTotalAmount(new BigDecimal(330));
		invoiceEntity.setNetAmount(new BigDecimal(300));
		
		InvoiceModel model = InvoicesControllerHelper.doEntityToModelConvertion(invoiceEntity);
		assertNotNull(model);
		assertTrue(model.getInvoiceNumber() == 1l);
		assertEquals(Strings.EMPTY, model.getInvoiceType());
		assertEquals("11/06/2015", model.getInvoiceDate());
		assertEquals(new BigDecimal(330), model.getTotalAmount());
		assertEquals(new BigDecimal(300), model.getNetAmount());
		assertNull(model.getCashMethod());
		assertNull(model.getSecurityFee());
	}
	
	@Test 
	public void testDoEntityToModelConvertionInvalidCashMethod() {
		Invoice invoiceEntity = new Invoice();
		invoiceEntity.setInvoiceNumber(1l);
		invoiceEntity.setInvoiceType("c");
		invoiceEntity.setInvoiceDate("11/06/2015");
		invoiceEntity.setTotalAmount(new BigDecimal(330));
		invoiceEntity.setNetAmount(new BigDecimal(300));
		invoiceEntity.setCashMethod("a");
		InvoiceModel model = InvoicesControllerHelper.doEntityToModelConvertion(invoiceEntity);
		assertNotNull(model);
		assertTrue(model.getInvoiceNumber() == 1l);
		assertEquals("Cash", model.getInvoiceType());
		assertEquals("11/06/2015", model.getInvoiceDate());
		assertEquals(new BigDecimal(330), model.getTotalAmount());
		assertEquals(new BigDecimal(300), model.getNetAmount());
		assertEquals(Strings.EMPTY, model.getCashMethod());
		assertNull(model.getSecurityFee());
	}
	
	@Test
	public void testDoEntityToModelConvertionSecurity() {
		Invoice invoiceEntity = new Invoice();
		invoiceEntity.setInvoiceNumber(1l);
		invoiceEntity.setInvoiceType("s");
		invoiceEntity.setInvoiceDate("11/06/2015");
		invoiceEntity.setTotalAmount(new BigDecimal(330));
		invoiceEntity.setNetAmount(new BigDecimal(300));
		invoiceEntity.setCashMethod(null);
		invoiceEntity.setSecurityFee(new BigDecimal(2));
		InvoiceModel model = InvoicesControllerHelper.doEntityToModelConvertion(invoiceEntity);
		assertNotNull(model);
		assertTrue(model.getInvoiceNumber() == 1l);
		assertEquals("Security", model.getInvoiceType());
		assertEquals("11/06/2015", model.getInvoiceDate());
		assertEquals(new BigDecimal(330), model.getTotalAmount());
		assertEquals(new BigDecimal(300), model.getNetAmount());
		assertNull(model.getCashMethod());
		assertEquals(new BigDecimal(2), model.getSecurityFee());
	}
}
