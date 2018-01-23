package c2info_OrderBuk_UIPages;

import org.apache.log4j.Logger;
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
	
/*	@FindBy(xpath=".//*[@id='wrapper']/div/div[1]/div/div/div/label[1]")
	WebElement orderID ;*/
	
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
	
	
	
	
	
	
}
