package c2info_OrderBuk_UIPages;


import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import c2info_OrderBuk_TestBase.TestBase;

public class ToBeVerified extends TestBase{

	public static final Logger log = Logger.getLogger(ToBeVerified.class.getName());
	WebDriverWait wait = new WebDriverWait(driver, 45);
	
	@FindBy(xpath=".//*[@id='bootstrap-table_next']")
	WebElement NextBtn ;
	
	@FindBy(xpath=".//*[@class='paginate_button next disabled']")
	WebElement NxtBtnDisabled ;
	
	@FindBy(xpath="//p[contains(text(),'To Be Verified')]")
	WebElement ToBeVerifiedLink ;
	
	@FindBy(xpath=".//*[@id='bootstrap-table']/tbody/tr[1]")
	WebElement FirstOrder ;
	
	@FindBy(xpath=".//button[contains(text(),'Invalid')]")
	WebElement InvalidButton ;
	
	@FindBy(xpath=".//button[contains(text(),'Valid')]")
	WebElement ValidButton ;
	
	@FindBy(xpath=".//img")
	WebElement Image ;
	
	@FindBy(xpath=".//button[contains(text(),'Submit')]")
	WebElement InvalidOrderSubmit ;
	
	@FindBy(xpath=".//*[@id='bootstrap-table']/tbody/tr[1]/td[1]")
	WebElement FirstOrderID ;
	
	@FindBy(id="headid")
	WebElement PrescPageOrderID ;
	
	public ToBeVerified(){
		PageFactory.initElements(driver, this);
	}
	
	public int getCountOfOrdersToBeVerified(){
		
		int OrderSize=0;
		ToBeVerifiedLink.click();
		waitforPageToLoad();
		List<WebElement> paginate = driver.findElements(By.xpath(".//*[@id='bootstrap-table_paginate']/ul/li"));
		int paginateSize = paginate.size();
		
		for(int i=2; i<paginateSize;i++){
			
			int OrderSizePerPage = driver.findElements(By.xpath(".//*[@id='bootstrap-table']/tbody/tr")).size();	
			OrderSize+= OrderSizePerPage ;
			System.out.println(OrderSize);
			NextBtn.click();
			waitforPageToLoad();
			
			
		}
		
			log.info("Order Count :"+OrderSize);	
		
		
		return OrderSize ;
	}
	
	public void selectAnOrder(){
		FirstOrder.click();
		log.info("Clicked on the first Order");
		waitforPageToLoad();
	}
	
	public void makeOrderInvalid(){
		wait.until(ExpectedConditions.visibilityOf(InvalidButton));
		InvalidButton.click();
		log.info("Clicked on Invalid Button");
		wait.until(ExpectedConditions.visibilityOf(InvalidOrderSubmit));
		InvalidOrderSubmit.click();
		log.info("Clicked on Submit Button");
	}
	
	public void makeOrderValid(){
		ValidButton.click();
		log.info("Clicked on Valid Button");
		waitforPageToLoad();
	}
	
	public String getOrderID(){
		return FirstOrderID.getText();
	}
	
	public String getCancellationMsg(){
		return driver.findElement(By.xpath(".//span[contains(text(),'Order Cancelled Successfully')]")).getText();
	}
	
	public Boolean imageDisplay() throws InterruptedException{
		//wait.until(ExpectedConditions.visibilityOf(Image));
		Thread.sleep(5000);
		if(Image.isDisplayed()==true){	
		return true;
		}
		else
			return false ;
		
	}
	
	public String getOrderIDInPrescImagePage() throws InterruptedException{
		Thread.sleep(5000);
		String orderid = PrescPageOrderID.getText();
		orderid = orderid.replaceAll("Prescription of order #","").trim();
		return orderid ;
	}
	
}





























