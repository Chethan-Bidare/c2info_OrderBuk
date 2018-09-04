package c2info_OrderBuk_DashboardTCs;
 
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.LoginPage;

public class TC_001_VerifyAllBucketLinks extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC_001_VerifyAllBucketLinks.class.getName());
	
	@BeforeClass
	public void setup() throws IOException{
		init();
		log.info("Initializing Setup");
		
	}
	
	@Test(priority=2)
	public void verifyAllBucketlinks() throws InterruptedException{
		
		log.info("======= TC_001_VerifyAllBucketLinks Test Started ======");
		LoginPage loginpage = new LoginPage();
		loginpage.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		Dashboard dashboard = new Dashboard();
		ArrayList<String> ExpectedValue = dashboard.getBucketNames();
		ArrayList<String> ActualValue = dashboard.getpageTitles();
		if(ExpectedValue.size()==ActualValue.size()){
			for(int i=0;i<ExpectedValue.size(); i++){
				try {
					Assert.assertEquals(ActualValue.get(i),ExpectedValue.get(i));
					log.info("Page Title Verified successfully for : "+ExpectedValue.get(i));
				} catch (AssertionError e) {
					log.info("Page Title verification failed for : "+ExpectedValue.get(i));
					getScreenshot(getPageTitle());
					e.printStackTrace();
				}
			}
			log.info("======= TC_001_VerifyAllBucketLinks Finished ======");
		}
		
	}

}
