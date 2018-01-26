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
import c2info_OrderBuk_UIPages.SupportCancelled;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_001_VerifyInvalidPrescFromTBV extends TestBase{
	
public static final Logger log = Logger.getLogger(TC_001_VerifyInvalidPrescFromTBV.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test
	public void verifyInvalidPrescFromTBV() throws InterruptedException{
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		SupportCancelled sc = new SupportCancelled();
		OrderQuery oq = new OrderQuery();
		db.selectBucket(APP.getProperty("ToBeVerifiedPageTitle"));
		tbv.select100Orders();
		tbv.selectAnOrder();
		String orderid = tbv.getOrderIDInPrescImagePage();
		tbv.makeOrderInvalid();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("SupportCancelledPageTitle"));
		oq.SearchOrder(orderid);
		tbv.selectAnOrder();
		String cancelledOrderID = sc.getOrderIDInSC();
		Assert.assertEquals(orderid, cancelledOrderID);
	}

}
