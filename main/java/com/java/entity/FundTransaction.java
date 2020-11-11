package com.java.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fundtransaction")
public class FundTransaction {
	@Id
	private Long transactionId;
	private Long accountNumber;
	private Long toAccountNumber;
	private double amount;
	/* private long accountNumber; */
	private String description;
	private Date date;

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	/*
	 * public Long getFromAccountNumber() { return fromAccountNumber; }
	 * 
	 * public void setFromAccountNumber(Long fromAccountNumber) {
	 * this.fromAccountNumber = fromAccountNumber; }
	 */

	public Long getToAccountNumber() {
		return toAccountNumber;
	}

	public void setToAccountNumber(Long toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

}
