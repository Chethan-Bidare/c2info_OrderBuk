package c2info_OrderBuk_IntegrationSuiteTCs;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.OrderConfirmed;
import c2info_OrderBuk_UIPages.ReadyForOrder;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_002_AddItemsInRFOandVerifyInOrderConfirmed extends TestBase{

public static final Logger log = Logger.getLogger(TC_002_AddItemsInRFOandVerifyInOrderConfirmed.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test
	public void verifyByAddingItemsinRFO() throws InterruptedException{
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		ReadyForOrder rfo = new ReadyForOrder();
		OrderConfirmed oc = new OrderConfirmed();
		db.selectBucket(APP.getProperty("ReadyforOrderPageTitle"));
		tbv.select100Orders();
		tbv.selectAnOrder();
		Thread.sleep(3000);
		String orderID = rfo.getOrderIDFromRFOPage();
		rfo.enterItemNameInSearch(APP.getProperty("ItemName1"));
		Thread.sleep(5000);
		ArrayList<String> itemNames = rfo.getItemNames();
		rfo.clickOnConfirmBtnInRFOpage();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("OrderConfirmedPageTitle"));
		tbv.select100Orders();
		rfo.selectOrder(orderID);
		ArrayList<String> itemListInOC = oc.getItemList();
		Thread.sleep(5000);
		Assert.assertTrue(itemNames.containsAll(itemListInOC)==true);
	}
}
