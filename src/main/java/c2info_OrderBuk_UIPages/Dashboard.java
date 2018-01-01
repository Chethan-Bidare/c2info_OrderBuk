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

public class Dashboard extends TestBase {
	
	public static final Logger log = Logger.getLogger(Dashboard.class.getName());
	WebDriverWait wait = new WebDriverWait(driver, 45);
	
	public String bucketName ;
	
	@FindBy(xpath=".//*[@id='side-menu']/li[2]")
	WebElement Dashboardbutton ;
	
	
	public Dashboard(){
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	public ArrayList<String> getBucketNames() throws InterruptedException{
		Thread.sleep(5000);
		/*waitforPageToLoad();
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(".//p"))));*/
		ArrayList<String> arr = new ArrayList<String>();
		int arrSize = driver.findElements(By.xpath(".//p")).size();
		log.info("Getting the count of bucket links : "+arrSize);
		List<WebElement> bucketlinks = driver.findElements(By.xpath(".//p"));
		log.info("Storing the webelements of all the buckets into a list");
		for(int i=0; i<arrSize; i++){
			WebElement bucketlink = bucketlinks.get(i);
			arr.add(bucketlink.getText());
			log.info("Storing the text of all the links into an arraylist");
		}
		return arr ;
	}
	
	public ArrayList<String> getpageTitles() throws InterruptedException{
		ArrayList<String> arr = getBucketNames();
		ArrayList<String> pageTitlesDashboard = new ArrayList<String>();
		for(int i=0; i<arr.size(); i++){
			driver.findElement(By.xpath("//p[contains(text(),'"+arr.get(i)+"')]")).click();
			waitforPageToLoad();
			log.info("Clicking on the bucket named :"+arr.get(i));
			pageTitlesDashboard.add(getPageTitle());
			log.info("Storing the page title of the bucket :"+arr.get(i));
			Dashboardbutton.click();
			waitforPageToLoad();
			log.info("Navigating back to dashboard");
			
		}
		return pageTitlesDashboard ;
	}
}
