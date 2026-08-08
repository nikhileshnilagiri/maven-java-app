package com.test.stm;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
public class Exp3 {
	public static void main(String args[]) throws InterruptedException{
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com");
		
		WebElement searchbox = driver.findElement(By.name("q"));
		System.out.println("Displayed: "+ searchbox.isDisplayed());
		System.out.println("Enabled: "+searchbox.isEnabled());
		
		System.out.println(searchbox.isDisplayed() && searchbox.isEnabled() ? "PASS: Search box is ready" : "FAIL: Search box is not ready");
		Thread.sleep(2000);
		driver.quit();
	}
}