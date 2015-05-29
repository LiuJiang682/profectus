package com.profectus.invoices.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class InvoiceModel implements Serializable {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = -704148720504674420L;
	
	private long invoiceNumber;
	private String invoiceType;
	private String invoiceDate;
	private BigDecimal totalAmount;
	private BigDecimal netAmount;
	private String cashMethod;
	private BigDecimal securityFee;
	
	public long getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}
	public String getCashMethod() {
		return cashMethod;
	}
	public void setCashMethod(String cashMethod) {
		this.cashMethod = cashMethod;
	}
	public BigDecimal getSecurityFee() {
		return securityFee;
	}
	public void setSecurityFee(BigDecimal securityFee) {
		this.securityFee = securityFee;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
}
