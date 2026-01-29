package com.ninza.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ninza.crm.generic.webdriverutility.WebDriverUtility;

public class LoginPage {
	WebDriver driver;
	WebDriverUtility wLib = new WebDriverUtility();
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "username")
	private WebElement UN;
	
	@FindBy(id = "inputPassword")
	private WebElement PWD;
	
	@FindBy(css = "[type='submit']")
	private WebElement loginBtn;
	
	public WebElement getUN() {
		return UN;
	}
	
	public WebElement getPWD() {
		return PWD;
	}
	
	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	public void Login(String url, String username, String password) {
		wLib.maximizeWindow(driver);
		wLib.waitForPageToLoad(driver);
		driver.get(url);
		UN.sendKeys(username);
		PWD.sendKeys(password);
		loginBtn.click();
	}
	
}
