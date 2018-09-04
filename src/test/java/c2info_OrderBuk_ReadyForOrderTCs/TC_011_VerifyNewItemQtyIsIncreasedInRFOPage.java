package c2info_OrderBuk_ReadyForOrderTCs;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.ReadyForOrder;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_011_VerifyNewItemQtyIsIncreasedInRFOPage extends TestBase{

public static final Logger log = Logger.getLogger(TC_011_VerifyNewItemQtyIsIncreasedInRFOPage.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test(priority=34)
	public void verifyQtyIncrease() throws InterruptedException{
		ReadyForOrder rfo = new ReadyForOrder();
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		
		db.selectBucket(APP.getProperty("ReadyforOrderPageTitle"));
		tbv.select100Orders();
		tbv.selectAnOrder();
		rfo.getItemNamesAndQtyInRFO();
		rfo.enterItemNameInSearch("Glycirest 500 Sr Tab");
		rfo.increaseQtyForAddedItem();
		refreshPage();
		rfo.increaseQtyForAddedItem();
		rfo.getItemNamesAndQtyInRFO();
		int qty = rfo.getItemQty("Glycirest 500 Sr Tab");
		System.out.println(qty);
		Assert.assertTrue(qty>1==true);
		
		
	}
}
