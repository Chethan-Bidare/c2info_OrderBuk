package c2info_OrderBuk_UIPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
	
	
	public DigitizePage(){
		PageFactory.initElements(driver, this);
	}
	
	public void addPatientDetails(String PatientName,String DoctorName){
		this.PatientName.sendKeys(PatientName);
		this.DoctorName.sendKeys(DoctorName);
		this.DoctorLicense.sendKeys("1234");
		
	}
	
	public void addItem(String ItemName){
		ItemSearchBox.click();
		ItemSearchBox.sendKeys(ItemName);
		SelectItemNameFromAutoSuggestionSearch(ItemName);
		waitforPageToLoad();
		
	}
	
	public void addItemsAndDosage(){
		for(int i=1; i<=4;i++){
			addItem(APP.getProperty("ItemName"+i+""));
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
	
	public void clickOnSubmit(){
		SubmitBtn.click();
		waitforPageToLoad();
	}
	
	public String getSuccessMsg(){
		return OrderSuccessMsg.getText();
	}
}
