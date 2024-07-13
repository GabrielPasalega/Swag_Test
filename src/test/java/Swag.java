import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class Swag {
    private WebDriver driver;
    private WebDriverWait wait;
    private SwagLogin loginInstance;
    private SwagProduct productInstance;
    private SwagCart cartInstance;
    private dropDown dropInstance;

    @BeforeMethod
    void Setup(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/chromedriver.exe");

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();


        loginInstance = new SwagLogin(driver,wait);
        productInstance = new SwagProduct(driver,wait);
        cartInstance = new SwagCart(driver,wait);
        dropInstance = new dropDown(driver,wait);
    }

//    @AfterMethod
//    void teardown(){
//        if (driver != null){
//            driver.quit();
//        }
//
//    }
    @Test
    void normalFlow(){

        loginInstance.swagLogin();

        productInstance.selectProduct();
        productInstance.assertProduct();

        cartInstance.swagBuy();
        cartInstance.checkout();

    }

    @Test
    void blockedFlow(){
        loginInstance.swagBlockedLogin();
    }
    @Test
    void problemFlow(){
        loginInstance.swagProblemLogin();
        
        productInstance.productImage();
        productInstance.selectProduct();

        driver.findElement(By.id("remove")).click();
        productInstance.assertProduct();

        cartInstance.swagBuy();
        cartInstance.problemCheckout();
    }

    @Test
    void performanceFlow(){
        loginInstance.swagPerformanceLogin();

        productInstance.performanceProductSelect();

        cartInstance.swagBuy();
        cartInstance.checkout();
    }

    @Test
    void errorFlow() throws InterruptedException {
        loginInstance.swagErrorLogin();

        productInstance.fastSelectProduct();

        cartInstance.swagBuy();
        //Thread.sleep(2000);
        cartInstance.errorCheckout(driver);
    }
    @Test
    void filterTest(){
        loginInstance.swagLogin();

        dropInstance.zToa();
        dropInstance.aToz();

    }
    @Test
    void multipleProducts(){

        loginInstance.swagLogin();

        productInstance.selectProduct();
        productInstance.assertProduct();
        productInstance.returnSelectProduct();

        cartInstance.swagBuy();
        cartInstance.checkout();

    }


}
