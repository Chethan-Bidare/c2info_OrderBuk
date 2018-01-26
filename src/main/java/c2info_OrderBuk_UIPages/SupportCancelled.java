package c2info_OrderBuk_UIPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import c2info_OrderBuk_TestBase.TestBase;

public class SupportCancelled extends TestBase{
	
	public static final Logger log = Logger.getLogger(ToBeVerified.class.getName());
	WebDriverWait wait = new WebDriverWait(driver, 45);
	
	
	@FindBy(xpath=".//*[@id='wrapper']/div/div/div/div/div[1]/h5[2]")
	WebElement orderID ;
	
	
	
	public SupportCancelled(){
		PageFactory.initElements(driver, this);
	}
	
	public String getOrderIDInSC() throws InterruptedException{
		Thread.sleep(4000);
		String orderid = orderID.getText();
		orderid = orderid.replaceAll("Order ID: ", "").trim();
		return orderid ;
	}

}
