package com.capgemini.mpa.repo;

import java.util.List;

import com.capgemini.mpa.beans.Customer;
import com.capgemini.mpa.beans.Transactions;

public interface WalletRepo 
{
	public boolean save(Customer customer);
	
	public Customer findOne(String mobileNo);
	
	public Customer Update(Customer customer, String transactionType);
	
	public List<Transactions> transactions(String mobileNo);
}
