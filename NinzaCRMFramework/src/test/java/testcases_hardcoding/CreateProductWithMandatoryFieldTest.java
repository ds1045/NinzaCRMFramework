package testcases_hardcoding;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateProductWithMandatoryFieldTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Launch Empty WebBrowser and maximize the browser window
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.get("http://49.249.28.218:8098/"); // Navigate to Ninza CRM application

		// Login with valid credentials (UN & PWD)
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[.='Sign In']")).click();

		// Click on Products Link
		driver.findElement(By.linkText("Products")).click();

		// Click on Add Product button
		driver.findElement(By.xpath("//span[.='Add Product']")).click();

		// Generate the random number
		Random random = new Random();
		int randomInt = random.nextInt(1000);
		String prodName = "LaptopLenovo" + randomInt;

		// Enter Product Name, Select Product Category, Enter Quantity and Price, Select Vendor
		driver.findElement(By.name("productName")).sendKeys(prodName);
		WebElement productCategory = driver.findElement(By.name("productCategory"));
		Select obj1 = new Select(productCategory);
		obj1.selectByValue("Electronics");
		WebElement quantity = driver.findElement(By.name("quantity"));
		quantity.clear();
		quantity.sendKeys("5");
		WebElement price = driver.findElement(By.name("price"));
		price.clear();
		price.sendKeys("10");
		WebElement vendorId = driver.findElement(By.name("vendorId"));
		Select obj2 = new Select(vendorId);
		obj2.selectByValue("VID_1571");

		// Click on Add button
		driver.findElement(By.xpath("//button[.='Add']")).click();

		// Capture the success message
		WebElement msg = driver.findElement(By.cssSelector(".Toastify__toast-body"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(msg));
		String successMsg = msg.getText();

		// Verify that product is added
		if (successMsg.contains(prodName)) {
			System.out.println("Product "+prodName+" Created");
		} else {
			System.out.println("Product Not Created");
		}

		// Logout of the application
		WebElement profileIcon = driver.findElement(By.cssSelector("[class='user-icon']"));
		Actions action = new Actions(driver);
		action.moveToElement(profileIcon).click().perform();
		WebElement logout = driver.findElement(By.cssSelector("[class='dropdown-item logout']"));
		action.click(logout).perform();

		driver.quit();
	}

}
