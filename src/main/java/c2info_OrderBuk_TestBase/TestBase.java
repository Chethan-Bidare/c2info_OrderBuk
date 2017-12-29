package c2info_OrderBuk_TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import c2info_OrderBuk_ExcelReader.ExcelReader;

public class TestBase {
	
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	public WebDriver driver ;
	public static ExtentReports extent ;
	public static ExtentTest Test ;
	public static ITestResult Result ;
	public Properties OR = new Properties();
	public Properties APP = new Properties();
	
	
	public void LoadfromORproperties() throws IOException{
		File path = new File(System.getProperty("user.dir")+"//src//main//java//c2info//OrderBuk//Config//OR.properties");
		FileInputStream fis = new FileInputStream(path);
		OR.load(fis);
	}
	
	public void LoadfromAPPproperties() throws IOException{
		File path = new File(System.getProperty("user.dir")+"//src//main//java//c2info//OrderBuk//Config//APP.properties");
		FileInputStream fis = new FileInputStream(path);
		APP.load(fis);
	}
	
	static{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("DD_MM_YYYY_HH_MM_SS");
		extent = new ExtentReports(System.getProperty("user.dir")+"//src//main//java//c2info//OrderBuk//Reports"+formatter.format(calendar.getTime())+".html",false);
	}
	
	public String[][] ReadExcel(String SheetName,String ExcelName){
		String path = System.getProperty("user.dir")+"//src//main//java//c2info//OrderBuk//Data//TestData.xlsx";
		ExcelReader excel = new ExcelReader(path);
		String[][] TestData = excel.getdatafromSheet(SheetName, ExcelName);
		return TestData;
		
	}
	
	public void init() throws IOException{
		LoadfromORproperties();
		LoadfromAPPproperties();
		selectBrowser(OR.getProperty("BrowserName"));
		waitForElementToLoad();
		clearHistory();
		getBaseUrl(OR.getProperty("URL"));
		PropertyConfigurator.configure(System.getProperty("user.dir")+"//log4j.properties");
		
	}
	
	public void selectBrowser(String BrowserName){
		if(BrowserName.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//Drivers//geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(BrowserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
			driver = new ChromeDriver();
		}
	}
	
	public void getBaseUrl(String BaseUrl){
		driver.get(BaseUrl);
		try {
			driver.manage().window().maximize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void waitForElementToLoad(){
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
	}
	
	public void clearHistory(){
		driver.manage().deleteAllCookies();
	}
	
	public void getScreenshot(String methodName){
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("DD_MM_YYYY_HH_MM_SS");
		
		try {
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String ReportDirectory = System.getProperty("user.dir")+"//src//main//java//c2info//OrderBuk//Screenshots//";
			File destFile = new File(ReportDirectory+"_"+methodName+formatter.format(calendar.getTime()+".png"));
			FileUtils.copyFile(srcFile, destFile);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeBrowser(){
		driver.close();
	}
	
	public void refreshPage(){
		driver.navigate().refresh();
	}
	
	

}
