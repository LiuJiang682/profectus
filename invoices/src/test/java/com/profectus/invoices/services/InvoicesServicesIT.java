package com.profectus.invoices.services;

import static org.junit.Assert.assertNotNull;

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
	}
	
}
