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

public class TC_002_VerifyImageExistanceInPrescriptionPage extends TestBase {

public static final Logger log = Logger.getLogger(TC_002_VerifyImageExistanceInPrescriptionPage.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test
	public void verifyImageInPrescriptionPage(){
		log.info("======= TC_002_VerifyImageExistanceInPrescriptionPage Test Started ======");
		Dashboard db = new Dashboard();
		db.selectBucket(APP.getProperty("ToBeVerifiedPageTitle"));
		ToBeVerified tbv = new ToBeVerified();
		tbv.selectAnOrder();
		Boolean Result = tbv.imageDisplay();
		System.out.println(Result);
		Assert.assertTrue(Result==true);
	}
}
