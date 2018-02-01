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

public class ReadyForOrder extends TestBase{

	public static final Logger log = Logger.getLogger(ReadyForOrder.class.getName());
	WebDriverWait wait = new WebDriverWait(driver, 45);
	
	@FindBy(xpath=".//*[@id='wrapper']/div/div[1]/div/div/div/label")
	WebElement OrderIDInRFO ;
	
	@FindBy(xpath=".//*[@id='wrapper']/div/div[1]/div/div/div/div/div[1]/form/div[1]")
	WebElement CustomerNameInRFO ;
	
	@FindBy(xpath=".//*[@id='wrapper']/div/div[1]/div/div/div/div/div[1]/form/div[3]")
	WebElement CustomerNumberInRFO ;
	
	@FindBy(id="subtotal")
	WebElement SubTotal ;
	
	@FindBy(id="discount")
	WebElement discount ;
	
	@FindBy(id="del")
	WebElement deliverycharge ;
	
	@FindBy(id="total")
	WebElement GrandTotal ;
	
	@FindBy(id="btnconfirm")
	WebElement ConfirmBtn ;
	
	@FindBy(id="reverify")
	WebElement reverifyBtn ;
	
	@FindBy(id="textbr")
	WebElement Remark ;
	
	@FindBy(xpath=".//*[@id='bootstrap-table_filter']/label/input")
	WebElement Searchbox ;
	
	@FindBy(id="cancel")
	WebElement cancelBtn ;
	
	@FindBy(id="btncancel")
	WebElement ordercancelbtn ;
	
	public ReadyForOrder(){
		PageFactory.initElements(driver, this);
	}
	
	public void selectOrder(String OrderID) throws InterruptedException{
		List<WebElement> orderIDs = driver.findElements(By.xpath(".//*[@id='bootstrap-table']/tbody/tr/td[1]"));
		for(WebElement we : orderIDs){
			if(we.getText().equals(OrderID)){
				we.click();
				
			}
			
		}
		Thread.sleep(5000);
	}
	
	public String getOrderIDFromRFOPage(){
		String OrderID = OrderIDInRFO.getText();
		OrderID = OrderID.substring(OrderID.length()-6,OrderID.length());
		OrderID = OrderID.trim();
		System.out.println(OrderID);
		return OrderID ;
		
	}
	
	public ArrayList<String> getItemNames(){
		ArrayList<String> ItemNames = new ArrayList<String>();
		List<WebElement> itemlist = driver.findElements(By.xpath(".//*[@id='printTable']/tbody/tr"));
		for(int i=1; i<=itemlist.size(); i++){
			String temp = driver.findElement(By.xpath(".//*[@id='printTable']/tbody/tr["+i+"]/td[2]")).getText();
			ItemNames.add(temp);
		}
		return ItemNames ;
	}
	
	public String getCustomerNameInRFOpage(){
		String custName = CustomerNameInRFO.getText();
		custName.trim();
		custName = custName.substring(16);
		return custName ;
	}
	
	public String getCustomerNumberInRFOpage(){
		String custNum = CustomerNumberInRFO.getText();
		custNum.trim();
		custNum = custNum.substring(16);
		return custNum ;
	}
	
	public ArrayList<Double> getItemwiseAmt() throws InterruptedException{
		Thread.sleep(2000);
		ArrayList<Double> ItemAmtlist = new ArrayList<Double>();
		List<WebElement> itemlist = driver.findElements(By.xpath(".//*[@id='printTable']/tbody/tr"));
		for(int i=0; i<itemlist.size(); i++){
			String temp = driver.findElement(By.xpath(".//*[@id='total_value"+i+"']")).getAttribute("value");
			System.out.println(temp);
			temp = temp.trim();
			double temp1 = Double.parseDouble(temp);
			ItemAmtlist.add(temp1);
		}
		return ItemAmtlist ;
	}
	
	public double getItemWiseTotalAmt() throws InterruptedException{
		double total = 0;
		ArrayList<Double> ItemAmttot = getItemwiseAmt();
		for(double sum : ItemAmttot ){
			total +=sum ;
		}
		return total ;
	}
	
	public double getSubTotal() throws InterruptedException{
		Thread.sleep(2000);
		String subtotal = SubTotal.getText();
		double subTotalAmt = Double.parseDouble(subtotal);
		return subTotalAmt ;
	}
	
	public double getDiscount() throws InterruptedException{
		Thread.sleep(2000);
		String disc = discount.getText();
		double discount = Double.parseDouble(disc);
		return discount ;
	}
	
	public double getDeliverycharge() throws InterruptedException{
		Thread.sleep(2000);
		String del = deliverycharge.getText();
		double delivery = Double.parseDouble(del);
		return delivery ;
	}
	
	public double getGrandTotal() throws InterruptedException{
		Thread.sleep(2000);
		String total = GrandTotal.getText();
		double GrandTot = Double.parseDouble(total);
		return GrandTot ;
	}
	
	public void clickOnConfirmBtnInRFOpage() throws InterruptedException{
		ConfirmBtn.click();
		Thread.sleep(5000);
	}
	
	public void clickOnCancelBtnInRFOpage(){
		cancelBtn.click();
	}
	
	public void enterCancelDetails(){
		Remark.sendKeys("order cancel");
		ordercancelbtn.click();
	}
	
	public void clickOnReverifyBtnInRFOpage(){
		reverifyBtn.click();
	}
	
	public void SearchOrder(String OrderID){
		Searchbox.clear();
		Searchbox.sendKeys(OrderID);
	}
	
	public void enterSearchdata(String ItemName){
		
	}
	
	public void UncheckAllItems() throws InterruptedException{
		Thread.sleep(3000);
		List<WebElement> itemlist = driver.findElements(By.xpath(".//*[@id='printTable']/tbody/tr"));
		for(int i=0; i<itemlist.size(); i++){
			driver.findElement(By.id("itemchk"+i+"")).click();
		}
		
	}
		
		public String select1Item() {
		
			List<WebElement> itemlist = driver.findElements(By.xpath(".//*[@id='printTable']/tbody/tr"));
			for(int i=0; i<itemlist.size(); i++){
				driver.findElement(By.id("itemchk"+i+"")).click();
			}
			String itemName = driver.findElement(By.id("01")).getText();
			System.out.println();
			driver.findElement(By.id("itemchk0")).click();
			return itemName ;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
