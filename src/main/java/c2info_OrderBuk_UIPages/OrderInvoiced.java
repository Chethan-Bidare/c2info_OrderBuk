package c2info_OrderBuk_UIPages;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import c2info_OrderBuk_TestBase.TestBase;

public class OrderInvoiced extends TestBase{

	public static final Logger log = Logger.getLogger(OrderInvoiced.class.getName());
	WebDriverWait wait = new WebDriverWait(driver, 45);
	
	
	
	@FindBy(xpath=".//*[contains(@class,'col-md-4 col-sm-12 text-right')]/button")
	WebElement orderDownloadButton ;
	
	public OrderInvoiced(){
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnOrderDownloadButton(){
		orderDownloadButton.click();
	}
	
	public double getCalculatedTotalOfEachSubTotal() throws InterruptedException{
		int noOfTransactions = driver.findElements(By.xpath(".//*[@class='panel panel-primary']")).size();
		double subtot = 0;
		double grandSubtotal = 0;
		
		ArrayList<Double> totalList =new ArrayList<Double>(); 
				
		for(int i=1; i<=noOfTransactions; i++){
			if(i==1){
				String subTotal = driver.findElement(By.xpath(".//*[@id='collapse"+i+"']/div/div[3]/div[1]/p/label[3]")).getText();
				subTotal = subTotal.trim().replaceAll("Subtotal ","");
				subTotal = subTotal.replaceAll(":", "");
				subTotal = subTotal.trim();
				 subtot = Double.parseDouble(subTotal);
				totalList.add(subtot);
			}
			else{
				driver.findElement(By.xpath(".//*[@id='accordion"+i+"']/div/div[1]")).click();
				Thread.sleep(2000);
				String subTotal = driver.findElement(By.xpath(".//*[@id='collapse"+i+"']/div/div[3]/div[1]/p/label[3]")).getText();
				subTotal = subTotal.trim().replaceAll("Subtotal ","");
				subTotal = subTotal.replaceAll(":", "");
				subTotal = subTotal.trim();
				 subtot = Double.parseDouble(subTotal);
				totalList.add(subtot);
			}
		}
		for(double value : totalList){
			System.out.println(value);			
			grandSubtotal +=value; 
        }
		return grandSubtotal;
	}
	
	public double getItemwiseTotal() throws InterruptedException{
		waitforPageToLoad();
		Thread.sleep(5000);
		double sum = 0;
		ArrayList<Double> value = new ArrayList<Double>();
		int noOfTransactions = driver.findElements(By.xpath(".//*[@class='panel panel-primary']")).size();
		for(int i=1; i<=noOfTransactions; i++){
			int x = i-1 ;
			int noOfItems = driver.findElements(By.xpath(".//*[@id='DataTables_Table_"+x+"']/tbody/tr")).size();
			for(int j=1;j<=noOfItems; j++){
				if(i>1){
					driver.findElement(By.xpath(".//*[@id='accordion"+i+"']/div/div[1]")).click();
					Thread.sleep(2000);
					String itemValue = driver.findElement(By.xpath(".//*[@id='DataTables_Table_"+x+"']/tbody/tr["+j+"]/td[7]")).getText();
					itemValue = itemValue.trim();
					itemValue = itemValue.replaceAll("Rs.", "");
					double itemPr = Double.parseDouble(itemValue);
					value.add(itemPr);
				}
				else{
				String itemValue = driver.findElement(By.xpath(".//*[@id='DataTables_Table_"+x+"']/tbody/tr["+j+"]/td[7]")).getText();
				itemValue = itemValue.trim();
				itemValue = itemValue.replaceAll("Rs.", "");
				double itemPr = Double.parseDouble(itemValue);
				value.add(itemPr);
				}   
			}
			
		}
		for(double i : value){
			sum +=i ;
		}
		return sum ;
		
	}
	
}
