package com.ninza.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ninza.crm.generic.webdriverutility.JavaUtility;
import com.ninza.crm.generic.webdriverutility.WebDriverUtility;

public class CreateCampaignPage {
	WebDriver driver;
	HomePage hp = new HomePage(driver);
	JavaUtility jLib = new JavaUtility();
	WebDriverUtility wLib = new WebDriverUtility();

	public CreateCampaignPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "campaignName")
	private WebElement campaignname;
	
	@FindBy(name="campaignStatus")
	private WebElement campaignstatus;
	
	@FindBy(name = "targetSize")
	private WebElement TargetSizeTf;
	
	@FindBy(name="expectedCloseDate")
	private WebElement expectedClosedate;
	
	@FindBy(xpath = "//button[text()='Create Campaign']")
	private WebElement createCampaignSubmitBtn;
	
	public WebElement getCampaignname() {
		return campaignname;
	}
	
	public WebElement getCampaignstatus() {
		return campaignstatus;
	}
	
	public WebElement getTargetSizeTf() {
		return TargetSizeTf;
	}
	
	public WebElement getExpectedClosedate() {
		return expectedClosedate;
	}
	
	public WebElement getCreateCampaignSubmitBtn() {
		return createCampaignSubmitBtn;
	}
	
	public void createCampaign(String campName, String size) {
		campaignname.sendKeys(campName);
		TargetSizeTf.clear();
		wLib.passInput(driver, TargetSizeTf, size);
		wLib.waitAndClick(driver, createCampaignSubmitBtn);
	}
	
	public void createCampaignWithStatus(String campName, String size, String status) {
		campaignname.sendKeys(campName);
		campaignstatus.sendKeys(status);
		TargetSizeTf.clear();
		wLib.passInput(driver, TargetSizeTf, size);
		wLib.waitAndClick(driver, createCampaignSubmitBtn);
	}
	
	public void createCampaignWithExpectedCloseDate(String campName, String size) {
		campaignname.sendKeys(campName);
		expectedClosedate.sendKeys(jLib.getRequiredDate(30));
		TargetSizeTf.clear();
		wLib.passInput(driver, TargetSizeTf, size);
		wLib.waitAndClick(driver, createCampaignSubmitBtn);
	}
	
}
