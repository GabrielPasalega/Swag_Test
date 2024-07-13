import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SwagCart {

    WebDriver driver;
    WebDriverWait wait;

    private static final Logger logger = LogManager.getLogger(SwagCart.class);

    public SwagCart(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void swagBuy() {
        try {
            driver.findElement(By.className("shopping_cart_link")).click();
            driver.findElement(By.className("btn_action")).click();
            logger.info("Product added to cart");
        } catch (Exception e) {
            logger.error("Error occurred while trying to add product to cart", e);
        }
    }

    public void checkout() {
        try {
            driver.findElement(By.id("first-name")).sendKeys("Gabriel");
            driver.findElement(By.id("last-name")).sendKeys("Pasalega");
            driver.findElement(By.id("postal-code")).sendKeys("010172");
            driver.findElement(By.id("continue")).click();
            driver.findElement(By.id("finish")).click();
            logger.info("Checkout completed");
        } catch (Exception e) {
            logger.error("Checkout error occurred", e);
        }
    }

    public void problemCheckout() {
        try {
            driver.findElement(By.id("first-name")).sendKeys("Gabriel");
            driver.findElement(By.id("last-name")).sendKeys("Pasalega");
            driver.findElement(By.id("postal-code")).sendKeys("010172");
            driver.findElement(By.id("continue")).click();

            WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]"));
            String actual = errorMessage.getText();
            String receivedMessage = "Error: Last Name is required";
            Assert.assertEquals(actual, receivedMessage);
            logger.info("Checkout error for problem client");
        } catch (Exception e) {
            logger.error("Problem user checkout did not received an error", e);
        }
    }

    private void captureConsoleLogs(WebDriver driver) {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        boolean foundUnauthorizedError = false;

        for (LogEntry entry : logEntries) {
            String message = entry.getMessage();
            //String level = entry.getLevel().toString();

            // Check for specific 401 Unauthorized error message in console logs
            if (message.contains("401")) {
                foundUnauthorizedError = true;
                break; // Exit loop once the error is found
            }
        }

        Assert.assertTrue(foundUnauthorizedError, "401 Unauthorized error message not found in console logs");
    }

    public void errorCheckout(WebDriver driver) throws InterruptedException {
        try {
            driver.findElement(By.id("first-name")).sendKeys("Gabriel");
            driver.findElement(By.id("last-name")).sendKeys("Pasalega");
            driver.findElement(By.id("postal-code")).sendKeys("010172");

            WebElement lastName = driver.findElement(By.id("last-name"));
            String writtenName = lastName.getAttribute("value").trim();
            String actualName = "";
            Assert.assertEquals(writtenName, actualName);

            driver.findElement(By.id("continue")).click();
            driver.findElement(By.id("finish")).click();
            Thread.sleep(1500);
            captureConsoleLogs(driver);
            logger.info("Error 401 found in DOM");
        } catch (Exception e) {
            logger.error("Error user could not finish checkout OR DOM error not found", e);
        }
    }
}


