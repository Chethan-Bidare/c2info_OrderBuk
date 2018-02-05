package c2info_OrderBuk_IntegrationSuiteTCs;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.DigitizePage;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.OrderSentToServer;
import c2info_OrderBuk_UIPages.ReadyForOrder;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_001_VerifyOrderInConfirmedOrderFromTBV extends TestBase {
public static final Logger log = Logger.getLogger(TC_001_VerifyOrderInConfirmedOrderFromTBV.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test
	public void pushOrderToReadyForOrder() throws InterruptedException{
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		DigitizePage dp = new DigitizePage();
		ReadyForOrder rfo = new ReadyForOrder();
		OrderSentToServer oss = new OrderSentToServer();
		db.selectBucket(APP.getProperty("ToBeVerifiedPageTitle"));
		tbv.select100Orders();
		tbv.selectAnOrder();
		tbv.makeOrderValid();
		dp.addPatientDetails(APP.getProperty("CustomerName"),APP.getProperty("DoctorName"));
		String orderID = dp.getOrderIDFromDigitizePage();
		ArrayList<String> expectedItemNames = dp.getItemNames();
		dp.addItemsAndDosage();
		dp.clickOnSubmit();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("ReadyforOrderPageTitle"));
		tbv.select100Orders();
		tbv.selectOrderFromOrderID(orderID);
		ArrayList<String> actualItemNames = rfo.getItemNames();
		String actualOrder = rfo.getOrderIDFromRFOPage();
		try {
			Assert.assertEquals(actualOrder,orderID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Assert.assertTrue(actualItemNames.containsAll(expectedItemNames));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		double expectedTotal = rfo.getGrandTotal();
		rfo.clickOnConfirmBtnInRFOpage();
		Thread.sleep(5000);
		db.clickOnDashboardinMenu();
		Thread.sleep(65000);
		db.selectBucket(APP.getProperty("OrderSentToServerPageTitle"));
		tbv.select100Orders();
		Thread.sleep(3000);
		rfo.selectOrder(orderID);
		Thread.sleep(5000);
		double actualTotal = oss.getGrandTotal();
		
		Assert.assertEquals(actualTotal, expectedTotal);
		
		
	}
}
