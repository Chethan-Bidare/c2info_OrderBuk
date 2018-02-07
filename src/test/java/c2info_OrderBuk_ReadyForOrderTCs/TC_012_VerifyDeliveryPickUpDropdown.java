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

public class TC_012_VerifyDeliveryPickUpDropdown extends TestBase{

public static final Logger log = Logger.getLogger(TC_012_VerifyDeliveryPickUpDropdown.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test(priority=0)
	public void verifyDeliveryChargesForPickUp() throws InterruptedException{
		ReadyForOrder rfo = new ReadyForOrder();
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		
		db.selectBucket(APP.getProperty("ReadyforOrderPageTitle"));
		tbv.select100Orders();
		tbv.selectAnOrder();
		rfo.selectDeliveryTypeDropdown("Pickup");
		Thread.sleep(4000);
		double actualDelCharges = rfo.getDeliverycharge();
		Assert.assertTrue(actualDelCharges<=0 ==true);
	}
	
	
	@Test(priority=1)
	public void verifyDeliveryChargesForDelivery() throws InterruptedException{
		ReadyForOrder rfo = new ReadyForOrder();
		
		rfo.selectDeliveryTypeDropdown("Delivery");
		Thread.sleep(4000);
		double actualDelCharges = rfo.getDeliverycharge();
		Assert.assertTrue(actualDelCharges>0==true);
	}
	
}
