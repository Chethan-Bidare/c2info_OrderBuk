package c2info_OrderBuk_CustConfirmedveriPendingTCs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.CustomerConfirmedVerificationPending;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.DigitizePage;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_007_VerifySubmitByAddingItems extends TestBase{
  
public static final Logger log = Logger.getLogger(TC_007_VerifySubmitByAddingItems.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test
	public void verifySubmitOrderFunctionality() throws InterruptedException{
		Dashboard db = new Dashboard();
		db.selectBucket(APP.getProperty("CCVPPageTitle"));
		select100Orders();
		ToBeVerified tbv = new ToBeVerified();
		tbv.selectAnOrder();
		tbv.makeOrderValid();
		CustomerConfirmedVerificationPending ccvp = new CustomerConfirmedVerificationPending();
		//String OrderID = ccvp.getOrderID();
		DigitizePage dp = new DigitizePage();
		dp.addPatientDetails("Chethan", "Bidare");
		ccvp.itemSelection();
		dp.addItem("Dolo 650mg Tab");
	}
}
