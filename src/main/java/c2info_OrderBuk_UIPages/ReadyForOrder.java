package c2info_OrderBuk_UIPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;

import c2info_OrderBuk_TestBase.TestBase;

public class ReadyForOrder extends TestBase{

	public static final Logger log = Logger.getLogger(ReadyForOrder.class.getName());
	WebDriverWait wait = new WebDriverWait(driver, 45);
}
