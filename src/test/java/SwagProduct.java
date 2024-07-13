import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SwagProduct {
    WebDriver driver;
    WebDriverWait wait;

    private static final Logger logger = LogManager.getLogger(SwagProduct.class);

    public SwagProduct(WebDriver driver, WebDriverWait wait){
        this.driver=driver;
        this.wait=wait;
    }

    public void selectProduct(){
        try{
        driver.findElement(By.xpath("//*[@id=\"item_1_title_link\"]/div")).click();
        driver.findElement(By.xpath("//*[@id=\"add-to-cart\"]")).click();
        logger.info("Product selected");
    } catch (Exception e){
            logger.error("Product could not be selected", e);
        }
    }

    public void returnSelectProduct(){
        try{
        driver.findElement(By.id("back-to-products")).click();
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-onesie\"]")).click();
        logger.info("Returned back to all products");
    }catch (Exception e){
            logger.error("Could not return to product selection", e);
        }
    }

    public void fastSelectProduct() {
        try {
            driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();
            logger.info("Product selected from the main menu");
        } catch (Exception e){
            logger.error("Could not select product from main menu", e);
        }
    }
    public void assertProduct(){
        try{
        WebElement element = driver.findElement(By.xpath("//*[@id=\"remove\"]"));
        String actual = element.getText();
        String expected = "Remove";
        Assert.assertEquals(expected,actual);
        logger.info("Assertion successful");
    } catch (Exception e){
            logger.error("Assertion failed",e);
        }
    }

    public void productImage() {
        try {
            WebElement image = driver.findElement(By.xpath("//*[@id=\"item_0_img_link\"]/img"));
            String actualImage = image.getAttribute("src");
            String expectedImage = "https://www.saucedemo.com/static/media/sl-404.168b1cce.jpg";
            Assert.assertEquals(actualImage, expectedImage);
            logger.info("Image asserted successful");
        } catch (Exception e){
            logger.error("Image assertion failed", e);
        }
    }
    public void performanceProductSelect() {
        try {
            WebElement loading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")));
            loading.click();
            logger.info("Performance issue user product selected");
        }catch (Exception e){
            logger.error("Failed to get product for performance issue user", e);
        }
    }
}
