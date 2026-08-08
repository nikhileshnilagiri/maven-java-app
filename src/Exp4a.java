package com.test.stm;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Exp4a {

    public static void main(String args[]) throws Exception {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.google.com");

            WebElement searchbox = driver.findElement(By.name("q"));

            File folder = new File("Screenshots");

            if (!folder.exists()) {
                folder.mkdir();
            }

            File current = searchbox.getScreenshotAs(OutputType.FILE);

            File currentFile = new File(
                    "Screenshots/current_searchbox.png"
            );

            FileHandler.copy(current, currentFile);

            System.out.println("Current Screenshot Saved");
            System.out.println(currentFile.getAbsolutePath());

            File baselineFile = new File(
                    "Screenshots/baseline_searchbox.png"
            );

            if (!baselineFile.exists()) {

                FileHandler.copy(currentFile, baselineFile);

                System.out.println("Baseline Image not found.");
                System.out.println("Baseline image created.");

            } else {

                boolean result = compare(baselineFile, currentFile);

                if (result) {
                    System.out.println(
                            "BITMAP CHECKPOINT : TEST PASS"
                    );
                    System.out.println("Images are identical.");

                } else {
                    System.out.println(
                            "BITMAP CHECKPOINT : TEST FAIL"
                    );
                    System.out.println("Images are different.");
                }
            }

        } catch (Exception e) {

            System.out.println("Something went wrong.");
            e.printStackTrace();

        } finally {

            driver.quit();
        }
    }

    public static boolean compare(File img1, File img2)
            throws Exception {

        BufferedImage image1 = ImageIO.read(img1);
        BufferedImage image2 = ImageIO.read(img2);

        for (int x = 0; x < image1.getWidth(); x++) {

            for (int y = 0; y < image1.getHeight(); y++) {

                if (image1.getRGB(x, y)
                        != image2.getRGB(x, y)) {

                    return false;
                }
            }
        }

        return true;
    }
}