package c2info_OrderBuk_LoginTest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.LoginPage;

public class TC_001_LoginTestWithValidData extends TestBase {

	public static final Logger log = Logger.getLogger(TC_001_LoginTestWithValidData.class.getName());

	@BeforeClass
	public void setup() throws IOException{
		init();
		log.info("Initializing Setup");
		
	}
	
	@Test
	public void loginWithValidData() throws InterruptedException{
		log.info("======= TC_001_LoginTestWithValidData Test Started ======");
		LoginPage loginpage = new LoginPage();
		loginpage.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		Assert.assertEquals(getPageTitle(), APP.getProperty("DashboardPageTitle"));
	}
	
	
}
