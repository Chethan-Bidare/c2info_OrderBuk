package c2info_OrderBuk_OrderSentToServerTCs;  

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

public class TC_006_VerifySumOfSubTotalsWithGrandSubTotal extends TestBase{

public static final Logger log = Logger.getLogger(TC_006_VerifySumOfSubTotalsWithGrandSubTotal.class.getName());
	
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test
	public void verifySumOfSubTotalsWithGrandSubTotal() throws InterruptedException{
		log.info("======= TC_006_VerifySumOfSubTotalsWithGrandSubTotal Test Started ======");
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		OrderSentToServer oss = new OrderSentToServer();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("OrderSentToServerPageTitle"));
		select100Orders();
		tbv.selectAnOrder();
		Thread.sleep(5000);
		double calculatedSubTotal = oss.getCalculatedTotalOfEachSubTotal();
		double actualSubTotal = oss.getGrandSubTotal();
		Assert.assertEquals(calculatedSubTotal, actualSubTotal);
		
		
	}
}
