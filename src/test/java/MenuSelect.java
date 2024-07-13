import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class MenuSelect {
   WebDriver driver;
   WebDriverWait wait;

    public MenuSelect(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public void selectMegaMenu() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/app-root/cx-storefront/app-custom-navigation/header/cx-page-layout/cx-page-slot/app-icld-megamenu/nav/ul/li[1]/span")).click();
    }

    public void selectItem(){
        WebElement menuItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/cx-storefront/app-custom-navigation/header/cx-page-layout/cx-page-slot/app-icld-megamenu/nav/ul/li[1]/div/div/ul[2]/li[1]/cx-generic-link/a")));
        menuItem.click();
    }

    public void selectSpotlight(){
        WebElement spotlightItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"in-the-spotlight\"]/div/div/app-custom-product-grid-item[1]/div/app-icld-card-item/div/a/div[1]")));
        spotlightItem.click();
    }
}


