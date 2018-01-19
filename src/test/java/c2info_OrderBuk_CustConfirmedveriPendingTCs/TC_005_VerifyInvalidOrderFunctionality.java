package c2info_OrderBuk_CustConfirmedveriPendingTCs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.CustomerConfirmedVerificationPending;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.OrderQuery;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_005_VerifyInvalidOrderFunctionality extends TestBase{

public static final Logger log = Logger.getLogger(TC_005_VerifyInvalidOrderFunctionality.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test
	public void verifyCancelButtonFunctionality() throws InterruptedException{
		Dashboard db = new Dashboard();
		db.selectBucket(APP.getProperty("CCVPPageTitle"));
		select100Orders();
		ToBeVerified tbv = new ToBeVerified();
		tbv.selectAnOrder();
		tbv.makeOrderValid();
		CustomerConfirmedVerificationPending ccvp = new CustomerConfirmedVerificationPending();
		String OrderID = ccvp.getOrderID();
		ccvp.clickOnInvalidButton();
		ccvp.enterCancellationdetails();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("OrderQueryPageTitle"));
		OrderQuery oq = new OrderQuery();
		oq.SearchOrder(OrderID);
		String ActualResult = oq.getStatus();
		Assert.assertEquals(ActualResult, "SUPPORT CANCELLED");
	}
}
