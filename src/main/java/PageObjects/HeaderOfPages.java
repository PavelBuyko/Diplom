package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class HeaderOfPages {
    protected final By LOGO = By.xpath("//*[@id=\"root\"]/div/header/nav/div/a");
    protected final By CONSTRUCTOR = By.xpath("//*[@id=\"root\"]/div/header/nav/ul/li[1]/a/p");
    protected final By PERSONALACCOUNTBUTTON = By.xpath("//*[@id='root']/div/header/nav/a/p");


    public void clickLogo(WebDriver driver)
    {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(LOGO));
        driver.findElement(LOGO).click();
    }
    public void clickConstructor(WebDriver driver)
    {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(CONSTRUCTOR));
        driver.findElement(CONSTRUCTOR).click();
    }
    public void clickAccount(WebDriver driver)
    {
        new WebDriverWait(driver, Duration.ofSeconds(3))
            .until(ExpectedConditions.elementToBeClickable(PERSONALACCOUNTBUTTON));
        driver.findElement(PERSONALACCOUNTBUTTON).click();
    }

}
