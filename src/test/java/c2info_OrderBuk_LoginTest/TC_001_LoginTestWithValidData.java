package c2info_OrderBuk_LoginTest;  

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	
	@Test(priority=1)
	public void loginWithValidData() throws InterruptedException{
		log.info("======= TC_001_LoginTestWithValidData Test Started ======");
		LoginPage loginpage = new LoginPage();
		loginpage.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.titleContains(APP.getProperty("DashboardPageTitle")));
		Assert.assertEquals(getPageTitle(), APP.getProperty("DashboardPageTitle"));
	}
	
	@Test(priority=-1)
	public void loginWithInvalidUserName() throws InterruptedException{
		LoginPage loginpage = new LoginPage();
		loginpage.doLogin("Chethan Bidare", OR.getProperty("Password"));
		Thread.sleep(3000);
		Assert.assertEquals(loginpage.getInvalidUserNameOrPwdMSG(),APP.getProperty("InvalidUserNameOrPwdMSG"));
	}
	
	@Test(priority=0)
	public void loginWithInvalidPWD() throws InterruptedException{
		LoginPage loginpage = new LoginPage();
		loginpage.doLogin(OR.getProperty("UserName"), "pwd");
		Assert.assertEquals(loginpage.getInvalidUserNameOrPwdMSG(),APP.getProperty("InvalidUserNameOrPwdMSG"));
	}	
}
