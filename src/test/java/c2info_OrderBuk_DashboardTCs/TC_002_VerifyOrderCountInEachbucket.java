package c2info_OrderBuk_DashboardTCs;

import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_002_VerifyOrderCountInEachbucket extends TestBase{
 
public static final Logger log = Logger.getLogger(TC_002_VerifyOrderCountInEachbucket.class.getName());
	
	@BeforeClass
	public void setup() throws IOException{
		init();
		log.info("Initializing Setup");
		
	}
	
	@Test(priority=3, enabled=false)
	public void verifyOrderCountToBeVerified() throws InterruptedException{
		log.info("======= TC_002_VerifyOrderCountInEachbucket Test Started ======");
		LoginPage loginpage = new LoginPage();
		loginpage.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		Dashboard db = new Dashboard();
		HashMap<String,Integer> hm = db.getOrderCountOfEachBuckets();
		int ExpectedValue = hm.get("TO BE VERIFIED");
		System.out.println("Expected Value :"+ExpectedValue);
		ToBeVerified tbv = new ToBeVerified();
		int ActualValue = tbv.getCountOfOrdersToBeVerified();
		System.out.println("Actual Value :"+ActualValue);
		Assert.assertEquals(ActualValue, ExpectedValue);
	}
}
