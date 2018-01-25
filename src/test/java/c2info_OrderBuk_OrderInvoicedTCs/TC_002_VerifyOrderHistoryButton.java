package c2info_OrderBuk_OrderInvoicedTCs;  

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.NewItemPutOnHold;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_002_VerifyOrderHistoryButton extends TestBase{

public static final Logger log = Logger.getLogger(TC_002_VerifyOrderHistoryButton.class.getName());
	
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test
	public void verifyOrderhistorybutton() throws InterruptedException{
		log.info("======= TC_002_VerifyOrderHistoryButton Test Started ======");
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		NewItemPutOnHold nph = new NewItemPutOnHold();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("OrderInvoicedPageTitle"));
		select100Orders();
		tbv.selectAnOrder();
		nph.clickOnOrderHistory();
		boolean actualResult = nph.confirmOrderHistoryExistence();
		Assert.assertTrue(actualResult==true);
	}
}
