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

public class OrderConfirmed extends TestBase{
	
	public static final Logger log = Logger.getLogger(OrderConfirmed.class.getName());
	WebDriverWait wait = new WebDriverWait(driver, 45);
	
	@FindBy(xpath=".//*[@id='wrapper']/div/div[1]/div/div/div/label[1]")
	WebElement orderID ;
	
	@FindBy(xpath=".//*[@id='w0']/div[1]/div[1]/div[1]/div/div[1]/table/tbody/tr[3]/td[2]")
	WebElement customerName ;
	
	@FindBy(xpath=".//*[@id='w0']/div[1]/div[1]/div[1]/div/div[1]/table/tbody/tr[4]/td[2]")
	WebElement mobileNum ;
	
	@FindBy(xpath=".//*[@id='w0']/div[1]/div[1]/div[1]/div/div[1]/table/tbody/tr[2]/td[2]")
	WebElement headerAmt ;
	
	@FindBy(xpath=".//*[@id='w0']/div[1]/div[1]/div[1]/div/div[1]/table/tbody/tr[5]/td[2]")
	WebElement headerModeOfPayment ;
	
	@FindBy(xpath=".//*[@id='w0']/div[1]/div[1]/div[1]/div/div[1]/table/tbody/tr[6]/td[2]")
	WebElement headerDelivery ;
	
	@FindBy(xpath=".//*[@id='w0']/div[1]/div[1]/div[1]/div/div[3]/address")
	WebElement billingAddress ;
	
	@FindBy(xpath=".//*[@id='w0']/div[1]/div[1]/div[1]/div/div[2]/address")
	WebElement shippingAddress ;
	
	@FindBy(xpath=".//*[@id='gdtotal']/td[3]/span")
	WebElement grandTotal ;
	
	@FindBy(xpath=".//*[@id='w0']/div[2]/div[1]/div[3]/table/tbody/tr[1]/td[3]/span")
	WebElement grandSubTotal ;
	
	@FindBy(xpath=".//*[@id='w0']/div[2]/div[1]/div[3]/table/tbody/tr[2]/td[3]/span")
	WebElement DiscVal ;
	
	@FindBy(xpath=".//*[@id='w0']/div[2]/div[1]/div[3]/table/tbody/tr[3]/td[3]/span")
	WebElement DeliveryVal ;
	
	@FindBy(xpath=".//*[@id='w0']/div[2]/div[1]/div[2]/span")
	WebElement footerMOD ;
	
	@FindBy(xpath=".//*[@id='w0']/div[2]/div[1]/div[1]/span")
	WebElement footerMOP ;
	
	/*@FindBy(xpath="")
	WebElement xyz ;
	
	@FindBy(xpath="")
	WebElement xyz ;
	
	@FindBy(xpath="")
	WebElement xyz ;*/
	
	public OrderConfirmed(){
		PageFactory.initElements(driver, this);
	}
	
	public String getOrderIDFromOCPage(){
		waitforPageToLoad();
		String OrderID = orderID.getText();
		OrderID = OrderID.substring(OrderID.length()-6,OrderID.length());
		OrderID = OrderID.trim();
		System.out.println(OrderID);
		return OrderID ;
		
	}
	
	public String getCustomerNameInOCpage(){
		String custName = customerName.getText();
		custName.trim();
		custName = custName.substring(2);
		custName = custName.replaceAll(" ","");
		return custName ;
	}
	
	public String getCustomerNumberInOCpage(){
		String custNum = mobileNum.getText();
		custNum.trim();
		custNum = custNum.substring(2);
		custNum = custNum.trim();
		return custNum ;
	}
	
	public String getHeaderAmtInOCpage(){
		String headerAmount = headerAmt.getText();
		headerAmount.trim();
		headerAmount = headerAmount.substring(2);
		headerAmount = headerAmount.trim();
		return headerAmount;
	}
	
	public String getModeOfPaymentinOCpage(){
		String headerMOP = headerModeOfPayment.getText();
		headerMOP.trim();
		headerMOP = headerMOP.substring(2);
		headerMOP = headerMOP.trim();
		return headerMOP ;
	}
	
