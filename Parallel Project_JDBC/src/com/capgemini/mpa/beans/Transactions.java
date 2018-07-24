package com.capgemini.mpa.beans;

import java.math.BigDecimal;
import java.sql.Date;

public class Transactions
{
	private String mobileNo;
	private String transactionType;
	private BigDecimal amount;
	
	public Transactions() {
		super();
	}

	public Transactions(String mobileNo, String transactionType, BigDecimal amount) {
		super();
		this.mobileNo = mobileNo;
		this.transactionType = transactionType;
		this.amount = amount;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transactions [mobileNo=" + mobileNo + ", transactionType=" + transactionType + ", amount=" + amount
				+ "]";
	}	
}