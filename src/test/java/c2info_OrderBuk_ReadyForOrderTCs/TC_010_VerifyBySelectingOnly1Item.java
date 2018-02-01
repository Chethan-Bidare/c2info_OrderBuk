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
import c2info_OrderBuk_UIPages.OrderSentToServer;
import c2info_OrderBuk_UIPages.ReadyForOrder;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_010_VerifyBySelectingOnly1Item extends TestBase{
	
public static final Logger log = Logger.getLogger(TC_010_VerifyBySelectingOnly1Item.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test
	public void verifyBySelectingOnly1Item() throws InterruptedException{
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		ReadyForOrder rfo = new ReadyForOrder();
		OrderSentToServer oss = new OrderSentToServer();
		
		db.selectBucket(APP.getProperty("ReadyforOrderPageTitle"));
		tbv.select100Orders();
		tbv.selectAnOrder();
		Thread.sleep(3000);
		
		String orderID = rfo.getOrderIDFromRFOPage();
		String itemName = rfo.select1Item();
		System.out.println("item name = "+itemName);
		rfo.clickOnConfirmBtnInRFOpage();
		db.clickOnDashboardinMenu();
		Thread.sleep(65000);
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("OrderSentToServerPageTitle"));
		tbv.select100Orders();
		rfo.selectOrder(orderID);
		Thread.sleep(10000);
		ArrayList<String> itemNames = oss.getItemList();
		Assert.assertTrue(itemNames.contains(itemName)==true);
		
		
	}


}
