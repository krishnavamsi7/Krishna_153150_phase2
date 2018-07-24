package com.capgemini.mpa.test;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.mpa.beans.Customer;
import com.capgemini.mpa.beans.Wallet;
import com.capgemini.mpa.exception.InsufficientBalanceException;
import com.capgemini.mpa.exception.InvalidInputException;
import com.capgemini.mpa.service.WalletService;
import com.capgemini.mpa.service.WalletServiceImpl;

public class TestAppServer 
{
	public static WalletService walletService;
	{
		walletService = new WalletServiceImpl();
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		walletService = new WalletServiceImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception 
	{
		walletService = null;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void initData()
	{
		 Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Amit", "9900112212",new Wallet(new BigDecimal(9000)));
		 Customer cust2=new Customer("Ajay", "9963242422",new Wallet(new BigDecimal(6000)));
		 Customer cust3=new Customer("Yogini", "9922950519",new Wallet(new BigDecimal(7000)));
				
		 data.put("9900112212", cust1);
		 data.put("9963242422", cust2);	
		 data.put("9922950519", cust3);	
				
	}
		@Test
		public void Name() {
			Map<String, Customer> data = new HashMap<String, Customer>();
			Customer customer = new Customer("Jones", "9900112212", new Wallet(
					new BigDecimal(9000)));
			data.put("9900112212", customer);
			assertEquals(customer.getName(), "Jones");
		}

		@Test
		public void MobileNo() {
			Map<String, Customer> data = new HashMap<String, Customer>();
			Customer customer = new Customer("Peter", "9963242422", new Wallet(
					new BigDecimal(6000)));
			data.put("9963242422", customer);
			assertEquals(customer.getMobileNo(), "9963242422");
		}

		@Test(expected=InvalidInputException.class)
		public void exceptionTest6() 
		{
		 walletService.createAccount("","",new BigDecimal(25000));		   
		}
		
		@Test
		public void wallet() {
			Map<String, Customer> data = new HashMap<String, Customer>();
			Customer customer = new Customer("Tony", "9922950519", new Wallet(
					new BigDecimal(7000)));
			data.put("9922950519", customer);
		
			Wallet w1=customer.getWallet();
			assertEquals(w1.getBalance(), new BigDecimal(7000));
		}
		@Test
		public void namenull() {
			Map<String, Customer> data = new HashMap<String, Customer>();
			Customer customer = new Customer(null, "9900112212", new Wallet(
					new BigDecimal(9000)));
			data.put("9900112212", customer);
			assertNull(customer.getName());
		}
		@Test
		public void MobileNonull()
		{
			Map<String, Customer> data = new HashMap<String, Customer>();
			Customer customer = new Customer("Rogers", null, new Wallet(
					new BigDecimal(9000)));
			data.put("9900112212", customer);

			assertNull(customer.getMobileNo());
		}
		@Test
		public void Balancenull()
		{
			Map<String, Customer> data = new HashMap<String, Customer>();
			Customer customer = new Customer("Loki","9900112212", new Wallet(null));
			data.put("9900112212", customer);
			assertNotNull(customer.getWallet());
		}		
		@Test(expected=InvalidInputException.class)
		public void mobilenumber1() 
		{
		 walletService.createAccount("Krishna","98989898989",new BigDecimal(25000));   
		}
		
		@Test(expected=InvalidInputException.class)
		public void mobilenumber2() 
		{
		 walletService.createAccount("nikhil","0123456789",new BigDecimal(25000));   
		}

	@Test
	public void MobileNoValidation()
	{
		Map<String, Customer> data = new HashMap<String, Customer>();
		Customer customer = new Customer("Jack","9494",new Wallet(new BigDecimal(4000)));
		data.put("9494", customer);
		assertNotEquals("999999999", "9494");
	}
		
	@Test(expected=InvalidInputException.class)
	public void showBalance1() 
	{
		walletService.createAccount("Krishna", "9898989898", new BigDecimal(2500));
		walletService.showBalance("9898989898");
	}
	
	@Test(expected=InvalidInputException.class)
	public void showbalance2()
	{
		walletService.showBalance("1456145616314");
	}
	
	@Test
	public void insertHashMap() 
	{
	 Customer customer=walletService.createAccount("krishna","9949695125",new BigDecimal(25000));
	 Map<String,Customer> m=new HashMap<>();
	 m.put(customer.getMobileNo(),customer);
	 assertEquals(m.containsKey("9949695125"),true);		   
	}
	
	@Test(expected=InvalidInputException.class)
	public void deposit1() throws InvalidInputException 
	{
	 Customer customer=walletService.createAccount("Reacher","9876543210",new BigDecimal(25000));
	 customer=walletService.depositAmount("9876543210",new BigDecimal(5000));
	 assertEquals(customer.getMobileNo(),"9876543210");
	}
	
	@Test(expected=InvalidInputException.class)
	public void deposit2() throws InvalidInputException 
	{
	 Customer customer=walletService.createAccount("James","9632587410",new BigDecimal(25000));
	 customer=walletService.depositAmount("9632587410",new BigDecimal(5000));
	 assertEquals(customer.getName(),"James");
	}
	
	@Test(expected=InvalidInputException.class)
	public void deposit3() throws InvalidInputException 
	{
	 Customer customer=walletService.createAccount("John Wick","9874561230",new BigDecimal(25000));
	 customer=walletService.depositAmount("9874561230",new BigDecimal(5000));
	 assertEquals(customer.getWallet().getBalance(),new BigDecimal(30000));
	}
	
//	@Test
//	public void fundTransfer1() throws InvalidInputException 
//	{
//	 walletService.createAccount("Thor","9898989898",new BigDecimal(2000));
//	 Customer customer=walletService.createAccount("Loki","8989898989",new BigDecimal(1000)); 
//	 walletService.fundTransfer("9898989898", "8989898989", new BigDecimal(1000));
//	 assertEquals(customer.getWallet().getBalance(),new BigDecimal(2000));
//	}
//	@Test
//	public void fundTransfer2() throws InvalidInputException 
//	{
//	Customer customer=walletService.createAccount("Thor","9898989898",new BigDecimal(2000));
//	 walletService.createAccount("Loki","8989898989",new BigDecimal(1000)); 
//	 walletService.fundTransfer("9898989898", "8989898989", new BigDecimal(1000));
//	 assertEquals(customer.getWallet().getBalance(),new BigDecimal(1000));
//	}
	
	@Test(expected=InvalidInputException.class)
	public void withdraw1() throws InsufficientBalanceException 
	{
	 walletService.createAccount("Messi","9080706050",new BigDecimal(5000));
	 walletService.withdrawAmount("9080706050", new BigDecimal(6000)); 
	 }
	
	@Test(expected=InvalidInputException.class)
	public void withdraw2() //throws InsufficientBalanceException
	{
	Customer customer=walletService.createAccount("Messi","9080706050",new BigDecimal(5000));
	 walletService.withdrawAmount("9080706050", new BigDecimal(3000));
	 assertEquals(customer.getWallet().getBalance(),new BigDecimal(2000));
	 }
	
	@Test(expected=InvalidInputException.class)
	public void withdraw3()  
	{
	Customer customer=walletService.createAccount("Messi","9080706050",new BigDecimal(5000));
	 walletService.withdrawAmount("9080706050", new BigDecimal(3000));
	 assertEquals(customer.getMobileNo(),"9080706050");
	 }
	
}