package c2info_OrderBuk_SupportCancelledTCs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.OrderQuery;
import c2info_OrderBuk_UIPages.ReadyForOrder;
import c2info_OrderBuk_UIPages.SupportCancelled;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_005_VerifyCancelButtonFromRFO extends TestBase{
	
public static final Logger log = Logger.getLogger(TC_005_VerifyCancelButtonFromRFO.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test
	public void verifyCancelButtonFromRFO() throws InterruptedException{
		Dashboard db = new Dashboard();
		db.selectBucket(APP.getProperty("ReadyforOrderPageTitle"));
		ReadyForOrder rfo = new ReadyForOrder();
		select100Orders();
		ToBeVerified tbv = new ToBeVerified();
		tbv.selectAnOrder();
		String OrderID = rfo.getOrderIDFromRFOPage();
		rfo.clickOnCancelBtnInRFOpage();
		rfo.enterCancelDetails();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("SupportCancelledPageTitle"));
		OrderQuery oq = new OrderQuery();
		oq.SearchOrder(OrderID);
		tbv.selectAnOrder();
		SupportCancelled sc = new SupportCancelled();
		String cancelledOrderID = sc.getOrderIDInSC();
		Assert.assertEquals(OrderID, cancelledOrderID);
	}

}
