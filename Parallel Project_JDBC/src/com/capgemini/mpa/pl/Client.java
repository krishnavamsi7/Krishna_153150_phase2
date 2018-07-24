package com.capgemini.mpa.pl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.capgemini.mpa.beans.Customer;
import com.capgemini.mpa.beans.Transactions;
import com.capgemini.mpa.exception.InsufficientBalanceException;
import com.capgemini.mpa.exception.InvalidInputException;
import com.capgemini.mpa.service.WalletService;
import com.capgemini.mpa.service.WalletServiceImpl;

public class Client 
{
	private WalletService walletService;
	
	public Client()
	{
		walletService = new WalletServiceImpl();
	}
	
	public void menu()
	{
		System.out.println("\n\t\t****Welcome to MyPayments App****");
		System.out.println("\t\t1.Create Account");
		System.out.println("\t\t2.Show Balance");
		System.out.println("\t\t3.Fund Transfer");
		System.out.println("\t\t4.Deposit Amount");
		System.out.println("\t\t5.Withdraw Amount");
		System.out.println("\t\t6.View Transactions");
		System.out.println("\t\t0.Exit");
		
		Scanner console = new Scanner(System.in);
		
		System.out.println("\tPlease Select an Option: ");
		
		int choice = console.nextInt();
		
		switch(choice)
		{
		case 1:
			Customer customer = null;
			System.out.print("Enter Your Name: ");
			String name = console.next();
			System.out.print("Enter Your Mobile Number: ");
			String MobileNo = console.next();
			System.out.print("Enter Amount to be Deposited in Your Account: ");
			BigDecimal amount = console.nextBigDecimal();
			
			
			
			try 
			{
				 customer = walletService.createAccount(name, MobileNo, amount);
					System.out.println("\nAccount created successfully");
					System.out.println(customer );
			} 
			catch (Exception e1) 
			{
				System.out.println(e1.getMessage());
			}
			
			
		break;
			
		case 2:
			System.out.print("Enter Mobile Number: ");
			String MobileNo1 = console.next();
			
			try 
			{
				Customer customer2 = walletService.showBalance(MobileNo1);
				System.out.println(customer2);
			}
			catch (InvalidInputException e) 
			{
				System.out.println(e.getMessage());
			}
			
		break;
		
		case 3:
			System.out.print("Enter Your Mobile Number: ");
			String SourceMobileNo = console.next();
			System.out.print("Enter Mobile Number of Account to which You Want to transfer: ");
			String TargetMobileNo = console.next();
			System.out.print("Enter Amount to Transfer: ");
			BigDecimal Amount = console.nextBigDecimal();
			
			try 
			{
				Customer customer3 = walletService.fundTransfer(SourceMobileNo, TargetMobileNo, Amount);
				if(customer3!=null)
				System.out.println(customer3);
				else
					System.out.println("\nAccount is not present for this Mobile Number\n");
			}
			catch (InsufficientBalanceException e) 
			{
				System.out.println(e.getMessage());
			}
			
			break;
			
		case 4:
			System.out.print("Enter Mobile Number to Deposit Amount: ");
			String Mobileno = console.next();
			System.out.print("Enter Amount to Deposit: ");
			BigDecimal DAmount = console.nextBigDecimal();
			
			
				Customer customer4 = walletService.depositAmount(Mobileno, DAmount);
				System.out.println(customer4);
			break;
			
		case 5:
			System.out.print("Enter Mobile Number to Withdraw Amount: ");
			String mobileno = console.next();
			System.out.print("Enter Amount to Withdraw: ");
			BigDecimal WAmount = console.nextBigDecimal();
			
			try 
			{
				Customer customer5 = walletService.withdrawAmount(mobileno, WAmount);
				System.out.println(customer5);
			}
			catch (InsufficientBalanceException e) 
			{
				System.out.println(e.getMessage());

			}
			catch(InvalidInputException e)
			{
				System.out.println(e.getMessage());
			}
			break;
			
		case 6:
			Transactions transactions = new Transactions();
			
			System.out.print("Enter Mobile Number: ");
			String mobileNo = console.next();
			
			List<Transactions> transactionList = walletService.viewTransactions(mobileNo);
			System.out.println("\nAccount Number \t Transaction Type \t Amount ");
			
			try {
				for(Transactions transaction:transactionList)
				{
					System.out.print(transaction.getMobileNo()+"\t");
					System.out.print(transaction.getTransactionType()+"\t");
					System.out.print("\t\t" + transaction.getAmount()+"\t\n");
				}
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			}
			break;
			
		case 0:
			System.out.println("Thank You");
			System.exit(0);
			break;
			
		default:
			System.out.println("Invalid option");
		break;
			
		}
	}
	   public static void main( String[] args )
	   {
		   Client client = new Client();
			
			while(true)
				
			client.menu();	        
	    }
}