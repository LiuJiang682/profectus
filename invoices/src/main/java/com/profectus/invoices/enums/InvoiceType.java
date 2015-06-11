package com.profectus.invoices.enums;

public enum InvoiceType {
	CASH("c", "Cash"),
	SECURITY("s", "Security");
	
	private String code;
	private String label;
	
	InvoiceType(final String code, final String label) {
		this.code = code;
		this.label = label;
	}
	
	public String getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}
	
	public static InvoiceType fromCode(final String code) {
		for (InvoiceType invoiceType : InvoiceType.values()) {
			if (invoiceType.getCode().equalsIgnoreCase(code)) {
				return invoiceType;
			}
		}
		
		throw new IllegalArgumentException("Unknown invoice type code: " + code);
	}
}
