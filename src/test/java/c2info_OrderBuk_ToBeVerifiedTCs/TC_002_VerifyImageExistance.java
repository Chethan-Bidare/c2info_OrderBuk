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

public class TC_002_VerifyImageExistance extends TestBase {

public static final Logger log = Logger.getLogger(TC_002_VerifyImageExistance.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test(priority=14)
	public void verifyImageInPrescriptionPage() throws InterruptedException{
		log.info("======= TC_002_VerifyImageExistanceInPrescriptionPage Test Started ======");
		Dashboard db = new Dashboard();
		db.selectBucket(APP.getProperty("ToBeVerifiedPageTitle"));
		ToBeVerified tbv = new ToBeVerified();
		tbv.selectAnOrder();
		Boolean Result = tbv.imageDisplay();
		System.out.println(Result);
		Assert.assertTrue(Result==true);
	}
	
	@Test(priority=15)
	public void verifyImageInDigitizePage() throws InterruptedException{
		log.info("======= TC_002_VerifyImageExistanceInPrescriptionPage Test Started ======");	
		ToBeVerified tbv = new ToBeVerified();
		tbv.makeOrderValid();
		Boolean result = tbv.imageDisplay();
		System.out.println(result);
		Assert.assertTrue(result==true);
	}
}
