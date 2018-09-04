package c2info_OrderBuk_DashboardTCs;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_ToBeVerifiedTCs.TC_002_VerifyImageExistance;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.OrderQuery;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_006_VerifySearchFunctionailtyInAllBuckets extends TestBase{
	
public static final Logger log = Logger.getLogger(TC_002_VerifyImageExistance.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test(priority=12)
	public void verifySearchFunctionality() throws InterruptedException{
		Dashboard db = new Dashboard();
		ToBeVerified tbv = new ToBeVerified();
		OrderQuery oq = new OrderQuery();
		
		ArrayList<String> bucketNames = db.getBucketNames();
		
		for(int i=0; i<bucketNames.size(); i++){
		String bucket = bucketNames.get(i);
		db.selectBucket(bucket);
		String orderID = tbv.getOrderID();
		try {
			oq.SearchOrder(orderID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(2000);
		
		if(db.getTableSize()==1){
			Assert.assertTrue(orderID.equalsIgnoreCase(tbv.getOrderID())==true);
			db.clickOnDashboardinMenu();
			Thread.sleep(3000);
		}
		else if(db.getTableSize()==0){
			db.clickOnDashboardinMenu();
		}
		else{
			Assert.assertFalse(orderID.equalsIgnoreCase(tbv.getOrderID())==true);
			db.clickOnDashboardinMenu();
		}
		
		}
	}

}
