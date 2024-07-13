import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SwagLogin {

    WebDriver driver;
    WebDriverWait wait;

    private static final Logger logger = LogManager.getLogger(SwagLogin.class);

    public SwagLogin(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    //Standard Login
    public void swagLogin() {
        try {
            driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
            driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
            driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
            logger.info("Login successful");
        } catch (Exception e) {
            logger.error("Failed regular user login");
        }
    }

    //Blocked user login
    public void swagBlockedLogin() {
        try {
            driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("locked_out_user");
            driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
            driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
            WebElement element = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]"));
            String actual = element.getText();
            String expected = "Epic sadface: Sorry, this user has been locked out.";
            Assert.assertEquals(expected, actual);
            logger.info("Login blocked");
        } catch (Exception e) {
            logger.error("Blocked user login successful", e);
        }

    }

    //Problem user login
    public void swagProblemLogin() {
        try {

            driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("problem_user");
            driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
            driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
            logger.info("Problem login successful");

        } catch (Exception e) {
            logger.error("Problem user failed login", e);
        }
    }

    //Performance issue user login
    public void swagPerformanceLogin() {
        try {
            driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("performance_glitch_user");
            driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
            driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
            logger.info("Performance login successful");
        } catch (Exception e) {
            logger.error("Performance issue user login failed", e);
        }
    }

    //Error user login
    public void swagErrorLogin() {
        try {
            driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("error_user");
            driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
            driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
            logger.info("Error login successful");

        } catch (Exception e) {
        logger.error("Error user login failed", e);
        }

    }
}