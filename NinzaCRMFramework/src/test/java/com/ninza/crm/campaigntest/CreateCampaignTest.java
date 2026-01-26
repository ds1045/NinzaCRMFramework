package com.ninza.crm.campaigntest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.ninza.crm.generic.fileutility.ExcelUtility;
import com.ninza.crm.generic.fileutility.FileUtility;
import com.ninza.crm.generic.webdriverutility.JavaUtility;
import com.ninza.crm.generic.webdriverutility.WebDriverUtility;
import com.ninza.crm.objectrepositoryutility.CreateCampaignPage;
import com.ninza.crm.objectrepositoryutility.HomePage;
import com.ninza.crm.objectrepositoryutility.LoginPage;

public class CreateCampaignTest {

	@Test
	public void createCampaignTest() throws Throwable {
		// TODO Auto-generated method stub

		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		// Read common data from properties file
		String browser = fLib.getDataFromPropertiesFile("browser");
		String url = fLib.getDataFromPropertiesFile("url");     
		String username = fLib.getDataFromPropertiesFile("username");
		String password = fLib.getDataFromPropertiesFile("password");
		
		// Read test script data from excel file
		String camp = eLib.getDataFromExcel("campaign", 1, 2)+jLib.getRandomString(5);
		String size = eLib.getDataFromExcel("campaign", 1, 3);
		
		WebDriver driver;
		if(browser.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		}
		else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		else {
			driver = new EdgeDriver();
		}
			
		// Login to Ninza CRM application
		LoginPage lp = new LoginPage(driver);
		lp.Login(url, username, password);

		// Create Campaign
		HomePage hp = new HomePage(driver);
		hp.getCreateCampaignBtn().click();
		CreateCampaignPage cp = new CreateCampaignPage(driver);
		cp.createCampaign(camp, size);
	
		// Verify that campaign is created
		WebElement msg = hp.getSuccessmsg();
		wLib.waitForVisibilityOfElement(driver, msg);
		String successMsg = msg.getText();
		if (successMsg.contains(camp)) {
			System.out.println("Campaign Created Successfully");
		} else {
			System.out.println("Campaign Not Created");
		}
		hp.getClosemsg().click();

		// Logout of the application
		hp.Logout();

	}

}
