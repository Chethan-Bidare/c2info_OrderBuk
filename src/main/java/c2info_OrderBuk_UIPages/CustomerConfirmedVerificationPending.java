package c2info_OrderBuk_UIPages;

import java.util.ArrayList;
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
	
	@FindBy(xpath=".//*[@id='wrapper']/div/div/div/div/div[2]/div[1]/span")
	WebElement NewItemPutOnHoldSuccessMsg ;
	
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
	
	
	public ArrayList<String> getQtyFromTheList(){
		List<WebElement> items = driver.findElements(By.xpath(".//*[@id='all_list']/tr/td[3]/a"));
		ArrayList<String> ItemQty = new ArrayList<String>();
		for(int i=1;i<=items.size();i++){
			String temp =  driver.findElement(By.xpath(".//*[@id='all_list']/tr["+i+"]/td[3]")).getText();
			temp = temp.replaceAll("[+^]","");
			temp = temp.trim();
			System.out.println(temp);
			ItemQty.add(temp);
			
		}
		return ItemQty ;
	}
	
	public void itemSelection() throws InterruptedException {
		List<WebElement> items = driver.findElements(By.xpath(".//*[@id='all_list']/tr/td[3]/a"));
		ArrayList<String> itemQty = getQtyFromTheList();
		for(int i=1; i<=items.size(); i++){
			driver.findElement(By.xpath(".//*[@id='all_list']/tr[1]/td[3]/a")).click();
			Thread.sleep(2000);
			if(i<=2){
			driver.findElement(By.id("mrng1")).clear();
			log.info("Clearing the text field for Morning dosage");
			driver.findElement(By.id("mrng1")).sendKeys("1");
			log.info("Entering the Qty for Morning dosage");
			driver.findElement(By.id("days1")).clear();
			driver.findElement(By.id("days1")).sendKeys(itemQty.get(i-1));
			}
			else if(i==3){
				driver.findElement(By.id("mrng2")).clear();
				log.info("Clearing the text field for Morning dosage");
				driver.findElement(By.id("mrng2")).sendKeys("1");
				log.info("Entering the Qty for Morning dosage");
				driver.findElement(By.id("days2")).clear();
				driver.findElement(By.id("days2")).sendKeys(itemQty.get(i-1));
			}
			else{
				
				driver.findElement(By.id("mrng3")).clear();
				log.info("Clearing the text field for Morning dosage");
				driver.findElement(By.id("mrng3")).sendKeys("1");
				log.info("Entering the Qty for Morning dosage");
				driver.findElement(By.id("days3")).clear();
				driver.findElement(By.id("days3")).sendKeys(itemQty.get(i-1));
			}
		}
		
		
	}
	
	public String getSuccessMsg(){
		return NewItemPutOnHoldSuccessMsg.getText();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
