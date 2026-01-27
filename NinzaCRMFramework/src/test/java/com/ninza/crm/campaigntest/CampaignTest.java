package com.ninza.crm.campaigntest;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ninza.crm.baseTest.BaseClass;
import com.ninza.crm.objectrepositoryutility.CreateCampaignPage;
import com.ninza.crm.objectrepositoryutility.HomePage;

@Listeners(com.ninza.crm.listenerutility.ListenerImplementation.class)
public class CampaignTest extends BaseClass {

	@Test(priority = 1, groups = {"smokeTest", "regressionTest"})
	public void createCampaignTest() throws Throwable {
		
		// Read test script data from excel file
		String camp = eLib.getDataFromExcel("campaign", 1, 2)+jLib.getRandomString(5);
		String size = eLib.getDataFromExcel("campaign", 1, 3);		

		// Create Campaign
		HomePage hp = new HomePage(driver);
		wLib.waitAndClick(driver, hp.getCreateCampaignBtn());
		CreateCampaignPage cp = new CreateCampaignPage(driver);
		cp.createCampaign(camp, size);
	
		// Verify that campaign is created
		WebElement msg = hp.getSuccessmsg();
		wLib.waitForVisibilityOfElement(driver, msg);
		String successMsg = msg.getText();
		Assert.assertTrue(successMsg.contains(camp)); 
		hp.getClosemsg().click();
		
	}
	
	@Test(priority = 2, groups = "regressionTest")
	public void createCampaignWithStatusTest() throws Throwable {
		
		// Read test script data from excel file
		String camp = eLib.getDataFromExcel("campaign", 4, 2)+jLib.getRandomString(5);
		String size = eLib.getDataFromExcel("campaign", 4, 3);
		String status = eLib.getDataFromExcel("campaign", 4, 4);
		
		// Create Campaign
		HomePage hp = new HomePage(driver);
		wLib.waitAndClick(driver, hp.getCreateCampaignBtn());
		CreateCampaignPage cp = new CreateCampaignPage(driver);
		cp.createCampaignWithStatus(camp, size, status);
	
		// Verify that campaign is created
		WebElement msg = hp.getSuccessmsg();
		wLib.waitForVisibilityOfElement(driver, msg);
		String successMsg = msg.getText();
		Assert.assertTrue(successMsg.contains(camp)); 
		hp.getClosemsg().click();
		
	}
	
	@Test(priority = 3, groups = "regressionTest")
	public void createCampaignWithExpectedCloseDateTest() throws Throwable {
		
		// Read test script data from excel file
		String camp = eLib.getDataFromExcel("campaign", 7, 2)+jLib.getRandomString(5);
		String size = eLib.getDataFromExcel("campaign", 7, 3);
		
		// Create Campaign
		HomePage hp = new HomePage(driver);
		wLib.waitAndClick(driver, hp.getCreateCampaignBtn());
		CreateCampaignPage cp = new CreateCampaignPage(driver);
		cp.createCampaignWithExpectedCloseDate(camp, size);
	
		// Verify that campaign is created
		WebElement msg = hp.getSuccessmsg();
		wLib.waitForVisibilityOfElement(driver, msg);
		String successMsg = msg.getText();
		Assert.assertTrue(successMsg.contains(camp)); 
		hp.getClosemsg().click();
		
	}
	
}
