package c2info_OrderBuk_DashboardTCs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.LoginPage;

public class TC_003_VerifyLogOut extends TestBase{
 
public static final Logger log = Logger.getLogger(TC_003_VerifyLogOut.class.getName());
	
	@BeforeClass
	public void setup() throws IOException{
		init();
		log.info("Initializing Setup");
		
	}
	
	@Test
	public void verifyLogoutInDashboard() throws InterruptedException{
		log.info("======= TC_003_VerifyLogOut Test Started ======");
		LoginPage loginpage = new LoginPage();
		loginpage.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		Dashboard db = new Dashboard();
		db.logOut();
		String ActualResult = getPageTitle();
		System.out.println(ActualResult);
		Assert.assertEquals(ActualResult, APP.getProperty("LoginPageTitle"));
	}
}
