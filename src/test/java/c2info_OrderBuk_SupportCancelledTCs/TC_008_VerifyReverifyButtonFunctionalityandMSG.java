package c2info_OrderBuk_SupportCancelledTCs;

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
import c2info_OrderBuk_UIPages.ReadyForOrder;
import c2info_OrderBuk_UIPages.SupportCancelled;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_008_VerifyReverifyButtonFunctionalityandMSG extends TestBase{

public static final Logger log = Logger.getLogger(TC_008_VerifyReverifyButtonFunctionalityandMSG.class.getName());
	
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test(priority=0)
	public void verifyReverifyFunctionality() throws InterruptedException{
		log.info("======= TC_008_VerifyReverifyButtonFunctionality Test Started ======");
		Dashboard db = new Dashboard();
		ReadyForOrder rfo = new ReadyForOrder();
		ToBeVerified tbv = new ToBeVerified();
		OrderQuery oq = new OrderQuery();
		SupportCancelled sc = new SupportCancelled();
		db.selectBucket(APP.getProperty("SupportCancelledPageTitle"));
		select100Orders();
		tbv.selectAnOrder();
		String OrderID = sc.getOrderIDInSC();
		rfo.clickOnReverifyBtnInRFOpage();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("OrderQueryPageTitle"));
		oq.SearchOrder(OrderID);
		String ActualResult = oq.getStatus();
		try {
			Assert.assertEquals(ActualResult, "TO BE VERIFIED");
		} catch (Exception e) {
			Assert.assertEquals(ActualResult,APP.getProperty("CCVPPageTitle"));
		}
	}
	
	
	@Test(priority=1)
	public void verifyReverifyMsg() throws InterruptedException{
		log.info("======= TC_008_VerifyReverifyButtonMSG Test Started ======");
		Dashboard db = new Dashboard();
		ReadyForOrder rfo = new ReadyForOrder();
		ToBeVerified tbv = new ToBeVerified();
		NewItemPutOnHold nph = new NewItemPutOnHold();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("SupportCancelledPageTitle"));
		select100Orders();
		tbv.selectAnOrder();
		rfo.clickOnReverifyBtnInRFOpage();
		String successMsg = nph.getSuccessMsgforReverify();
		Assert.assertEquals(successMsg, "Order Reverified Successfully.");
		
	}

}
