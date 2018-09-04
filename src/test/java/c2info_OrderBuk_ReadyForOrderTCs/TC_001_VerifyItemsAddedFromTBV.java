package c2info_OrderBuk_ReadyForOrderTCs; 

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.DigitizePage;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.ReadyForOrder;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_001_VerifyItemsAddedFromTBV extends TestBase{

public static final Logger log = Logger.getLogger(TC_001_VerifyItemsAddedFromTBV.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test(priority=22)
	public void verifyItemsAddedInTBV() throws InterruptedException{
		Dashboard db = new Dashboard();
		db.selectBucket(APP.getProperty("ToBeVerifiedPageTitle"));
		ToBeVerified tbv = new ToBeVerified();
		select100Orders();
		tbv.selectAnOrder();
		tbv.makeOrderValid();
		DigitizePage dp = new DigitizePage();
		String ExpectedValue = dp.getOrderIDFromDigitizePage();
		dp.addPatientDetails(APP.getProperty("CustomerName"), APP.getProperty("DoctorName"));
		dp.addItemsAndDosage();
		ArrayList<String> itemNamesAddedInTBV = dp.getItemNames();
		dp.clickOnSubmit();
		db.clickOnDashboardinMenu();
		db.selectBucket(APP.getProperty("ReadyforOrderPageTitle"));
		ReadyForOrder rfo = new ReadyForOrder();
		select100Orders();
		rfo.selectOrder(ExpectedValue);
		ArrayList<String> itemNamesFoundInRFO = rfo.getItemNames();
		try{
			
			Assert.assertTrue(itemNamesAddedInTBV.containsAll(itemNamesFoundInRFO));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
