package c2info_OrderBuk_ReadyForOrderTCs;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.ReadyForOrder;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_009_VerifyByAddingItemsInRFO extends TestBase {
	
public static final Logger log = Logger.getLogger(TC_009_VerifyByAddingItemsInRFO.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test
	public void verifyByAddingItems() throws InterruptedException{
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		ReadyForOrder rfo = new ReadyForOrder();
		
		db.selectBucket(APP.getProperty("ReadyforOrderPageTitle"));
		tbv.select100Orders();
		tbv.selectAnOrder();
		Thread.sleep(3000);
		//String orderID = rfo.getOrderIDFromRFOPage();
		rfo.enterItemNameInSearch(APP.getProperty("ItemName1"));
		Thread.sleep(5000);
		ArrayList<String> itemNames = rfo.getItemNames();
		/*rfo.clickOnConfirmBtnInRFOpage();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("OrderConfirmedPageTitle"));
		tbv.select100Orders();
		rfo.selectOrder(orderID);
		ArrayList<String> itemListInOC = oc.getItemList();
		Thread.sleep(5000);
		Assert.assertTrue(itemNames.containsAll(itemListInOC)==true);
		*/
		
		Assert.assertTrue(itemNames.contains(APP.getProperty("ItemName1")));
	}

}
