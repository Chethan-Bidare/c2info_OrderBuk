package c2info_OrderBuk_IntegrationSuiteTCs;

import java.io.IOException;

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

public class TC_003_VerifyValuesFromRFOtoOrderConfirmedPage extends TestBase{
	
public static final Logger log = Logger.getLogger(TC_003_VerifyValuesFromRFOtoOrderConfirmedPage.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test(priority=0)
	public void verifyGrandTotalValues() throws InterruptedException{
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		ReadyForOrder rfo = new ReadyForOrder();
		OrderConfirmed oc = new OrderConfirmed();
		
		db.selectBucket(APP.getProperty("ReadyforOrderPageTitle"));
		tbv.select100Orders();
		tbv.selectAnOrder();
		Thread.sleep(3000);
		String orderID = rfo.getOrderIDFromRFOPage();
		double expectedGrandTotal = rfo.getGrandTotal();
		double expectedSubTotal = rfo.getSubTotal();
		double expectedDiscount = rfo.getDiscount();
		double expectedDeliveryCharges = rfo.getDeliverycharge();
		rfo.clickOnConfirmBtnInRFOpage();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("OrderConfirmedPageTitle"));
		tbv.select100Orders();
		rfo.selectOrder(orderID);
		Thread.sleep(5000);
		double actualGrandTotal = oc.getGrandSubTotalInOCpage();
		double actualSubtotal = oc.getGrandSubTotalInOCpage();
		double actualDiscount = oc.getDiscountValueInOCpage();
		double actualDelCharges = oc.getDeliveryValueInOCpage();
		Assert.assertEquals(actualGrandTotal, expectedGrandTotal);
		Assert.assertEquals(actualSubtotal, expectedSubTotal);
		Assert.assertEquals(actualDiscount, expectedDiscount);
		Assert.assertEquals(actualDelCharges, expectedDeliveryCharges);
	}
	

}
