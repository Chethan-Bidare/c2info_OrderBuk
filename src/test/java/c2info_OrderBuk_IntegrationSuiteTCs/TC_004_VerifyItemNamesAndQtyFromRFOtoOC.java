package c2info_OrderBuk_IntegrationSuiteTCs;

import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.DigitizePage;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.OrderConfirmed;
import c2info_OrderBuk_UIPages.ReadyForOrder;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_004_VerifyItemNamesAndQtyFromRFOtoOC extends TestBase{
		
		public static final Logger log = Logger.getLogger(TC_004_VerifyItemNamesAndQtyFromRFOtoOC.class.getName());
			
			@BeforeClass
			public void setup() throws IOException, InterruptedException{
				init();
				log.info("Initializing Setup");
				LoginPage lp = new LoginPage();
				lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
				
			}
			
			@Test
			public void verifyItemNamesAndQtyFromRFOtoOC() throws InterruptedException{
				Dashboard db = new Dashboard();
				ToBeVerified tbv = new ToBeVerified();
				ReadyForOrder rfo = new ReadyForOrder();
				OrderConfirmed oc = new OrderConfirmed();
				DigitizePage dp = new DigitizePage();
				
				db.selectBucket(APP.getProperty("ReadyforOrderPageTitle"));
				tbv.select100Orders();
				tbv.selectAnOrder();
				Thread.sleep(3000);
				HashMap<String, Integer> expectedItemAndQty = rfo.getItemNamesAndQtyInRFO();
				db.clickOnDashboardinMenu();
				db.selectBucket(APP.getProperty("OrderConfirmedPageTitle"));
				db.select100Orders();
				tbv.selectAnOrder();
				Thread.sleep(5000);
				HashMap<String, Integer> actualItemsAndQty = oc.getItemNamesWithQty();
				
			}
    

	
}