package com.test.stm;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Exp4b {

    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("https://www.google.com");
            Thread.sleep(3000);

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage fullImage = ImageIO.read(screenshot);

            File folder = new File("SeleniumScreenshots");
            if (!folder.exists()) folder.mkdirs();

            int width = Math.min(800, fullImage.getWidth());
            int height = Math.min(600, fullImage.getHeight());
            BufferedImage currentArea = fullImage.getSubimage(0, 0, width, height);

            File currentFile = new File("SeleniumScreenshots/current_google_area.png");
            ImageIO.write(currentArea, "png", currentFile);

            System.out.println("Current screenshot saved: " + currentFile.getAbsolutePath());

            File baselineFile = new File("SeleniumScreenshots/baseline_google_area.png");

            if (!baselineFile.exists()) {
                ImageIO.write(currentArea, "png", baselineFile);
                System.out.println("Baseline image created.");
            } else {
                BufferedImage baseline = ImageIO.read(baselineFile);
                double similarity = compareImages(baseline, currentArea);

                System.out.println("Image Similarity : " + similarity + "%");

                if (similarity >= 95)
                    System.out.println("BITMAP CHECKPOINT : TEST PASS");
                else
                    System.out.println("BITMAP CHECKPOINT : TEST FAIL");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    public static double compareImages(BufferedImage img1, BufferedImage img2) {
        if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight())
            return 0;

        long totalPixels = (long) img1.getWidth() * img1.getHeight();
        long samePixels = 0;

        for (int x = 0; x < img1.getWidth(); x++) {
            for (int y = 0; y < img1.getHeight(); y++) {
                if (img1.getRGB(x, y) == img2.getRGB(x, y))
                    samePixels++;
            }
        }

        return ((double) samePixels / totalPixels) * 100;
    }
}