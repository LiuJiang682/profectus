package com.profectus.invoices.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.profectus.common.constants.Constants.Strings;

public class InvoiceTypeTest {

	@Test
	public void testFromCode() {
		assertEquals(InvoiceType.CASH, InvoiceType.fromCode("c"));
		assertEquals(InvoiceType.SECURITY, InvoiceType.fromCode("S"));
	}
	
	@Test
	public void testFromCodeNull() {
		try {
			InvoiceType.fromCode(null);
			fail("Program reached unexptected point");
		} catch (IllegalArgumentException e) {
			String message = e.getMessage();
			assertEquals("Unknown invoice type code: null", message);
		}
	}
	
	@Test
	public void testFromCodeEmptyString() {
		try {
			InvoiceType.fromCode(Strings.EMPTY);
			fail("Program reached unexptected point");
		} catch (IllegalArgumentException e) {
			String message = e.getMessage();
			assertEquals("Unknown invoice type code: ", message);
		}
	}
	
	@Test
	public void testFromCodeRubbishString() {
		try {
			InvoiceType.fromCode("a");
			fail("Program reached unexptected point");
		} catch (IllegalArgumentException e) {
			String message = e.getMessage();
			assertEquals("Unknown invoice type code: a", message);
		}
	}
}
