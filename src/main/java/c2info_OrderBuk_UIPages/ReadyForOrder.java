package c2info_OrderBuk_UIPages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import c2info_OrderBuk_TestBase.TestBase;

public class ReadyForOrder extends TestBase{

	public static final Logger log = Logger.getLogger(ReadyForOrder.class.getName());
	WebDriverWait wait = new WebDriverWait(driver, 45);
	
	@FindBy(xpath=".//*[@id='wrapper']/div/div[1]/div/div/div/label")
	WebElement OrderIDInRFO ;
	
	public ReadyForOrder(){
		PageFactory.initElements(driver, this);
	}
	
	public void selectOrder(String OrderID) throws InterruptedException{
		List<WebElement> orderIDs = driver.findElements(By.xpath(".//*[@id='bootstrap-table']/tbody/tr/td[1]"));
		for(WebElement we : orderIDs){
			if(we.getText().equals(OrderID)){
				we.click();
				
			}
			
		}
		Thread.sleep(5000);
	}
	
	public String getOrderIDFromRFOPage(){
		String OrderID = OrderIDInRFO.getText();
		OrderID = OrderID.substring(OrderID.length()-6,OrderID.length());
		OrderID = OrderID.trim();
		System.out.println(OrderID);
		return OrderID ;
		
	}
}
