package c2info_OrderBuk_UIPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import c2info_OrderBuk_TestBase.TestBase;

public class LoginPage extends TestBase{

	public static final Logger log = Logger.getLogger(LoginPage.class.getName());
	WebDriverWait wait = new WebDriverWait(driver, 45);
	
	@FindBy(id="loginform-username")
	WebElement UserName ;
	
	@FindBy(id="loginform-password")
	WebElement Password ;
	
	@FindBy(name="login-button")
	WebElement LoginBtn ;
	
	@FindBy(xpath=".//*[@id='login-form']/div[2]/div/p")
	WebElement errorMsg ;
	
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	public void doLogin(String UserName,String pwd) throws InterruptedException{
		Thread.sleep(5000);
		this.UserName.clear();
		log.info("Clearing the Username field");
		this.UserName.sendKeys(UserName);
		log.info("Entering the Username :"+UserName);
		Password.clear();
		log.info("Clearing the Password field");
		Password.sendKeys(pwd);
		log.info("Entering the Password :"+pwd);
		LoginBtn.click();
		log.info("Clicked on Login button");
		waitforPageToLoad();
		
	}
	
	public String getInvalidUserNameOrPwdMSG(){
		return errorMsg.getText();
	}
}
