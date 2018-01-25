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

public class OrderSentToServer extends TestBase{

	public static final Logger log = Logger.getLogger(OrderSentToServer.class.getName());
	WebDriverWait wait = new WebDriverWait(driver, 45);
	
	@FindBy(xpath=".//*[@id='wrapper']/div/div[1]/div/div/div/label[1]")
	WebElement orderID ;
	
	@FindBy(xpath=".//*[@id='wrapper']/div/div/div/div/div/div/div[1]/div[1]/div/div[1]/table/tbody/tr[2]/td[2]")
	WebElement headerAmt ;
	
	@FindBy(xpath=".//*[@id='wrapper']/div/div/div/div/div/div/div[1]/div[1]/div/div[1]/table/tbody/tr[3]/td[2]")
	WebElement customerName ;
	
	@FindBy(xpath=".//*[@id='wrapper']/div/div/div/div/div/div/div[1]/div[1]/div/div[1]/table/tbody/tr[4]/td[2]")
	WebElement mobileNum ;
	
	@FindBy(xpath=".//*[@id='wrapper']/div/div/div/div/div/div/div[1]/div[1]/div/div[1]/table/tbody/tr[5]/td[2]")
	WebElement headerModeOfPayment ;
	
	@FindBy(xpath=".//*[@id='wrapper']/div/div/div/div/div/div/div[1]/div[1]/div/div[1]/table/tbody/tr[6]/td[2]")
	WebElement headerDelivery ;
	
	@FindBy(xpath=".//*[@id='wrapper']/div/div/div/div/div/div/div[1]/div[1]/div/div[2]/address")
	WebElement shippingAddress ;
	
	@FindBy(xpath=".//*[@id='wrapper']/div/div/div/div/div/div/div[1]/div[1]/div/div[3]/address")
	WebElement billingAddress ;
	
	@FindBy(xpath=".//*[@id='gdtotal']/td[3]/span")
	WebElement grandTotal ;
	
	@FindBy(xpath=".//*[@class='table']/tbody/tr[1]/td[3]/span")
	WebElement grandSubTotal ;
	
	@FindBy(xpath=".//*[@class='table']/tbody/tr[2]/td[3]/span")
	WebElement DiscVal ;
	
	@FindBy(xpath=".//*[@class='table']/tbody/tr[3]/td[3]/span")
	WebElement DeliveryVal ;
	
	@FindBy(xpath=".//*[@class='col-sm-4 text-left']/span")
	WebElement footerMOP ;
	
	@FindBy(xpath=".//*[@class='col-sm-6 text-left']/span")
	WebElement footerMOD ;
	
	@FindBy(xpath=".//*[contains(@class,'table-responsive col-md-2 col-lg-2 col-sm-3 text-right mar-top')]/button")
	WebElement orderDownloadButton ;
	
	/*@FindBy(xpath=".//*[@class='col-sm-6 text-left']/span")
	WebElement footerMOD ;
	
	@FindBy(xpath=".//*[@class='col-sm-6 text-left']/span")
	WebElement footerMOD ;*/
	
	public OrderSentToServer(){
		PageFactory.initElements(driver, this);
	}
	
	public String getOrderIDFromOSSPage(){
		waitforPageToLoad();
		String OrderID = orderID.getText();
		OrderID = OrderID.substring(OrderID.length()-6,OrderID.length());
		OrderID = OrderID.trim();
		System.out.println(OrderID);
		return OrderID ;
		
	}
	
	public String getCustomerNameInOSSpage(){
		String custName = customerName.getText();
		custName.trim();
		custName = custName.substring(2);
		custName = custName.replaceAll(" ","");
		return custName ;
	}
	
	public String getCustomerNumberInOSSpage(){
		String custNum = mobileNum.getText();
		custNum.trim();
		custNum = custNum.substring(2);
		custNum = custNum.trim();
		return custNum ;
	}
	
	public String getHeaderAmt(){
		String headerAmount = headerAmt.getText();
		headerAmount.trim();
		headerAmount = headerAmount.substring(2);
		headerAmount = headerAmount.trim();
		return headerAmount;
	}
	
	public String getModeOfPayment(){
		String headerMOP = headerModeOfPayment.getText();
		headerMOP.trim();
		headerMOP = headerMOP.substring(2);
		headerMOP = headerMOP.trim();
		return headerMOP ;
	}
	
