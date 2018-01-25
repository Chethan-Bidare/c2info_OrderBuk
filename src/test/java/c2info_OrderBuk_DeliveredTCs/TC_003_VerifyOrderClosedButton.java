package c2info_OrderBuk_DeliveredTCs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.NewItemPutOnHold;
import c2info_OrderBuk_UIPages.OrderQuery;
import c2info_OrderBuk_UIPages.OrderSentToServer;
import c2info_OrderBuk_UIPages.ToBeVerified;
 
public class TC_003_VerifyOrderClosedButton extends TestBase{
	
public static final Logger log = Logger.getLogger(TC_003_VerifyOrderClosedButton.class.getName());
	
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test(priority=0)
	public void verifyOrderClosedButtonFunctionality() throws InterruptedException{
		log.info("======= TC_003_VerifyOrderClosedButton Test Started ======");
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		NewItemPutOnHold nph = new NewItemPutOnHold();
		OrderSentToServer oss = new OrderSentToServer();
		OrderQuery oq = new OrderQuery();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("OrderDeliveredPageTitle"));
		select100Orders();
		tbv.selectAnOrder();
		String OrderID = oss.getOrderIDFromOSSPage();
		nph.clickOnOrderClosed();
		nph.enterOrderClosedRemarkAndSubmit();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("OrderQueryPageTitle"));
		oq.SearchOrder(OrderID);
		String ActualResult = oq.getStatus();
		Assert.assertEquals(ActualResult, "Order Closed");
	}
	
	
	@Test(priority=1)
	public void verifyOrderClosedButtonMsg() throws InterruptedException{
		log.info("======= TC_003_VerifyOrderClosedMsg Test Started ======");
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		NewItemPutOnHold nph = new NewItemPutOnHold();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("OrderDeliveredPageTitle"));
		select100Orders();
		tbv.selectAnOrder();
		nph.clickOnOrderClosed();
		nph.enterOrderClosedRemarkAndSubmit();
		String successMsg = nph.orderClosedMsg();
		Assert.assertEquals(successMsg, "Order Closed Successfully.");
	}


}
