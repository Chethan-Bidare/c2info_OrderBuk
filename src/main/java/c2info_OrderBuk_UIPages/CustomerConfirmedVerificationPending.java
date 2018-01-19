package c2info_OrderBuk_UIPages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import c2info_OrderBuk_TestBase.TestBase;

public class CustomerConfirmedVerificationPending extends TestBase {

	public static final Logger log = Logger.getLogger(ReadyForOrder.class.getName());
	WebDriverWait wait = new WebDriverWait(driver, 45);
	
	@FindBy(xpath=".//h5")
	WebElement PageHeader ;
	
	@FindBy(xpath=".//*[@class='table-responsive m-t col-sm-6 col-md-6 col-lg-6']/table/tbody/tr[1]/td[2]")
	WebElement OrderID ;
	
	@FindBy(xpath=".//*[@class='table-responsive m-t col-sm-6 col-md-6 col-lg-6']/table/tbody/tr[2]/td[2]")
	WebElement CustomerName ;
	
	@FindBy(xpath=".//*[@class='table-responsive m-t col-sm-6 col-md-6 col-lg-6']/table/tbody/tr[3]/td[2]")
	WebElement CustomerNumber ;
	
	@FindBy(xpath=".//address")
	WebElement ShippingAddress ;
	
	@FindBy(id="btninv")
	WebElement Invalid ;
	
	@FindBy(name="note")
	WebElement CancellationRemark ;
	
	@FindBy(name="Cancel the Order")
	WebElement CancellationSubmitButton ;
	
	@FindBy(id="btnsubmit")
	WebElement SubmitBtn ;
	
	/*@FindBy(xpath=".//h5")
	WebElement PageHeader ;
	
	@FindBy(xpath=".//h5")
	WebElement PageHeader ;
	*/
	
	public CustomerConfirmedVerificationPending(){
		PageFactory.initElements(driver, this);
	}
	
	
	public String getPageHeader(){
		waitforPageToLoad();
		return PageHeader.getText();
	}
	
	public String getOrderID(){
		waitforPageToLoad();
		String orderID = OrderID.getText();
		orderID = orderID.replaceAll(":","");
		orderID = orderID.trim();
		return orderID ;
	}
	
	public String getCustomerName(){
		waitforPageToLoad();
		String custName = CustomerName.getText();
		custName = custName.replaceAll(":","");
		custName = custName.trim();
		return custName ;
	}
	
	public String getCustomerNumber(){
		waitforPageToLoad();
		String custNum = CustomerNumber.getText();
		custNum = custNum.replaceAll(":","");
		custNum = custNum.trim();
		return custNum ;
	}
	
	public String getShippingAddress(){
		waitforPageToLoad();
		return ShippingAddress.getText();
	}
	
	public void clickOnSubmitButton(){
		waitforPageToLoad();
		SubmitBtn.click();
	}
	
	public void clickOnInvalidButton(){
		waitforPageToLoad();
		Invalid.click();
	}
	
	public void enterCancellationdetails() throws InterruptedException{
	Thread.sleep(3000);
	CancellationRemark.clear();
	CancellationRemark.sendKeys("test");
	CancellationSubmitButton.click();
	}
	
	public void itemSelection(){
		List<WebElement> items = driver.findElements(By.xpath(".//*[@id='all_list']/tr/td[3]/a"));
		for(int i=1; i<=items.size(); i++){
			driver.findElement(By.xpath(".//*[@id='all_list']/tr["+i+"]/td[3]/a")).click();
			driver.findElement(By.id("mrng"+i+"")).clear();
			log.info("Clearing the text field for Morning dosage");
			driver.findElement(By.id("mrng"+i+"")).sendKeys("1");
			log.info("Entering the Qty for Morning dosage");
			driver.findElement(By.id("aftn"+i+"")).clear();
			log.info("Clearing the text field for Afternoon dosage");
			driver.findElement(By.id("aftn"+i+"")).sendKeys("1");
			log.info("Entering the Qty for Afternoon dosage");
			driver.findElement(By.id("ngt"+i+"")).clear();
			log.info("Clearing the text field for Night dosage");
			driver.findElement(By.id("ngt"+i+"")).sendKeys("1");
			log.info("Entering the Qty for Afternoon dosage");
			driver.findElement(By.id("days"+i+"")).sendKeys("5");
			log.info("Entering No of days ");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
