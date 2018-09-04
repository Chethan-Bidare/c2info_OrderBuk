package c2info_OrderBuk_DashboardTCs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.LoginPage;

public class TC_004_Verify_UserName extends TestBase{
 
public static final Logger log = Logger.getLogger(TC_004_Verify_UserName.class.getName());
	
	@BeforeClass
	public void setup() throws IOException{
		init();
		log.info("Initializing Setup");	
	}
	
	@Test(priority=5)
	public void verifyUserName() throws InterruptedException{
		log.info("======= TC_003_VerifyLogOut Test Started ======");
		LoginPage loginpage = new LoginPage();
		loginpage.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		Dashboard db = new Dashboard();
		String ActualResult = db.getUserName();
		String Expectedresult = OR.getProperty("UserName");
		Expectedresult = Expectedresult.substring(Expectedresult.length()-6, Expectedresult.length());
		Expectedresult="Welcome"+" "+Expectedresult ;
		Assert.assertEquals(ActualResult,Expectedresult);
	}
}
