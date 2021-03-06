package c2info_OrderBuk_ReadyForOrderTCs;  

import java.io.IOException;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.ReadyForOrder;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_003_VerifySubTotalwithItemWiseAmt extends TestBase {

public static final Logger log = Logger.getLogger(TC_003_VerifySubTotalwithItemWiseAmt.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test(priority=26)
	public void verifySubTotalAmt() throws InterruptedException{

		Dashboard db = new Dashboard();
		db.selectBucket(APP.getProperty("ReadyforOrderPageTitle"));
		ReadyForOrder rfo = new ReadyForOrder();
		select100Orders();
		ToBeVerified tbv = new ToBeVerified();
		tbv.selectAnOrder();
		double itemwiseTotAmt = rfo.getItemWiseTotalAmt();
		double subtotal = rfo.getSubTotal();
		Assert.assertEquals(subtotal, itemwiseTotAmt);

		
	}
}
