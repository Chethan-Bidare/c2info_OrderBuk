package c2info_OrderBuk_ToBeVerifiedTCs; 

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.DigitizePage;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.OrderQuery;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_008_VerifyNewItemRequestFunctionality extends TestBase{

public static final Logger log = Logger.getLogger(TC_008_VerifyNewItemRequestFunctionality.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test
	public void verifyNewItemRequest() throws InterruptedException{
		Dashboard db = new Dashboard();
		db.selectBucket(APP.getProperty("ToBeVerifiedPageTitle"));
		select100Orders();
		ToBeVerified tbv = new ToBeVerified();
		tbv.selectAnOrder();
		tbv.makeOrderValid();
		DigitizePage dp = new DigitizePage();
		dp.addPatientDetails("Chethan", "Bidare");
		String OrderID = dp.getOrderIDFromDigitizePage();
		dp.clickOnRequestNewItem();
		dp.requestNewItemDetails();
		dp.clickOnSubmit();
		db.clickOnDashboardinMenu();
		db.selectBucket("Order Query");
		OrderQuery oq = new OrderQuery();
		oq.SearchOrder(OrderID);
		String ActualResult = oq.getStatus();
		Assert.assertEquals(ActualResult, "New Item Put On Hold");
	}

}
