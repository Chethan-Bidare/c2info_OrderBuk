package c2info_OrderBuk_UIPages;

import java.util.ArrayList;
import java.util.HashMap;
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
	
	@FindBy(xpath=".//*[@class='fa fa-sign-out']")
	WebElement SignOut ;
	
	@FindBy(xpath=".//*[@class='m-r-sm text-muted welcome-message']")
	WebElement WelcomeUser ;
	
	@FindBy(xpath=".//*[@id='side-menu']/li[2]/a")
	WebElement DashboardIcon ;
	
	@FindBy(xpath=".//*[@id='side-menu']/li[3]/ul/li/a")
	WebElement ManageUsers;
	
	@FindBy(xpath=".//*[@id='side-menu']/li[5]/ul/li/a")
	WebElement ManageCustomers;
	
	@FindBy(xpath=".//*[@id='side-menu']/li[3]/a")
	WebElement ManageUserLink ;
	
	@FindBy(xpath=".//*[@id='side-menu']/li[5]/a")
	WebElement ManageCustLink ;
	
	@FindBy(xpath=".//*[@id='side-menu']/li[4]/a")
	WebElement BucketLinks ;
	
	@FindBy(xpath=".//*[@id='side-menu']/li[6]/a")
	WebElement EmailSmsLinks ;
	
	@FindBy(xpath=".//*[@id='side-menu']/li[7]/a")
	WebElement ImgVideoLinks ;
	
	@FindBy(xpath=".//*[@id='side-menu']/li[7]/ul/li[1]/a")
	WebElement ImageUpload ;
	
	@FindBy(xpath=".//*[@id='side-menu']/li[7]/ul/li[2]/a")
	WebElement Image ;
	
	@FindBy(xpath=".//*[@id='side-menu']/li[7]/ul/li[3]/a")
	WebElement Video ;
	
	
	
	public Dashboard(){
		PageFactory.initElements(driver, this);
	}
	
	public void selectBucket(String bucketName){
		driver.findElement(By.xpath(".//p[contains(text(),'"+bucketName+"')]")).click();
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
	
	public HashMap<String, Integer> getOrderCountOfEachBuckets() throws InterruptedException{
		Thread.sleep(5000);
		int count1 ;
		ArrayList<String> temp = getBucketNames();
		HashMap<String, Integer> hmp = new HashMap<String, Integer>();
		List<WebElement> OrderCountLinks = driver.findElements(By.xpath(".//h3"));
		log.info("Storing the webelements of all the buckets into a list");
		for(int i=0; i<OrderCountLinks.size(); i++){
			String count = OrderCountLinks.get(i).getText() ;
			if(count.equals(" ")){
				count1= 0;
			}
			else{
				count1 = Integer.parseInt(count);
			}
			
		
			hmp.put(temp.get(i).toUpperCase(),count1);
			log.info("Adding the values to an array : "+count1);		
	}
		
		
		return hmp ;
	}
	
	public void logOut(){
		waitforPageToLoad();
		SignOut.click();
		log.info("Clicked on LogOut "+SignOut);
	}
	
	public String getUserName(){
		return WelcomeUser.getText();
	}
	
	public void clickOnDashboardinMenu(){
		DashboardIcon.click();
		waitforPageToLoad();
	}
	
	public void clickOnManageUsersInMenu(){
		ManageUserLink.click();
		ManageUsers.click();
		waitforPageToLoad();
	}
	
	public void clickOnManageCustomersInMenu(){
		ManageCustLink.click();
		ManageCustomers.click();
		waitforPageToLoad();
	}
	
	public void clickOnImageUploadInMenu(){
		ImgVideoLinks.click();
		ImageUpload.click();
		waitforPageToLoad();
	}
	
	public void clickOnImageInMenu(){
		ImgVideoLinks.click();
		Image.click();
		waitforPageToLoad();
	}
	
	public void clickOnVideoInMenu(){
		ImgVideoLinks.click();
		Video.click();
		waitforPageToLoad();
	}
}
