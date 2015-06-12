package com.profectus.invoices.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.profectus.invoices.entity.Invoice;

@ContextConfiguration(locations = {"/test-application-context.xml" })
@TransactionConfiguration(defaultRollback=true)
public class InvoicesServicesIT extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private InvoicesServices invoicesServices;
	
	@Test
	public void testFind() {
		Invoice invoice = this.invoicesServices.find(1l);
		assertNotNull(invoice);
		assertTrue(1 == invoice.getInvoiceNumber());
		assertEquals("c", invoice.getInvoiceType());
		assertEquals("11/06/2015", invoice.getInvoiceDate());
		assertEquals(new BigDecimal(330).setScale(2), invoice.getTotalAmount());
		assertEquals(new BigDecimal(300).setScale(2), invoice.getNetAmount());
		assertEquals("s", invoice.getCashMethod());
		assertNull(invoice.getSecurityFee());
	}
	
	@Test
	public void testFindByInvoiceType() {
		List<Invoice> invoices = this.invoicesServices.findByInvoiceType("c", 10);
		assertNotNull(invoices);
		assertTrue(invoices.size() == 10);
	}
	
}
