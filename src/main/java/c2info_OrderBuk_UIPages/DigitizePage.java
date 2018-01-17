package c2info_OrderBuk_UIPages;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import c2info_OrderBuk_TestBase.TestBase;

public class DigitizePage extends TestBase{

	public static final Logger log = Logger.getLogger(DigitizePage.class.getName());
	WebDriverWait wait = new WebDriverWait(driver, 45);
	
	@FindBy(id="customerform-patientname")
	WebElement PatientName ;
	
	@FindBy(id="customerform-doctorname")
	WebElement DoctorName ;
	
	@FindBy(id="customerform-doctorlicense")
	WebElement DoctorLicense ;
	
	@FindBy(id="btnsubmit")
	WebElement SubmitBtn ;
	
	@FindBy(id="btninv")
	WebElement InvalidBtn ;
	
	@FindBy(id="btnrequest")
	WebElement NewItemRequestBtn ;
	
	@FindBy(id="dose1")
	WebElement DoseAsUnit ;
	
	@FindBy(id="ll")
	WebElement ItemSearchBox ;
	
	@FindBy(id="pickup1")
	WebElement PickUp ;
	
	@FindBy(id="reorder1")
	WebElement ReOrder ;
	
	@FindBy(xpath=".//span[contains(text(),'Order Sent to Ready For Order')]")
	WebElement OrderSuccessMsg ;
	
	@FindBy(xpath=".//tr[1]/td[2]")
	WebElement OrderIDInDS ;
	
	@FindBy(name="reason")
	WebElement OrderCancellationReason ;
	
	@FindBy(name="note")
	WebElement OrderCancellationRemark ;
	
	@FindBy(name="Cancel the Order")
	WebElement OrderCancellationSubmit ;
	
	public DigitizePage(){
		PageFactory.initElements(driver, this);
	}
	
	public void addPatientDetails(String PatientName,String DoctorName){
		this.PatientName.sendKeys(PatientName);
		this.DoctorName.sendKeys(DoctorName);
		this.DoctorLicense.sendKeys("1234");
		
	}
	
	public void addItem(String ItemName) throws InterruptedException {
		ItemSearchBox.click();
		ItemSearchBox.clear();
		Thread.sleep(2000);
		ItemSearchBox.sendKeys(ItemName);
		Thread.sleep(3000);
		SelectItemNameFromAutoSuggestionSearch(ItemName);
		waitforPageToLoad();
		
	}
	
	public ArrayList<String> getItemNames(){
		ArrayList<String> ItemNames = new ArrayList<String>();
		ItemNames.add("Crocin Advance 500mg Tab");
		ItemNames.add("Dolo 650mg Tab");
		ItemNames.add("Woodwards Gripe Water 130ml");
		ItemNames.add("Rantac 300mg Tab");
		
		return ItemNames ;
	}
	
	public void addItemsAndDosage() throws InterruptedException{
		ArrayList<String> itemnames = getItemNames();
		
		for(int i=1; i<=4;i++){
			
			
			addItem(itemnames.get(i-1));
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
			
		}
		
	}
	
	public void selectDoseAsUnit(String option){
		Select select = new Select(DoseAsUnit);
		if(option.equalsIgnoreCase("Yes")){
		select.selectByIndex(0);
		}
		else if(option.equalsIgnoreCase("No")){
			select.selectByIndex(1);
		}
	}
	
	public void getMaxQtyValues(){
		
	}
	
	public void clickOnSubmit() throws InterruptedException{
		SubmitBtn.click();
		Thread.sleep(6000);
	}
	
	public String getSuccessMsg(){
		return OrderSuccessMsg.getText();
	}
	
	public String getOrderIDFromDigitizePage(){
		String OrderID = OrderIDInDS.getText();
		OrderID = OrderID.replaceAll(":","");
		OrderID = OrderID.trim();
		return OrderID ;
		
	}
}