	public String getDeliveryORPickUp(){
		String delPick = headerDelivery.getText();
		delPick.trim();
		delPick = delPick.substring(2);
		delPick = delPick.trim();
		return delPick ;
	}
	
	public String getBillingAddress(){
		return billingAddress.getText();
	}
	
	public String getShippingAddress(){
		return shippingAddress.getText();
	}
	
	public double getItemwiseTotal() throws InterruptedException{
		waitforPageToLoad();
		Thread.sleep(5000);
		double sum = 0;
		ArrayList<Double> value = new ArrayList<Double>();
		int noOfTransactions = driver.findElements(By.xpath(".//*[@class='panel panel-primary']")).size();
		for(int i=1; i<=noOfTransactions; i++){
			int noOfItems = driver.findElements(By.xpath(".//*[@id='collapse"+i+"']/div/div[1]/table/tbody/tr")).size();
			for(int j=1;j<=noOfItems; j++){
				if(i>1){
					driver.findElement(By.xpath(".//*[@id='accordion"+i+"']/div/div[1]")).click();
					Thread.sleep(2000);
					String itemValue = driver.findElement(By.xpath(".//*[@id='collapse"+i+"']/div/div[1]/table/tbody/tr["+j+"]/td[5]")).getText();
					itemValue = itemValue.trim();
					itemValue = itemValue.replaceAll("Rs.", "");
					double itemPr = Double.parseDouble(itemValue);
					value.add(itemPr);
				}
				else{
				String itemValue = driver.findElement(By.xpath(".//*[@id='collapse"+i+"']/div/div[1]/table/tbody/tr["+j+"]/td[5]")).getText();
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
	
	public double getGrandTotal(){
		String gt = grandTotal.getText().trim();
		double grandTotal = Double.parseDouble(gt);
		return grandTotal;
	}
	
	public double getGrandSubTotal(){
		String st = grandSubTotal.getText().trim();
		double subtotal = Double.parseDouble(st);
		return subtotal ; 
	}
	
	public double getCalculatedTotalOfEachSubTotal() throws InterruptedException{
		int noOfTransactions = driver.findElements(By.xpath(".//*[@class='panel panel-primary']")).size();
		double subtot = 0;
		double grandSubtotal = 0;
		
		ArrayList<Double> totalList =new ArrayList<Double>(); 
				
		for(int i=1; i<=noOfTransactions; i++){
			if(i==1){
				String subTotal = driver.findElement(By.xpath(".//*[@id='collapse"+i+"']/div/div[2]/div[1]/p/label[3]")).getText();
				subTotal = subTotal.trim().replaceAll("Subtotal ","");
				subTotal = subTotal.replaceAll(":", "");
				subTotal = subTotal.trim();
				 subtot = Double.parseDouble(subTotal);
				totalList.add(subtot);
			}
			else{
				driver.findElement(By.xpath(".//*[@id='accordion"+i+"']/div/div[1]")).click();
				Thread.sleep(2000);
				String subTotal = driver.findElement(By.xpath(".//*[@id='collapse"+i+"']/div/div[2]/div[1]/p/label[3]")).getText();
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
	
	public double getDiscountValue(){
		String disc = DiscVal.getText().trim();
		double discount = Double.parseDouble(disc);
		return discount ;
	}
	
	public double getDeliveryValue(){
		String deli = DeliveryVal.getText().trim();
		double delCharges = Double.parseDouble(deli);
		return delCharges ;
	}
	
	public String getFooterMOD(){
		return footerMOD.getText();
	}
	
	public String getFooterMOP(){
		return footerMOP.getText();
	}
	
	public void clickOnOrderDownloadPDFButton(){
		orderDownloadButton.click();
	}
	
	
	public void clickOnInvDownloadPDFButton() throws InterruptedException{
		List<WebElement> invList = driver.findElements(By.xpath(".//*[@class='panel panel-primary']"));
		
		for(int i=1; i<=invList.size(); i++){
			if(i==1){
				driver.findElement(By.xpath(".//*[@id='collapse"+i+"']/div/div[2]/div[2]/div[4]/button")).click();
			}
			else{
				driver.findElement(By.xpath(".//*[@id='accordion"+i+"']/div/div[1]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath(".//*[@id='collapse"+i+"']/div/div[2]/div[2]/div[4]/button")).click();
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
}
