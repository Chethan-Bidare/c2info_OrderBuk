package c2info_OrderBuk_UIPages;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
	
	public ToBeVerified(){
		PageFactory.initElements(driver, this);
	}
	
	public int getCountOfOrdersToBeVerified(){
		
		int OrderSize=0;
		ToBeVerifiedLink.click();
		waitforPageToLoad();
		
		
		while(NextBtn.isEnabled()==true){
			int OrderSizePerPage = driver.findElements(By.xpath(".//*[@id='bootstrap-table']/tbody/tr")).size();
			OrderSize+= OrderSizePerPage ;
			System.out.println(OrderSize);
			NextBtn.click();
			waitforPageToLoad();
			
			
		}
		
			log.info("Order Count :"+OrderSize);	
		
		
		return OrderSize ;
	}
	
}
