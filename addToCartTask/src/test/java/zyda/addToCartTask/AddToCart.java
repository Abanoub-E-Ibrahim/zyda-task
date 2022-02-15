package zyda.addToCartTask;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.Test; 
import org.openqa.selenium.JavascriptExecutor;

public class AddToCart {

		WebDriver driver;
		WebDriverWait wait;

		@BeforeTest
		public void openurl() {
			String chromePath = System.getProperty("user.dir")+"\\Resources\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver",chromePath);
			driver = new ChromeDriver();
			System.out.println("Open URL");
			driver.navigate().to("http://automationpractice.com/index.php");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}


		@Test(priority = 1, enabled =true)
		public void case0_addToCart() throws InterruptedException {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Actions action = new Actions(driver);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,800)", "");	
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//Add first element 
			WebElement target_element = driver.findElement(By.xpath("//ul[@id='homefeatured']//li[@class='ajax_block_product col-xs-12 col-sm-4 col-md-3 last-item-of-mobile-line']"));
			js.executeScript("arguments[0].scrollIntoView();", target_element);
			action.moveToElement(target_element).perform();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.linkText("Add to cart")).click();
			driver.findElement(By.xpath("//span[@title='Continue shopping']//span[1]")).click();
			//Add second element
			WebElement target_element2 = driver.findElement(By.xpath("//ul[@id='homefeatured']//li[@class='ajax_block_product col-xs-12 col-sm-4 col-md-3 last-in-line first-item-of-tablet-line last-item-of-mobile-line']"));
			js.executeScript("arguments[0].scrollIntoView();", target_element2);
			action.moveToElement(target_element2).perform();
			Thread.sleep(1500);
			driver.findElement(By.linkText("Add to cart")).click();
			driver.findElement(By.xpath("//a[@title='Proceed to checkout']")).click();
			js.executeScript("window.scrollBy(0,1000)", "");
			driver.findElement(By.xpath("//p/a[@title='Proceed to checkout']")).click();
			//Sign in step
			driver.findElement(By.id("email")).sendKeys("abanoub.emil50@gmail.com");
			driver.findElement(By.id("passwd")).sendKeys("12345678");
			driver.findElement(By.id("SubmitLogin")).click();
			js.executeScript("window.scrollBy(0,1000)", "");
			driver.findElement(By.name("processAddress")).click();
			js.executeScript("window.scrollBy(0,1000)", "");
			driver.findElement(By.name("cgv")).click();
			driver.findElement(By.name("processCarrier")).click();
			//Payment
			js.executeScript("window.scrollBy(0,800)", "");
			driver.findElement(By.className("bankwire")).click();
			//Confirm order
			js.executeScript("window.scrollBy(0,800)", "");
			driver.findElement(By.xpath("//button[@class=\"button btn btn-default button-medium\"]")).click();
			js.executeScript("window.scrollBy(0,500)", "");
			Assert.assertTrue(driver.findElement(By.xpath("//p[@class='cheque-indent']")).isDisplayed());
		}

	}
