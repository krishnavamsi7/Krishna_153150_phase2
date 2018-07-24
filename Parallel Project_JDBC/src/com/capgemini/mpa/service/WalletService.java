package com.capgemini.mpa.service;

import java.math.BigDecimal;
import java.util.List;

import com.capgemini.mpa.beans.Customer;
import com.capgemini.mpa.beans.Transactions;

public interface WalletService
{
public Customer createAccount(String name ,String mobileno, BigDecimal amount);
public Customer showBalance (String mobileno);
public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, BigDecimal amount);
public Customer depositAmount (String mobileNo,BigDecimal amount );
public Customer withdrawAmount(String mobileNo, BigDecimal amount);
public List<Transactions> viewTransactions(String mobileNo);
public boolean isValid(Customer customer);


}

