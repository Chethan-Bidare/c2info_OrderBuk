package c2info_OrderBuk_TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

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
		SimpleDateFormat formatter = new SimpleDateFormat();
		extent = new ExtentReports(System.getProperty("user.dir")+"//src//main//java//c2info//OrderBuk//Reports"+formatter.format(calendar.getTime())+".html",false);
	}
	
	

}
