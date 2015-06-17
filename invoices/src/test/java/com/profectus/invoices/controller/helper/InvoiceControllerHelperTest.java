package com.profectus.invoices.controller.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.profectus.common.constants.Constants.Strings;
import com.profectus.invoices.entity.Invoice;
import com.profectus.invoices.model.InvoiceModel;

public class InvoiceControllerHelperTest {

	private InvoicesControllerHelperImpl testInstance;
	
	@Before
	public void setUp() {
		this.testInstance = new InvoicesControllerHelperImpl();
	}
	
	@After
	public void tearDown() {
		this.testInstance = null;
	}
	@Test
	public void testGetInvoiceTypeLabel() {
		assertEquals(Strings.EMPTY, testInstance.getInvoiceTypeLabel(Strings.EMPTY));
		assertEquals(Strings.EMPTY, testInstance.getInvoiceTypeLabel("g"));
		assertEquals("Cash", testInstance.getInvoiceTypeLabel("C"));
		assertEquals("Security", testInstance.getInvoiceTypeLabel("s"));
	}
	
	@Test
	public void testGetCashMethodLabel() {
		assertEquals(Strings.EMPTY, testInstance.getCashMethodLabel(Strings.EMPTY));
		assertEquals(Strings.EMPTY, testInstance.getCashMethodLabel("g"));
		assertEquals("Collected", testInstance.getCashMethodLabel("C"));
		assertEquals("Supplied", testInstance.getCashMethodLabel("s"));
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
		InvoiceModel model = testInstance.doEntityToModelConvertion(new InvoiceModel(invoiceEntity));
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
		
		InvoiceModel model = testInstance.doEntityToModelConvertion(new InvoiceModel(invoiceEntity));
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
		
		InvoiceModel model = testInstance.doEntityToModelConvertion(new InvoiceModel(invoiceEntity));
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
		InvoiceModel model = testInstance.doEntityToModelConvertion(new InvoiceModel(invoiceEntity));
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
		InvoiceModel model = testInstance.doEntityToModelConvertion(new InvoiceModel(invoiceEntity));
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
