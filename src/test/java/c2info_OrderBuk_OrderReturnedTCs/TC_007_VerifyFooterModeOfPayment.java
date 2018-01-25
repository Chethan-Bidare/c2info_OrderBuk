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

public class TC_007_VerifyFooterModeOfPayment extends TestBase{
	

public static final Logger log = Logger.getLogger(TC_007_VerifyFooterModeOfPayment.class.getName());
	
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test
	public void verifyFooterModeOfPayment() throws InterruptedException{
		log.info("======= TC_007_VerifyFooterModeOfPayment Test Started ======");
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		OrderSentToServer oss = new OrderSentToServer();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("OrderReturnedPageTitle"));
		select100Orders();
		tbv.selectAnOrder();
		Thread.sleep(5000);
		String actualResult = oss.getFooterMOP();
		if(actualResult.equalsIgnoreCase("cash")){
			Assert.assertEquals(actualResult, "Cash");
		}
		else{
			Assert.assertEquals(actualResult, "Card");
		}
	}




}
