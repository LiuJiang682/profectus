package com.profectus.invoices.enums;

public enum CashMethod {
	COLLECTED("c", "Collected"),
	SUPPLIED("s", "Supplied");
	
	private String code;
	private String lable;
	
	CashMethod(final String code, final String label) {
		this.code = code;
		this.lable = label;
	}

	public String getCode() {
		return code;
	}

	public String getLable() {
		return lable;
	}
	
	public static CashMethod fromCode(final String code) {
		for (CashMethod cashMethod : CashMethod.values()) {
			if (cashMethod.getCode().equalsIgnoreCase(code)) {
				return cashMethod;
			}
		}
		
		throw new IllegalArgumentException("Unknown cash method code: " + code);
	}
}
