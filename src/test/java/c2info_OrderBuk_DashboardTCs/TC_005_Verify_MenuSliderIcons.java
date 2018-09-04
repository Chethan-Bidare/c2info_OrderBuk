package c2info_OrderBuk_DashboardTCs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.LoginPage;

public class TC_005_Verify_MenuSliderIcons extends TestBase {
  
public static final Logger log = Logger.getLogger(TC_004_Verify_UserName.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test(priority=6)
	public void verifyDashboardLinkInMenuSlider() throws InterruptedException{
		Dashboard dashboard = new Dashboard();
		dashboard.clickOnDashboardinMenu();
		String ActualResult = getPageTitle();
		Assert.assertEquals(ActualResult, APP.getProperty("DashboardPageTitle"));
	}
	
	@Test(priority=7)
	public void verifyManageUsersLinkInMenuSlider(){
		Dashboard dashboard = new Dashboard();
		dashboard.clickOnManageUsersInMenu();
		String ActualResult = getPageTitle();
		Assert.assertEquals(ActualResult, APP.getProperty("ManageUsersPageTitle"));
	}
	
	@Test(priority=8)
	public void verifyManageCustomersLinkInMenuSlider(){
		Dashboard dashboard = new Dashboard();
		dashboard.clickOnManageCustomersInMenu();
		String ActualResult = getPageTitle();
		Assert.assertEquals(ActualResult, APP.getProperty("ManageCustomersPageTitle"));
	}
	
	@Test(priority=9)
	public void verifyImageUploadInMenuSlider(){
		Dashboard dashboard = new Dashboard();
		dashboard.clickOnImageUploadInMenu();
		String ActualResult = getPageTitle();
		Assert.assertEquals(ActualResult, "Image Upload");
	}
	
	@Test(priority=10)
	public void verifyImageInMenuSlider(){
		Dashboard dashboard = new Dashboard();
		dashboard.clickOnImageInMenu();
		String ActualResult = getPageTitle();
		Assert.assertEquals(ActualResult, "Image");
	}
	
	@Test(priority=11)
	public void verifyVideoInMenuSlider(){
		Dashboard dashboard = new Dashboard();
		dashboard.clickOnVideoInMenu();
		String ActualResult = getPageTitle();
		Assert.assertEquals(ActualResult, "Video");
	}
}
