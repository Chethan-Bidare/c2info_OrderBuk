package c2info_OrderBuk_ReadyForOrderTCs;

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

public class TC_013_VerifyPaymentDropdown extends TestBase{

public static final Logger log = Logger.getLogger(TC_013_VerifyPaymentDropdown.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test(priority=0)
	public void verifyPaymentModeCard() throws InterruptedException{
		ReadyForOrder rfo = new ReadyForOrder();
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		OrderConfirmed oc = new OrderConfirmed();
		
		db.selectBucket(APP.getProperty("ReadyforOrderPageTitle"));
		tbv.select100Orders();
		tbv.selectAnOrder();
		String orderid = rfo.getOrderIDFromRFOPage();
		rfo.selectPaymentTypeDropdown("Card on delivery");
		rfo.clickOnConfirmBtnInRFOpage();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("OrderConfirmedPageTitle"));
		tbv.select100Orders();
		rfo.selectOrder(orderid);
		String actualPayMode = oc.getFooterMOPInOCpage();
		System.out.println(actualPayMode);
		Assert.assertEquals(actualPayMode,"Card on delivery" );
		
	}
	

	@Test(priority=1)
	public void verifyPaymentModeCash() throws InterruptedException{
		ReadyForOrder rfo = new ReadyForOrder();
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		OrderConfirmed oc = new OrderConfirmed();
		
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("ReadyforOrderPageTitle"));
		tbv.select100Orders();
		tbv.selectAnOrder();
		String orderid = rfo.getOrderIDFromRFOPage();
		rfo.selectPaymentTypeDropdown("Cash on delivery");
		rfo.clickOnConfirmBtnInRFOpage();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("OrderConfirmedPageTitle"));
		tbv.select100Orders();
		rfo.selectOrder(orderid);
		String actualPayMode = oc.getFooterMOPInOCpage();
		System.out.println(actualPayMode);
		Assert.assertEquals(actualPayMode,"Cash on delivery" );
		
	}
	

	@Test(priority=2)
	public void verifyPaymentModeOnline() throws InterruptedException{
		ReadyForOrder rfo = new ReadyForOrder();
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		OrderConfirmed oc = new OrderConfirmed();
		
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("ReadyforOrderPageTitle"));
		tbv.select100Orders();
		tbv.selectAnOrder();
		String orderid = rfo.getOrderIDFromRFOPage();
		rfo.selectPaymentTypeDropdown("Online payment");
		rfo.clickOnConfirmBtnInRFOpage();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("OrderConfirmedPageTitle"));
		tbv.select100Orders();
		rfo.selectOrder(orderid);
		String actualPayMode = oc.getFooterMOPInOCpage();
		System.out.println(actualPayMode);
		Assert.assertEquals(actualPayMode,"Online payment" );
		
	}

}
