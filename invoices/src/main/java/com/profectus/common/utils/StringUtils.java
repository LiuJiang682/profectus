package com.profectus.common.utils;

import com.profectus.common.constants.Constants.Numeral;

public class StringUtils {

	private static final String REGEX_INTEGER = "^\\d+$";

	// Private constructor.
	private StringUtils() {	
	}
	
	public static boolean isEmpty(final String str) {
		return (null == str) || (Numeral.ZERO == str.length());
	}
	
	public static boolean isValidInteger(final String str) {
		return (!isEmpty(str)) && (str.matches(REGEX_INTEGER));
	}
}
