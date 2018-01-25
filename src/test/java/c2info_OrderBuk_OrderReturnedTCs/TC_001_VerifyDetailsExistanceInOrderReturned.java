package c2info_OrderBuk_OrderReturnedTCs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.OrderSentToServer;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_001_VerifyDetailsExistanceInOrderReturned extends TestBase{
	
public static final Logger log = Logger.getLogger(TC_001_VerifyDetailsExistanceInOrderReturned.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test(priority=0)
	public void verifyOrderIDIsNotNull() throws InterruptedException{
		log.info("======= verifyOrderIDIsNotNull Test Started ======");
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		OrderSentToServer oss = new OrderSentToServer();
		
		db.selectBucket(APP.getProperty("OrderReturnedPageTitle"));
		tbv.select100Orders();
		tbv.selectAnOrder();
		String orderID = oss.getOrderIDFromOSSPage();
		System.out.println(orderID);
		Assert.assertNotEquals(orderID, "");
		Assert.assertNotEquals(orderID,"null");
	}
	
	@Test(priority=1)
	public void verifyCustomerNameIsNotNull(){
		log.info("======= verifyCustomerNameIsNotNull Test Started ======");
		OrderSentToServer oss = new OrderSentToServer();
		String custName = oss.getCustomerNameInOSSpage();
		System.out.println(custName);
		Assert.assertNotEquals(custName, "");
		Assert.assertNotEquals(custName,"null");
	}
	
	@Test(priority=2)
	public void verifyCustomerNumIsNotNull(){
		log.info("======= verifyCustomerNumIsNotNull Test Started ======");
		OrderSentToServer oss = new OrderSentToServer();
		String custNum = oss.getCustomerNumberInOSSpage();
		System.out.println(custNum);
		Assert.assertNotEquals(custNum, "");
		Assert.assertNotEquals(custNum,"null");
	}
	
	@Test(priority=3)
	public void verifyHeaderAmtIsNotNull(){
		log.info("======= verifyHeaderAmtIsNotNull Test Started ======");
		OrderSentToServer oss = new OrderSentToServer();
		String headerAmt = oss.getHeaderAmt();
		System.out.println(headerAmt);
		Assert.assertNotEquals(headerAmt, "");
		Assert.assertNotEquals(headerAmt,"null");
	}
	
	@Test(priority=4)
	public void verifyHeaderMOPIsNotNull(){
		log.info("======= verifyHeaderMOPIsNotNull Test Started ======");
		OrderSentToServer oss = new OrderSentToServer();
		String headerMOP = oss.getModeOfPayment();
		System.out.println(headerMOP);
		Assert.assertNotEquals(headerMOP, "");
		Assert.assertNotEquals(headerMOP,"null");
	}
	
	@Test(priority=5)
	public void verifyHeaderModeOfDeliveryIsNotNull(){
		log.info("======= verifyHeaderModeOfDeliveryIsNotNull Test Started ======");
		OrderSentToServer oss = new OrderSentToServer();
		String headerMOD = oss.getDeliveryORPickUp();
		System.out.println(headerMOD);
		Assert.assertNotEquals(headerMOD, "");
		Assert.assertNotEquals(headerMOD,"null");
	}
	
	@Test(priority=6)
	public void verifyShippingAddressIsNotNull(){
		log.info("======= verifyShippingAddressIsNotNull Test Started ======");
		OrderSentToServer oss = new OrderSentToServer();
		String shippingAddress = oss.getShippingAddress();
		System.out.println(shippingAddress);
		Assert.assertNotEquals(shippingAddress, "");
		Assert.assertNotEquals(shippingAddress,"null");
	}
	
	@Test(priority=7)
	public void verifyBillingAddressIsNotNull(){
		log.info("======= verifyBillingAddressIsNotNull Test Started ======");
		OrderSentToServer oss = new OrderSentToServer();
		String shippingAddress = oss.getShippingAddress();
		System.out.println(shippingAddress);
		Assert.assertNotEquals(shippingAddress, "");
		Assert.assertNotEquals(shippingAddress,"null");
	}




}