	public String getDeliveryORPickUpInOCpage(){
		String delPick = headerDelivery.getText();
		delPick.trim();
		delPick = delPick.substring(2);
		delPick = delPick.trim();
		return delPick ;
	}
	
	public String getBillingAddressInOCpage(){
		return billingAddress.getText();
	}
	
	public String getShippingAddressInOCPage(){
		return shippingAddress.getText();
	}
	
	public double getGrandTotalInOCpage(){
		String gt = grandTotal.getText().trim();
		double grandTotal = Double.parseDouble(gt);
		return grandTotal;
	}
	
	public double getGrandSubTotalInOCpage(){
		String st = grandSubTotal.getText().trim();
		double subtotal = Double.parseDouble(st);
		return subtotal ; 
	}
	
	public double getDiscountValueInOCpage(){
		String disc = DiscVal.getText().trim();
		double discount = Double.parseDouble(disc);
		return discount ;
	}
	
	public double getDeliveryValueInOCpage(){
		String deli = DeliveryVal.getText().trim();
		double delCharges = Double.parseDouble(deli);
		return delCharges ;
	}
	
	public String getFooterMODInOCpage(){
		return footerMOD.getText();
	}
	
	public String getFooterMOPInOCpage(){
		return footerMOP.getText();
	}
	
	public ArrayList<String> getItemList() throws InterruptedException{
		int noOfTransactions = driver.findElements(By.xpath(".//*[@class='panel panel-primary']")).size();
		ArrayList<String> itemList = new ArrayList<String>();
		
		for(int i=0; i<noOfTransactions; i++){
			int j=i+1 ;
			if(i==0){
				String itemName = driver.findElement(By.xpath(".//*[@id='DataTables_Table_"+i+"']/tbody/tr["+j+"]/td[1]")).getText();
				itemList.add(itemName);
			}
			else{
				List<WebElement> noOfTrans = driver.findElements(By.xpath(".//*[@id='accordion']/div/div[1]"));
				WebElement temp = noOfTrans.get(i);
				temp.click();
				Thread.sleep(2000);
				String itemName = driver.findElement(By.xpath(".//*[@id='DataTables_Table_"+i+"']/tbody/tr["+j+"]/td[1]")).getText();
				itemList.add(itemName);
			}
		}
		for(String value : itemList){
			System.out.println(value);			
			
        }
		return itemList;
	}
	
	public HashMap<String,Integer> getItemNamesWithQty() throws InterruptedException{
		
		int noOfTransactions = driver.findElements(By.xpath(".//*[@class='panel panel-primary']")).size();
		HashMap<String,Integer> itemNamesWithQty = new HashMap<String, Integer>();
		for(int i=0; i<noOfTransactions;i++){
			int tablesize=driver.findElements(By.xpath(".//*[@id='DataTables_Table_"+i+"']/tbody/tr")).size();
			for(int j=1; j<=tablesize; j++)
			if(i==0){
				String itemName = driver.findElement(By.xpath(".//*[@id='DataTables_Table_"+i+"']/tbody/tr["+j+"]/td[1]")).getText();
				String qty = driver.findElement(By.xpath(".//*[@id='DataTables_Table_"+i+"']/tbody/tr["+j+"]/td[3]")).getText();
				int quantity = Integer.parseInt(qty);
				itemNamesWithQty.put(itemName, quantity);
			}
			else{
				List<WebElement> noOfTrans = driver.findElements(By.xpath(".//*[@id='accordion']/div/div[1]"));
				WebElement temp = noOfTrans.get(i);
				temp.click();
				Thread.sleep(2000);
				String itemName = driver.findElement(By.xpath(".//*[@id='DataTables_Table_"+i+"']/tbody/tr["+j+"]/td[1]")).getText();
				String qty = driver.findElement(By.xpath(".//*[@id='DataTables_Table_"+i+"']/tbody/tr["+j+"]/td[3]")).getText();
				int quantity = Integer.parseInt(qty);
				itemNamesWithQty.put(itemName, quantity);
			}
		}
		for(String val :itemNamesWithQty.keySet()){
			
			String key = val.toString();
			int value = itemNamesWithQty.get(val);
			System.out.println("Key -"+key +"----- Value-"+value);
		}
		return itemNamesWithQty;
	}
	
	

}
