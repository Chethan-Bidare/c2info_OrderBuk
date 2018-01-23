package c2info_OrderBuk_NewItemPutOnHoldTCs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_OrderBuk_TestBase.TestBase;
import c2info_OrderBuk_UIPages.Dashboard;
import c2info_OrderBuk_UIPages.LoginPage;
import c2info_OrderBuk_UIPages.NewItemPutOnHold;
import c2info_OrderBuk_UIPages.ToBeVerified;

public class TC_001_VerifyAtleastOneItemIsAddedasNewItem extends TestBase{
	
public static final Logger log = Logger.getLogger(TC_001_VerifyAtleastOneItemIsAddedasNewItem.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("UserName"), OR.getProperty("Password"));
		
	}
	
	@Test
	public void verifyAtleastOneItemIsAddedasNewItem(){
		Dashboard db = new Dashboard();
		db.selectBucket(APP.getProperty("NewItemPutOnHoldPageTitle"));
		select100Orders();
		ToBeVerified tbv = new ToBeVerified();
		tbv.selectAnOrder();
		NewItemPutOnHold nph = new NewItemPutOnHold();
		boolean result = nph.checkNewItemExists();
		Assert.assertTrue(result==true);
	}

}
