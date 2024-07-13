import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class dropDown {
    WebDriver driver;
    WebDriverWait wait;

    private static final Logger logger = LogManager.getLogger(dropDown.class);

    public dropDown(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void aToz() {
        try {
            driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select")).click();
            driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select/option[1]")).click();

            WebElement firstProduct = driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div"));
            String actual = firstProduct.getText();
            String expected = "Sauce Labs Backpack";
            Assert.assertEquals(actual, expected);
            logger.info("aToz dropdown filter is functional");
        } catch (Exception e) {
            logger.error("dropdown filter encountered an error", e);
        }
    }

    public void zToa() {
        try {
            driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select")).click();
            driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select/option[2]")).click();

            WebElement firstProduct = driver.findElement(By.xpath("//*[@id=\"item_3_title_link\"]/div"));
            String actual = firstProduct.getText();
            String expected = "Test.allTheThings() T-Shirt (Red)";
            Assert.assertEquals(actual, expected);
            logger.info("zToa dropdown filter is functional");
        } catch (Exception e){
            logger.error("dropdown filter encountered an error", e);
        }
    }
}