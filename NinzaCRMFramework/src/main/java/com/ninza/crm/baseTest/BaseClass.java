package com.ninza.crm.baseTest;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.ninza.crm.generic.databaseutility.DataBaseUtility;
import com.ninza.crm.generic.fileutility.ExcelUtility;
import com.ninza.crm.generic.fileutility.FileUtility;
import com.ninza.crm.generic.webdriverutility.JavaUtility;
import com.ninza.crm.generic.webdriverutility.WebDriverUtility;
import com.ninza.crm.objectrepositoryutility.HomePage;
import com.ninza.crm.objectrepositoryutility.LoginPage;

public class BaseClass {
	
	public WebDriver driver = null;
	public static WebDriver sdriver = null;
	public DataBaseUtility dbLib = new DataBaseUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public FileUtility fLib = new FileUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	
	@BeforeSuite(groups = {"smokeTest", "regressionTest"})
	public void configBS() throws SQLException {
		Reporter.log("==Connect to DB, Report Config==", true);
		dbLib.getDbConnection();
	}
	
	@BeforeTest(groups = {"smokeTest", "regressionTest"})
	public void configBT() {
		Reporter.log("==Pre Conditions==", true);
	}
	
	// @Parameters("BROWSER")
	@BeforeClass(groups = {"smokeTest", "regressionTest"})
//	public void configBC(String browser) throws Throwable {
	public void configBC() throws Throwable {
		Reporter.log("==Launch the Browser==", true);
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		if(BROWSER.equals("chrome")) {
			ChromeOptions settings = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.password_manager_leak_detection", false);
			settings.setExperimentalOption("prefs", prefs);
	        driver = new ChromeDriver(settings);
	    } else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			ChromeOptions settings = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.password_manager_leak_detection", false);
			settings.setExperimentalOption("prefs", prefs);
	        driver = new ChromeDriver(settings);
	    }
		sdriver = driver;
	}
	
	@BeforeMethod(groups = {"smokeTest", "regressionTest"})
	public void configBM() throws Throwable {
		Reporter.log("==Login to the application==", true);
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.Login(URL, USERNAME, PASSWORD);
	}
	
	@AfterMethod(groups = {"smokeTest", "regressionTest"})
	public void configAM() {
		Reporter.log("==Logout of the application==", true);
		HomePage hp = new HomePage(driver);
		hp.Logout();
	}
	
	@AfterClass(groups = {"smokeTest", "regressionTest"})
	public void configAC() {
		Reporter.log("==Close the Browser==", true);
		driver.quit(); 
	}
	
	@AfterTest(groups = {"smokeTest", "regressionTest"})
	public void configAT() {
		Reporter.log("==Post Conditions==", true);
	}
	
	@AfterSuite(groups = {"smokeTest", "regressionTest"})
	public void configAS() throws SQLException {
		Reporter.log("==Close the DB, Report Backup==", true);
		dbLib.closeDbConnection();
	}
	
}
