package c2info_OrderBuk_ReadyForOrderTCs; 

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.ReadyForOrder;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_002_VerifyDetailsExistence extends TestBase {
	
public static final Logger log = Logger.getLogger(TC_002_VerifyDetailsExistence.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test(priority=0)
	public void verifyOrderIDIsNotNull(){
		log.info("======= verifyOrderIDIsNotNull Test Started ======");
		Dashboard db = new Dashboard();
		db.selectBucket(APP.getProperty("ReadyforOrderPageTitle"));
		ReadyForOrder rfo = new ReadyForOrder();
		select100Orders();
		ToBeVerified tbv = new ToBeVerified();
		tbv.selectAnOrder();
		String orderID = rfo.getOrderIDFromRFOPage();
		Assert.assertNotEquals(orderID, "");
		Assert.assertNotEquals(orderID,"null");
	}
	
	@Test(priority=1)
	public void verifyCustomerNameIsNotNull(){
		log.info("======= verifyCustomerNameIsNotNull Test Started ======");
		ReadyForOrder rfo = new ReadyForOrder();
		String custName = rfo.getCustomerNameInRFOpage();
		System.out.println(custName);
		Assert.assertNotEquals(custName, "");
		Assert.assertNotEquals(custName,"null");
	}
	
	@Test(priority=2)
	public void verifyCustomerNumberIsNotNull(){
		log.info("======= verifyCustomerNumberIsNotNull Test Started ======");
		ReadyForOrder rfo = new ReadyForOrder();
		String custNum = rfo.getCustomerNumberInRFOpage();
		System.out.println(custNum);
		Assert.assertNotEquals(custNum, "");
		Assert.assertNotEquals(custNum,"null");
	}

}
