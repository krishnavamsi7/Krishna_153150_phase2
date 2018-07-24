package com.capgemini.mpa.beans;

import java.math.BigDecimal;

public class Wallet 
{
	private BigDecimal balance;

	public Wallet() 
	{
		super();
	}

	public Wallet(BigDecimal amount) 
	{
		super();
		this.balance = amount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return ", Balance=" + balance;
	}
	
	
	
	
}
