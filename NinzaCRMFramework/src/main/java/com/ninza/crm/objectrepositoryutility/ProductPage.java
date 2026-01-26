package com.ninza.crm.objectrepositoryutility;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ninza.crm.generic.webdriverutility.WebDriverUtility;

public class ProductPage {
	WebDriver driver;
	WebDriverUtility wLib =  new WebDriverUtility();
	
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[text()='Add Product']")
	private WebElement Addproductbutton;
	
	public WebElement getAddproductbutton() {
		return Addproductbutton;
	}
	
	@FindBy(name = "productName")
	private WebElement productName;
	
	@FindBy(name = "productCategory")
	private WebElement productcategory;
	
	@FindBy(name = "quantity")
	private WebElement quantity;
	
	@FindBy(name = "price")
	private WebElement price;
	
	@FindBy(name = "vendorId")
	private WebElement vendorid;
	
	@FindBy(xpath = "//button[text()='Add']")
	private WebElement AddproductBtn;
	
	public WebElement getProductName() {
		return productName;
	}
	
	public WebElement getProductcategory() {
		return productcategory;
	}
	
	public WebElement getQuantity() {
		return quantity;
	}
	
	public WebElement getPrice() {
		return price;
	}
	
	public WebElement getVendorid() {
		return vendorid;
	}
	
	public WebElement getAddproduct() {
		return AddproductBtn;
	}
		
	public void addProduct(String PRODUCTNAME, String PRODUCTCATEGORY, String QUANTITY, String PRICE) {
		Addproductbutton.click();
		productName.sendKeys(PRODUCTNAME);
		wLib.select(productcategory, PRODUCTCATEGORY);
		quantity.clear();
		quantity.sendKeys(QUANTITY);
		price.clear();
		price.sendKeys(PRICE);
		vendorid.click();
		vendorid.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		AddproductBtn.click();
	}
}
