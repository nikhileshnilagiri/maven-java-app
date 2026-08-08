package com.test.stm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Exp2 {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");

        String expectedTitle = "Google";
        String actualTitle = driver.getTitle();

        System.out.println("Actual Title   : " + actualTitle);
        System.out.println("Expected Title : " + expectedTitle);

        if (actualTitle.equals(expectedTitle)) {
            System.out.println("PASS: Title is correct");
        } else {
            System.out.println("FAIL: Title is incorrect");
        }

        Thread.sleep(2000);
        driver.quit();
    }
}
