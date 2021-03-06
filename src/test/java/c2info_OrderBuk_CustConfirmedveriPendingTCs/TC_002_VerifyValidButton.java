package c2info_OrderBuk_CustConfirmedveriPendingTCs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.CustomerConfirmedVerificationPending;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_002_VerifyValidButton extends TestBase {


public static final Logger log = Logger.getLogger(TC_002_VerifyValidButton.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test(priority=42)
	public void verifyValidButtonInPrescriptionPage() throws InterruptedException{
		log.info("======= TC_002_VerifyValidButton Test Started ======");
		Dashboard db = new Dashboard();
		db.selectBucket(APP.getProperty("CCVPPageTitle"));
		ToBeVerified tbv = new ToBeVerified();
		tbv.selectAnOrder();
		tbv.makeOrderValid();
		CustomerConfirmedVerificationPending ccvp = new CustomerConfirmedVerificationPending();
		Assert.assertEquals(ccvp.getPageHeader(), APP.getProperty("DigitizeScreen"));
	}
	

}
