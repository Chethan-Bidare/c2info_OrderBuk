package c2info_OrderBuk_UIPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import c2info_OrderBuk_TestBase.TestBase;

public class OrderQuery extends TestBase{
	
	public static final Logger log = Logger.getLogger(ToBeVerified.class.getName());
	WebDriverWait wait = new WebDriverWait(driver, 45);
	
	@FindBy(xpath=".//*[@id='bootstrap-table_filter']/label/input")
	WebElement Searchbox ;
	
	public OrderQuery(){
		PageFactory.initElements(driver, this);
	}
	
	public void SearchOrder(String OrderID) throws InterruptedException{
		
		Searchbox.clear();
		Searchbox.sendKeys(OrderID);
	}
	
	public String getStatus(){
		return driver.findElement(By.xpath(".//*[@id='bootstrap-table']/tbody/tr[1]/td[5]")).getText();
	}

}
