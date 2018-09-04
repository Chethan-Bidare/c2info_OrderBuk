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

public class TC_005_VerifySubmitButtonInDigitizePage extends TestBase{
	
	
public static final Logger log = Logger.getLogger(TC_005_VerifySubmitButtonInDigitizePage.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test(priority=18)
	public void verifySubmitButtonFunctionality() throws InterruptedException{
		Dashboard db = new Dashboard();
		db.selectBucket(APP.getProperty("ToBeVerifiedPageTitle"));
		ToBeVerified tbv = new ToBeVerified();
		tbv.selectAnOrder();
		tbv.makeOrderValid();
		DigitizePage dp = new DigitizePage();
		dp.addPatientDetails(APP.getProperty("CustomerName"), APP.getProperty("DoctorName"));
		dp.addItemsAndDosage();
		dp.clickOnSubmit();
		Assert.assertEquals(dp.getSuccessMsg(), "Order Sent to Ready For Order.");
	}

}
