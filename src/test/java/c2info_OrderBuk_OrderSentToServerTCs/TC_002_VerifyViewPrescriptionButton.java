package c2info_OrderBuk_OrderSentToServerTCs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.NewItemPutOnHold;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_002_VerifyViewPrescriptionButton extends TestBase {

public static final Logger log = Logger.getLogger(TC_002_VerifyViewPrescriptionButton.class.getName());
	
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test(priority=0)
	public void verifyPrescriptionButton() throws InterruptedException{
		log.info("======= TC_002_VerifyViewPrescriptionButton Test Started ======");
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		NewItemPutOnHold nph = new NewItemPutOnHold();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("OrderSentToServerPageTitle"));
		select100Orders();
		tbv.selectAnOrder();
		nph.clickOnViewPrescriptionButton();
		String pagetitle = nph.getPrescriptionPopUpTitle();
		Assert.assertEquals(pagetitle, "View Prescription popup");
	}
	
	@Test(priority=1)
	public void verifyImageExistanceinPrescPopUp(){
		log.info("======= TC_002_VerifyViewPrescriptionButton Test Started ======");
		NewItemPutOnHold nph = new NewItemPutOnHold();
		boolean result = nph.imageExistance();
		Assert.assertTrue(result==true);
	}
}
