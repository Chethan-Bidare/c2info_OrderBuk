package c2info_OrderBuk_Listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import c2info_OrderBuk_TestBase.TestBase;

public class Listeners extends TestBase implements ITestListener{

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("YYYY_MM_DD_HH_MM_SS");
		
		String arg1 = result.getName();
		
		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
			String ReportDirectory = System.getProperty("user.dir")+"//src//main//java//c2info_OrderBuk_ScreenShots//" ;
			File destFile = new File(ReportDirectory+"Success//"+arg1+ "_"+formater.format(calendar.getTime())+".png") ;
			FileUtils.copyFile(srcFile, destFile);
			
			
			Reporter.log("<a href='"+destFile.getAbsolutePath()+"' ><img src = '"+destFile.getAbsolutePath()+"' hieght='100' width='100' /></a>");
			
			
			
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
		
	

	public void onTestFailure(ITestResult result) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("YYYY_MM_DD_HH_MM_SS");
		
		String arg1 = result.getName();
		
		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
			String ReportDirectory = System.getProperty("user.dir")+"//src//main//java//c2info_OrderBuk_ScreenShots//" ;
			File destFile = new File(ReportDirectory+"Failure//"+arg1+ "_"+formater.format(calendar.getTime())+".png") ;
			FileUtils.copyFile(srcFile, destFile);
			
			
			Reporter.log("<a href='"+destFile.getAbsolutePath()+"' ><img src = '"+destFile.getAbsolutePath()+"' hieght='100' width='100' /></a>");
			
			
			
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
