package c2info_OrderBuk_OrderInvoicedTCs;  

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

public class TC_008_VerifyFooterModeOfDelivery extends TestBase{
	
public static final Logger log = Logger.getLogger(TC_008_VerifyFooterModeOfDelivery.class.getName());
	
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test
	public void verifyFooterModeOfDelivery() throws InterruptedException{
		log.info("======= TC_008_VerifyFooterModeOfDelivery Test Started ======");
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		OrderSentToServer oss = new OrderSentToServer();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("OrderInvoicedPageTitle"));
		select100Orders();
		tbv.selectAnOrder();
		Thread.sleep(5000);
		String actualResult = oss.getFooterMOD();
		if(actualResult.equalsIgnoreCase("Delivery")){
			Assert.assertEquals(actualResult, "Delivery");
		}
		else{
			Assert.assertEquals(actualResult, "PickUp");
		}
	}

}
