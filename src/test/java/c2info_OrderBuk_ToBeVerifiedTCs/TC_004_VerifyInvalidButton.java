package c2info_OrderBuk_ToBeVerifiedTCs;  

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_004_VerifyInvalidButton extends TestBase {
	
public static final Logger log = Logger.getLogger(TC_004_VerifyInvalidButton.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test(priority=17)
	public void verifyInvalidButton() throws InterruptedException{
		log.info("======= TC_004_VerifyInvalidButton Test Started ======");
		Dashboard db = new Dashboard();
		db.selectBucket(APP.getProperty("ToBeVerifiedPageTitle"));
		ToBeVerified tbv = new ToBeVerified();
		System.out.println(tbv.getOrderID());
		tbv.selectAnOrder();
		tbv.makeOrderInvalid();
		System.out.println(tbv.getCancellationMsg());
		Assert.assertEquals(tbv.getCancellationMsg(),"Order Cancelled Successfully.");
	}

}
