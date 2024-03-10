package PageObjects;

import Resources.URLs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends HeaderOfPages{
    protected WebDriver driver;
    private final By ENTERORMAKEORDERBUTTON = By.xpath("//*[@id='root']/div/main/section[2]/div/button");
    private final By BUNBUTTON = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[1]/span");
    private final By SAUCEBUTTON = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[2]/span");
    private final By INGREDIENTBUTTON = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[3]/span");
    public final By FIRSTBUN = By.xpath("//*[@id='root']/div/main/section[1]/div[2]/ul[1]/a[1]");
    private final By SELECTEDCLASSNAME = By.xpath("//*[@class ='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");

    public MainPage(WebDriver driver){this.driver = driver;}
    public void violetButtonClick(){driver.findElement(ENTERORMAKEORDERBUTTON).click(); }
    public void getMainPage()
    {
        driver.get(URLs.MAINPAGEURL);
    }

    public String getVoiletButtonText()
    {
        try
        {new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(ENTERORMAKEORDERBUTTON));
                 return driver.findElement(ENTERORMAKEORDERBUTTON).getText();
        }catch (RuntimeException e)
        {
            return null;
        }
    }
    public void bunButtonClick(){driver.findElement(BUNBUTTON).click();}
    public void sauceButtonClick(){driver.findElement(SAUCEBUTTON).click();}
    public void ingredientButtonClick(){driver.findElement(INGREDIENTBUTTON).click();}
    public String getSelectedTab()
    {
        return driver.findElement(SELECTEDCLASSNAME).getText();

    }



}
