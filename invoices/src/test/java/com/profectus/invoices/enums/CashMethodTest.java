package com.profectus.invoices.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.profectus.common.constants.Constants.Strings;

public class CashMethodTest {

	@Test
	public void testFromCode() {
		assertEquals(CashMethod.COLLECTED, CashMethod.fromCode("c"));
		assertEquals(CashMethod.SUPPLIED, CashMethod.fromCode("S"));
	}
	
	@Test
	public void testFromCodeNull() {
		try {
			CashMethod.fromCode(null);
			fail("Program reached unexptected point");
		} catch (IllegalArgumentException e) {
			String message = e.getMessage();
			assertEquals("Unknown cash method code: null", message);
		}
	}
	
	@Test
	public void testFromCodeEmptyString() {
		try {
			CashMethod.fromCode(Strings.EMPTY);
			fail("Program reached unexptected point");
		} catch (IllegalArgumentException e) {
			String message = e.getMessage();
			assertEquals("Unknown cash method code: ", message);
		}
	}
	
	@Test
	public void testFromCodeRubbishString() {
		try {
			CashMethod.fromCode("a");
			fail("Program reached unexptected point");
		} catch (IllegalArgumentException e) {
			String message = e.getMessage();
			assertEquals("Unknown cash method code: a", message);
		}
	}
}
