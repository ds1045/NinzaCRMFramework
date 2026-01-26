package com.ninza.crm.producttest;

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
import com.ninza.crm.objectrepositoryutility.HomePage;
import com.ninza.crm.objectrepositoryutility.LoginPage;
import com.ninza.crm.objectrepositoryutility.ProductPage;

public class CreateProductWithMandatoryFieldTest {

	@Test
	public void createProductWithMandatoryFieldTest() throws Throwable {
		// TODO Auto-generated method stub

		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility  jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();

		// Read common data from properties file
		String browser = fLib.getDataFromPropertiesFile("browser");
		String url = fLib.getDataFromPropertiesFile("url");
		String username = fLib.getDataFromPropertiesFile("username");
		String password = fLib.getDataFromPropertiesFile("password");

		// Read test script data from excel file
		String productName = eLib.getDataFromExcel("product", 1, 2)+jLib.getRandomNumber();
		String prodCategory = eLib.getDataFromExcel("product", 1, 3);
		String quant = eLib.getDataFromExcel("product", 1, 4);
		String priceValue = eLib.getDataFromExcel("product", 1, 5);
		
		WebDriver driver;
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new EdgeDriver();
		}

		// Login to Ninza CRM application
		LoginPage lp = new LoginPage(driver);
		lp.Login(url, username, password);

		// Create Campaign
		HomePage hp = new HomePage(driver);
		hp.getProduct().click();
		ProductPage cp = new ProductPage(driver);
		cp.addProduct(productName, prodCategory, quant, priceValue);
	
		// Verify that product is created
		WebElement msg = hp.getSuccessmsg();
		wLib.waitForVisibilityOfElement(driver, msg);
		String successMsg = msg.getText();
		if (successMsg.contains(productName)) {
			System.out.println("Product Added Successfully");
		} else {
			System.out.println("Product Not Added");
		}
		hp.getClosemsg().click();
		
		// Logout of the application
		hp.Logout();

	}

}
