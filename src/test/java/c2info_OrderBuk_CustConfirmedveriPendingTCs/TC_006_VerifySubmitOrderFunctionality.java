package c2info_OrderBuk_CustConfirmedveriPendingTCs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.CustomerConfirmedVerificationPending;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.DigitizePage;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.OrderQuery;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_006_VerifySubmitOrderFunctionality extends TestBase{
  
public static final Logger log = Logger.getLogger(TC_005_VerifyInvalidOrderFunctionality.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test(priority=49)
	public void verifySubmitOrderFunctionality() throws InterruptedException{
		Dashboard db = new Dashboard();
		db.selectBucket(APP.getProperty("CCVPPageTitle"));
		select100Orders();
		ToBeVerified tbv = new ToBeVerified();
		tbv.selectAnOrder();
		tbv.makeOrderValid();
		CustomerConfirmedVerificationPending ccvp = new CustomerConfirmedVerificationPending();
		String OrderID = ccvp.getOrderID();
		DigitizePage dp = new DigitizePage();
		dp.addPatientDetails("Chethan", "Bidare");
		ccvp.itemSelection();
		ccvp.clickOnSubmitButton();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("OrderQueryPageTitle"));
		OrderQuery oq = new OrderQuery();
		oq.SearchOrder(OrderID);
		String ActualResult = oq.getStatus();
		try {
			Assert.assertEquals(ActualResult, "ORDER CONFIRMED");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.assertEquals(ActualResult, "ORDER SENT TO SERVER");
			e.printStackTrace();
		}
	}
}
