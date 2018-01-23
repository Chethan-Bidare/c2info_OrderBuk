package c2info_OrderBuk_UIPages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import c2info_OrderBuk_TestBase.TestBase;

public class NewItemPutOnHold extends TestBase{

	public static final Logger log = Logger.getLogger(DigitizePage.class.getName());
	WebDriverWait wait = new WebDriverWait(driver, 45);
	
	
	@FindBy(xpath=".//h5[2]")
	WebElement OrderIDInNPH ;
	
	@FindBy(xpath=".//*[@id='wrapper']/div/div/div/div/div[2]/div[1]/span")
	WebElement ReverifyMsg ;
	
	@FindBy(xpath=".//*[contains(text(),'View Prescription')]")
	WebElement ViewPrescButton ;
	
	@FindBy(xpath=".//*[contains(text(),'View Order History')]")
	WebElement ViewOrderHistButton ;
	
	@FindBy(id="closed")
	WebElement OrderClosedButton ;
	
	@FindBy(id="txtar")
	WebElement OrderClosedRemark ;
	
	@FindBy(id="btnclosed")
	WebElement OrderClosedSubmit ;
	
	public NewItemPutOnHold(){
		PageFactory.initElements(driver, this);
	}
	public boolean checkNewItemExists(){
		waitforPageToLoad();
		List<WebElement> missingItems = driver.findElements(By.xpath(".//*[@id='wrapper']/div[1]/div/div/div/div[2]/div/div[1]/div/div[1]/div/table[2]/tbody/tr"));
		System.out.println(missingItems.size());
		if(missingItems.size()>2){
			
			return true ;
		}
		else{
			return false ;
		}
			
		
	}
	
	public String getOrderIDFromNPHPage(){
		String OrderID = OrderIDInNPH.getText();
		OrderID = OrderID.substring(OrderID.length()-6,OrderID.length());
		OrderID = OrderID.trim();
		return OrderID ;
		
	}
	
	public String getSuccessMsgforReverify() throws InterruptedException{
		Thread.sleep(5000);
		return ReverifyMsg.getText();
	}
	
	public void clickOnViewPrescriptionButton(){
		ViewPrescButton.click();
	}
	
	public void clickOnOrderHistory(){
		ViewOrderHistButton.click();
	}
	
	public void clickOnOrderClosed(){
		OrderClosedButton.click();
	}
	
	public String getPrescriptionPopUpTitle(){
		String parentWindow = driver.getWindowHandle();
		for(String childWindow : driver.getWindowHandles()){
			driver.switchTo().window(childWindow);
			
		}
		return driver.getTitle();
	}
	
	public boolean imageExistance(){
		if(driver.findElement(By.xpath(".//img")).isDisplayed()==true){
			return true ;
		}
		else
			return false ;
	}
	
	
	public boolean confirmOrderHistoryExistence() throws InterruptedException{
		Thread.sleep(5000);
		if(driver.findElement(By.xpath(".//*[@id='myModal']/div/div")).isDisplayed()==true){
			return true ;
		}
		else
			return false ;
	}
	
	public void enterOrderClosedRemarkAndSubmit() throws InterruptedException{
		Thread.sleep(3000);
		OrderClosedRemark.sendKeys("mnbmnb");
		OrderClosedSubmit.click();
	}
	
	public String orderClosedMsg() throws InterruptedException{
		Thread.sleep(5000);
		return driver.findElement(By.xpath(".//*[@id='wrapper']/div/div/div/div/div[2]/div[1]/span")).getText();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
