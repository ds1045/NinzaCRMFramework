package com.ninza.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ninza.crm.generic.webdriverutility.WebDriverUtility;

public class HomePage {
	WebDriver driver;
	WebDriverUtility wLib = new WebDriverUtility();
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Campaigns")
	private WebElement campaign;
	
	@FindBy(linkText = "Products")
	private WebElement product;
	
	@FindBy(xpath = "//span[text()='Create Campaign']")
	private WebElement createCampaignBtn;
	
	@FindBy(className = "user-icon")
	private WebElement userIcon;
	
	@FindBy(css = ".logout")
	private WebElement logoutBtn;
	
	@FindBy(css = "[role='alert']")
	private WebElement successMsg;
	
	@FindBy(xpath = "//button[@aria-label='close']")
	private WebElement closemsg;

	public WebElement getCampaign() {
		return campaign;
	}

	public WebElement getProduct() {
		return product;
	}

	public WebElement getCreateCampaignBtn() {
		return createCampaignBtn;
	}

	public WebElement getUserIcon() {
		return userIcon;
	}

	public WebElement getLogout() {
		return logoutBtn;
	}

	public WebElement getSuccessmsg() {
		return successMsg;
	}

	public WebElement getClosemsg() {
		return closemsg;
	}
	
	public void Logout() {
		wLib.clickOnWebElement(driver, userIcon);
		wLib.clickOnWebElement(driver, logoutBtn);
	}
}
