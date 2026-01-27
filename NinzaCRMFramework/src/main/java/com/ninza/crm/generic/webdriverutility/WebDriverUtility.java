package com.ninza.crm.generic.webdriverutility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	public void closeNavbarIfOpen(WebDriver driver) {
	    try {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript(
	            "let nav = document.getElementById('navbarNav'); if(nav && nav.classList.contains('show')) nav.classList.remove('show');"
	        );
	    } catch (Exception e) {
	        // ignore
	    }
	}

	public void jsClick(WebDriver driver, WebElement element) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", element);
	}

	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public void waitForVisibilityOfElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToBeClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitAndClick(WebDriver driver, WebElement element) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    wait.until(ExpectedConditions.elementToBeClickable(element));
	    
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView(true);", element);
	    
	    js.executeScript("arguments[0].click();", element);
	}
	
	public void select(WebElement element, int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}

	public void select(WebElement element, String value) {
		Select sel = new Select(element);
		sel.selectByValue(value);
	}

	public void select(String text, WebElement element) {
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}

	public void enterKeyPress() throws AWTException {
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_ENTER);
	}
	
	public void enterKeyRelease() throws AWTException {
		Robot rb = new Robot();
		rb.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public void downKeyPress() throws AWTException {
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_DOWN);
	}
	
	public void downKeyRelease() throws AWTException {
		Robot rb = new Robot();
		rb.keyRelease(KeyEvent.VK_DOWN);
	}
	
	public void upKeyPress() throws AWTException {
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_UP);
	}
	
	public void upKeyRelease() throws AWTException {
		Robot rb = new Robot();
		rb.keyRelease(KeyEvent.VK_UP);
	}
	
	public void mouseHoverOnWebElement(WebDriver driver, WebElement Element) {
		Actions act = new Actions(driver);
		act.moveToElement(Element).perform();
	}

	public void clickOnWebElement(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).click().perform();
	}

	public void doubleClickOnWebElement(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}

	public void rightClickOnWebElement(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}

	public void passInput(WebDriver driver, WebElement element, String text) {
		Actions act = new Actions(driver);
		act.click(element).sendKeys(text).perform();
	}

	public void dragAndDrop(WebDriver driver, WebElement src, WebElement target) {
		Actions act = new Actions(driver);
		act.dragAndDrop(src, target).perform();
	}
	
	public void enterKey(WebDriver driver) {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();
	}
	
	public void tabKey(WebDriver driver) {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).perform();
	}
		
	public void scrollAction(WebDriver driver, int x, int y) {
		Actions act = new Actions(driver);
		act.scrollByAmount(x, y).perform();
	}
	
	public void scrollAction(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.scrollToElement(element).perform();
	}
	
	public void shiftKey(WebDriver driver) {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.SHIFT).perform();
	}
	
	public void toScrollBy(WebDriver driver, int x, int y) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(" + x + "," + y + ")");
	}

	public void toScrollTo(WebDriver driver, int x, int y) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(" + x + "," + y + ")");
	}

	public void toScrollIntoView(WebDriver driver, WebElement element, boolean value) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView("+value+")", element);
	}
	
	public void actionOnHiddenElement(WebDriver driver, WebElement element, String msg) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].value='"+msg+"' ", element);
	}

	public void switchToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	public void switchToFrame(WebDriver driver, String nameorId) {
		driver.switchTo().frame(nameorId);
	}

	public void switchToFrame(WebDriver driver, WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}

	public void switchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void switchToAlertAndDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String switchToAlertAndgetText(WebDriver driver) {
		String text = driver.switchTo().alert().getText();
		return text;
	}

	public void switchToAlertAndsendKeys(WebDriver driver, String text) {
		driver.switchTo().alert().sendKeys(text);
	}
	
	public void switchToWindow(WebDriver driver) {

		Set<String> allWindId = driver.getWindowHandles();

		for (String id : allWindId) {

			driver.switchTo().window(id);
		}
	}

	public void switchToTabOnURL(WebDriver driver, String partialURL) {

		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();

		while (it.hasNext()) {
			String windowID = it.next();
			driver.switchTo().window(windowID);

			@Nullable
			String actUrl = driver.getCurrentUrl();
			if (actUrl.contains(partialURL)) {
				break;
			}

		}
	}

	public void switchToTabOnTitle(WebDriver driver, String partialTitle) {

		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();

		while (it.hasNext()) {
			String windowID = it.next();
			driver.switchTo().window(windowID);

			@Nullable
			String actUrl = driver.getCurrentUrl();
			if (actUrl.contains(partialTitle)) {
				break;
			}

		}
	}

	public void captureScreenshotOfWebPage(WebDriver driver, String fileName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File perm = new File("./Screenshots/" + fileName + ".png");
		FileHandler.copy(temp, perm);
	}
	
	public void captureScreenshotOfWebElement(WebDriver driver, WebElement element, String fileName) throws IOException {;
		File temp = element.getScreenshotAs(OutputType.FILE);
		File perm = new File("./Screenshots/" + fileName + ".png");
		FileHandler.copy(temp, perm);
	}

}
