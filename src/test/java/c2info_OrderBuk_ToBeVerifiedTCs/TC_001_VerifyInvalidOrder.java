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
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_001_VerifyInvalidOrder extends TestBase{

public static final Logger log = Logger.getLogger(TC_001_VerifyInvalidOrder.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test
	public void verifyInvalidOrder() throws InterruptedException{
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		DigitizePage dp = new DigitizePage();
		db.selectBucket(APP.getProperty("ToBeVerifiedPageTitle"));
		tbv.select100Orders();
		tbv.selectAnOrder();
		tbv.makeOrderValid();
		dp.clickOnInvalidButton();
		dp.cancelorderdetaisl();
		System.out.println(tbv.getCancellationMsg());
		Assert.assertEquals(tbv.getCancellationMsg(),"Order Cancelled Successfully.");
		
		
	}
}
