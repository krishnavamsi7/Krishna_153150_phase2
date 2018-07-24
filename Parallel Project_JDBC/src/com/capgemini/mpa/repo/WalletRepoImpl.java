package com.capgemini.mpa.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.capgemini.mpa.beans.Customer;
import com.capgemini.mpa.beans.Transactions;
import com.capgemini.mpa.beans.Wallet;
import com.capgemini.mpa.exception.InvalidInputException;
import com.capgemini.mpa.util.DBUtil;

public class WalletRepoImpl implements WalletRepo 
{
	
	private Map<String, Customer> data;
	
	List<Transactions> transactionsList = new ArrayList<Transactions>();
	
	public WalletRepoImpl(Map<String, Customer> data) 
	{
		super();
		this.data = data;
	}

	public WalletRepoImpl() 
	{
		super();
	}
	
	@Override
	public boolean save(Customer customer) 
	{
		
			try(Connection con = DBUtil.getConnection()) 
			{
				
				PreparedStatement pstm = con.prepareStatement("Insert into customer values(?,?,?)");
				pstm.setString(1, customer.getMobileNo());
				pstm.setString(2, customer.getName());
				pstm.setBigDecimal(3, customer.getWallet().getBalance());
				
				pstm.executeQuery();
				
				con.commit();
			}
			catch (SQLException | ClassNotFoundException e) 
			{
				throw new InvalidInputException("Account Already Exists");
			}
			

		return true;
	}


	@Override
	public Customer findOne(String mobileNo) 
	{
		Customer customer = null;
		
		try(Connection con = DBUtil.getConnection())
		{
			PreparedStatement pstm = con.prepareStatement("select * from customer where mobilenumber=?");
			pstm.setString(1, mobileNo);
			
			ResultSet res = pstm.executeQuery();
			
			while(res.next())
			{
			customer = new Customer();
			Wallet wallet = new Wallet();
			
			customer.setMobileNo(res.getString(1));
			customer.setName(res.getString(2));
			customer.setWallet(wallet);
			wallet.setBalance(res.getBigDecimal(3));
		    }
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return customer;
	}

	@Override
	public Customer Update(Customer customer, String transactionType) {
	
		try(Connection con = DBUtil.getConnection()) 
		{
			PreparedStatement pstm = con.prepareStatement("update customer set balance=? where mobilenumber=?");
			pstm.setBigDecimal(1, customer.getWallet().getBalance());
			pstm.setString(2, customer.getMobileNo());
			
			pstm.executeUpdate();
			
			PreparedStatement pstm1 = con.prepareStatement("insert into transactions values(?,?,?)");
			pstm1.setString(1, customer.getMobileNo());
			pstm1.setString(2, transactionType);
			pstm1.setBigDecimal(3, customer.getWallet().getBalance());
			
			pstm1.executeUpdate();
		}
		catch(SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
			throw new InvalidInputException("Account doesn't exist for this Mobile Number");
		}
		return customer;
	}

	@Override
	public List<Transactions> transactions(String mobileNo) 
	{
		try(Connection con = DBUtil.getConnection())
		{
			PreparedStatement pstm = con.prepareStatement("select * from transactions where mobilenumber=?");
			pstm.setString(1, mobileNo);
			
			ResultSet res = pstm.executeQuery();
			
			while(res.next())
			{
				transactionsList.add(new Transactions(res.getString(1),res.getString(2),res.getBigDecimal(3)));
			}
		}
		catch(Exception e)
		{
			throw new InvalidInputException("Account doesn't exist for this Mobile Number");
		}
		return transactionsList;
	}
}