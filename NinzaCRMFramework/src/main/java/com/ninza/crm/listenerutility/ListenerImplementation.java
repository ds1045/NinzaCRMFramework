package com.ninza.crm.listenerutility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ninza.crm.baseTest.BaseClass;

public class ListenerImplementation implements ITestListener, ISuiteListener {

	public ExtentSparkReporter spark; 
	public ExtentReports report; 
	public ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		Reporter.log("Report Configuration", true);
		Date d=new Date(); 
		String newDate = d.toString().replace(" ","_").replace(":","_"); 
		spark=new ExtentSparkReporter("./AdvanceReports/report_"+newDate+".html"); 
		spark.config().setDocumentTitle("NinzaCRM Test Suite Results"); 
		spark.config().setReportName("CRM Report"); 
		spark.config().setTheme(Theme.STANDARD); 
		report=new ExtentReports(); 
		report.attachReporter(spark); 
		report.setSystemInfo("OS","Windows 11"); 
		report.setSystemInfo("Browser", "Edge"); 
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		report.flush();
		Reporter.log("Report BackUp", true);
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = report.createTest(result.getMethod().getMethodName());
		test.log(Status.INFO,"====<" + result.getMethod().getMethodName() + ">Execution STARTED====");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		Reporter.log("====<" + result.getMethod().getMethodName() + ">==SUCCESS==");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub	
		String testName = result.getMethod().getMethodName();
	    Date d = new Date();
	    String newDate = d.toString().replace(" ", "_").replace(":", "_");

	    test.log(Status.FAIL,"=====" + testName + " FAILURE=====");

	    try {
	        if(BaseClass.sdriver != null) {
	            TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
	            String temp = ts.getScreenshotAs(OutputType.BASE64);
	            test.addScreenCaptureFromBase64String(temp, testName + newDate);
	        } else {
	            test.log(Status.WARNING, "Driver was NULL. Screenshot not captured.");
	        }
	    } catch (Exception e) {
	        test.log(Status.WARNING, "Screenshot capture failed: " + e.getMessage());
	    }

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.SKIP,"====<" + result.getMethod().getMethodName() + ">==SKIPPED==");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

}
