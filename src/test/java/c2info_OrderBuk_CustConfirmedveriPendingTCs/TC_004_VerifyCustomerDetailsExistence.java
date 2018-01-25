package c2info_OrderBuk_CustConfirmedveriPendingTCs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.CustomerConfirmedVerificationPending;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_004_VerifyCustomerDetailsExistence extends TestBase{
 
public static final Logger log = Logger.getLogger(TC_004_VerifyCustomerDetailsExistence.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test(priority=0)
	public void verifyOrderIDIsNotNull(){
		Dashboard db = new Dashboard();
		db.selectBucket(APP.getProperty("CCVPPageTitle"));
		select100Orders();
		ToBeVerified tbv = new ToBeVerified();
		tbv.selectAnOrder();
		tbv.makeOrderValid();
		CustomerConfirmedVerificationPending ccvp = new CustomerConfirmedVerificationPending();
		String orderID = ccvp.getOrderID();
		Assert.assertNotEquals(orderID, "");
		Assert.assertNotEquals(orderID, " ");
		Assert.assertNotEquals(orderID,"null");
	}
	
	@Test(priority=1)
	public void verifyCustomerNameIsNotNull(){
		CustomerConfirmedVerificationPending ccvp = new CustomerConfirmedVerificationPending();
		String custName = ccvp.getCustomerName();
		Assert.assertNotEquals(custName, "");
		Assert.assertNotEquals(custName, " ");
		Assert.assertNotEquals(custName,"null");
	}
	
	@Test(priority=2)
	public void verifyCustomerNumberIsNotNull(){
		CustomerConfirmedVerificationPending ccvp = new CustomerConfirmedVerificationPending();
		String custNum = ccvp.getCustomerNumber();
		Assert.assertNotEquals(custNum, "");
		Assert.assertNotEquals(custNum, " ");
		Assert.assertNotEquals(custNum,"null");
	}
	
	@Test(priority=3)
	public void verifyShippingAddressIsNotNull(){
		CustomerConfirmedVerificationPending ccvp = new CustomerConfirmedVerificationPending();
		String shippingAddress = ccvp.getShippingAddress();
		System.out.println(shippingAddress);
		Assert.assertNotEquals(shippingAddress, "");
		Assert.assertNotEquals(shippingAddress, " ");
		Assert.assertNotEquals(shippingAddress,"null");
	}
}
