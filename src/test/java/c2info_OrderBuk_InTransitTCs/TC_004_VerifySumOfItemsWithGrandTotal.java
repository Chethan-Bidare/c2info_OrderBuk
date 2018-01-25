package c2info_OrderBuk_InTransitTCs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.OrderInvoiced;
import c2info_OrderBuk_UIPages.OrderSentToServer;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_004_VerifySumOfItemsWithGrandTotal extends TestBase{

public static final Logger log = Logger.getLogger(TC_004_VerifySumOfItemsWithGrandTotal.class.getName());
	
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test
	public void verifySumOfItemsWithGrandTotal() throws InterruptedException{
		log.info("======= TC_004_VerifySumOfItemsWithGrandTotal Test Started ======");
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		OrderSentToServer oss = new OrderSentToServer();
		OrderInvoiced oi = new OrderInvoiced();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("InTransitPageTitle"));
		select100Orders();
		tbv.selectAnOrder();
		double itemwiseTotal = oi.getItemwiseTotal();
		double grandSubTotal = oss.getGrandSubTotal();
		Assert.assertEquals(itemwiseTotal, grandSubTotal);
	}

}
