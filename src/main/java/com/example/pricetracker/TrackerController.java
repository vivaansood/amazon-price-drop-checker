package com.example.pricetracker;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/track")
public class TrackerController {

    private final List<Product> products = new ArrayList<>();

    @GetMapping("/add-product")
    public Product addProduct(@RequestParam String url, @RequestParam double targetPrice) {
        System.out.println("Starting addProduct with URL: " + url);

        System.setProperty("webdriver.chrome.driver", "/Users/vivaansood/Downloads/chromedriver-mac-arm64/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String title = fetchProductTitle(driver, url);
        double currentPrice = fetchCurrentPrice(driver, url);
        driver.quit();

        Product product = new Product(url, title, currentPrice, targetPrice);
        products.add(product);
        return product;
    }

    @GetMapping("/check-prices")
    public List<Product> checkPrices() {
        return products;
    }

    private String fetchProductTitle(WebDriver driver, String url) {
        driver.get(url);
        try {
            return driver.findElement(By.id("productTitle")).getText();
        } catch (NoSuchElementException e) {
            System.err.println("Error fetching product title: Element not found");
            return "N/A";
        }
    }

    private double fetchCurrentPrice(WebDriver driver, String url) {
        driver.get(url);

        try {
            // Wait for the page to load completely
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(d -> ((JavascriptExecutor) d)
                            .executeScript("return document.readyState").equals("complete"));

            // Try multiple selectors to find the price
            WebElement priceElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(d -> {
                        try {
                            return d.findElement(By.cssSelector(".a-price .a-offscreen"));
                        } catch (NoSuchElementException e1) {
                            try {
                                return d.findElement(By.id("priceblock_ourprice"));
                            } catch (NoSuchElementException e2) {
                                return d.findElement(By.id("priceblock_dealprice"));
                            }
                        }
                    });

            // Extract and clean the price text
            String priceText = priceElement.getText().replaceAll("[^\\d.]", "");
            System.out.println("Fetched Price Text: " + priceText);

            if (!priceText.isEmpty()) {
                return Double.parseDouble(priceText);
            } else {
                System.err.println("Error parsing price: Price text is empty");
            }
        } catch (TimeoutException | NoSuchElementException e) {
            System.err.println("Error fetching product price: Element not found");
        } catch (NumberFormatException e) {
            System.err.println("Error parsing price: " + e.getMessage());
        }
        return 0.0;
    }
}
