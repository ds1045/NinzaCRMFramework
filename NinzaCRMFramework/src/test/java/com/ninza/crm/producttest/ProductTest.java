package com.ninza.crm.producttest;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ninza.crm.baseTest.BaseClass;
import com.ninza.crm.objectrepositoryutility.HomePage;
import com.ninza.crm.objectrepositoryutility.ProductPage;

@Listeners(com.ninza.crm.listenerutility.ListenerImplementation.class)
public class ProductTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createProductWithMandatoryFieldTest() throws Throwable {

		// Read test script data from excel file
		String productName = eLib.getDataFromExcel("product", 1, 2)+jLib.getRandomNumber();
		String prodCategory = eLib.getDataFromExcel("product", 1, 3);
		String quant = eLib.getDataFromExcel("product", 1, 4);
		String priceValue = eLib.getDataFromExcel("product", 1, 5);
		
		// Create Campaign
		HomePage hp = new HomePage(driver);
		wLib.closeNavbarIfOpen(driver);
		wLib.jsClick(driver, hp.getProduct());
		ProductPage cp = new ProductPage(driver);
		cp.addProduct(productName, prodCategory, quant, priceValue);
	
		// Verify that product is created
		WebElement msg = hp.getSuccessmsg();
		wLib.waitForVisibilityOfElement(driver, msg);
		String successMsg = msg.getText();
		Assert.assertTrue(successMsg.contains(productName)); 
		hp.getClosemsg().click();
		
	}

}
