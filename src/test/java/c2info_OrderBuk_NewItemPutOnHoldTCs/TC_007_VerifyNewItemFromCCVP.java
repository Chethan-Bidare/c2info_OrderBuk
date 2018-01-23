package c2info_OrderBuk_NewItemPutOnHoldTCs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.DigitizePage;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.NewItemPutOnHold;
import c2info_OrderBuk_UIPages.OrderQuery;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_007_VerifyNewItemFromCCVP extends TestBase{

public static final Logger log = Logger.getLogger(TC_007_VerifyNewItemFromCCVP.class.getName());
	
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test
	public void verifyNewItemFromCCVP() throws InterruptedException{
		log.info("======= TC_007_VerifyNewItemFromCCVP Test Started ======");
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		DigitizePage dp = new DigitizePage();
		NewItemPutOnHold nph = new NewItemPutOnHold();
		OrderQuery oq = new OrderQuery();
		db.selectBucket(APP.getProperty("CCVPPageTitle"));
		tbv.select100Orders();
		tbv.selectAnOrder();
		tbv.makeOrderValid();
		String orderID = dp.getOrderIDFromDigitizePage();
		dp.addPatientDetails("Chethan", "Bidare");
		dp.clickOnRequestNewItem();
		dp.requestNewItemDetails();
		dp.confirmNewItemRequest();
		dp.clickOnSubmit();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("NewItemPutOnHoldPageTitle"));
		oq.SearchOrder(orderID);
		tbv.selectAnOrder();
		String orderID_NPH = nph.getOrderIDFromNPHPage();
		Assert.assertEquals(orderID_NPH, orderID);
	}
}
