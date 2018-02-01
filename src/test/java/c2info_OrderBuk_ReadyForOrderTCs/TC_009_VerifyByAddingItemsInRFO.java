package c2info_OrderBuk_ReadyForOrderTCs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.LoginPage;

public class TC_009_VerifyByAddingItemsInRFO extends TestBase {
	
public static final Logger log = Logger.getLogger(TC_009_VerifyByAddingItemsInRFO.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test
	public void verifyByAddingItems(){
		
	}

}
