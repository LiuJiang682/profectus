package com.profectus.invoices.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "invoice")
@NamedQueries({
		@NamedQuery(name = "Invoice.findByInvoiceNumber", query = "SELECT I FROM com.profectus.invoices.entity.Invoice I where I.invoiceNumber = :invoiceNumber"),
		@NamedQuery(name = "Invoice.findByInvoiceType", query = "SELECT I FROM com.profectus.invoices.entity.Invoice I where I.invoiceType = :invoiceType"), })
public class Invoice implements Serializable {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = 6979145068414291675L;

	@Id
	@GeneratedValue
	@Column(name="invoice_number")
	private long invoiceNumber;
	@Column(name="invoice_type")
	private String invoiceType;
	@Column(name="invoice_date")
	private String invoiceDate;
	@Column(name="total_amount")
	private BigDecimal totalAmount;
	@Column(name="net_amount")
	private BigDecimal netAmount;
	@Column(name="cash_method")
	private String cashMethod;
	@Column(name="security_fee")
	private BigDecimal securityFee;
	
	public long getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
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
	public BigDecimal getSecurityFee() {
		return securityFee;
	}
	public void setSecurityFee(BigDecimal securityFee) {
		this.securityFee = securityFee;
	}
	public String getCashMethod() {
		return cashMethod;
	}
	public void setCashMethod(String cashMethod) {
		this.cashMethod = cashMethod;
	}

}
