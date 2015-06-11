package com.profectus.common.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void testIsEmpty() {
		assertTrue(StringUtils.isEmpty(null));
		assertTrue(StringUtils.isEmpty(""));
		assertFalse(StringUtils.isEmpty(" "));
		assertFalse(StringUtils.isEmpty("abc"));
	}
	
	@Test
	public void testIsValidInteger() {
		assertFalse(StringUtils.isValidInteger(null));
		assertFalse(StringUtils.isValidInteger(""));
		assertFalse(StringUtils.isValidInteger(" "));
		assertFalse(StringUtils.isValidInteger("abc"));
		
		assertTrue(StringUtils.isValidInteger("6"));
		assertTrue(StringUtils.isValidInteger("68"));
		assertFalse(StringUtils.isValidInteger(" 6"));
		assertFalse(StringUtils.isValidInteger("6.8"));
		assertFalse(StringUtils.isValidInteger("6 "));
	}
}
