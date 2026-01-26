package testcases_hardcoding;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCampaignTest {

	public static void main(String[] args) {

		// Launch Empty WebBrowser and maximize the browser window
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.get("http://49.249.28.218:8098/"); // Navigate to Ninza CRM application

		// Login with valid credentials (UN & PWD)
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[.='Sign In']")).click();

		// Click on Create Campaign button
		driver.findElement(By.xpath("//span[.='Create Campaign']")).click();
		
		// Generate the random number
		Random random = new Random();
		int randomInt = random.nextInt(1000);
		String campName = "PracticeTest" + randomInt;

		// Enter Campaign Name, Target Size
		driver.findElement(By.name("campaignName")).sendKeys(campName);
		WebElement targetSize = driver.findElement(By.name("targetSize"));
		targetSize.clear();
		targetSize.sendKeys("2");

		// Click on Create Campaign Button
		driver.findElement(By.xpath("//button[.='Create Campaign']")).click();

		// Capture the success message
		WebElement msg = driver.findElement(By.cssSelector(".Toastify__toast-body"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(msg));
		String successMsg = msg.getText();

		// Verify that campaign is created
		if (successMsg.contains(campName)) {
			System.out.println("Campaign Created");
		} else {
			System.out.println("Campaign Not Created");
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

