package PageObjects;

import Resources.URLs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoverPasswordPage {
    protected WebDriver driver;
    private final By ENTERBUTTON = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");

    public RecoverPasswordPage (WebDriver driver){this.driver = driver;}

    public void getRecoverPage()
    {
        driver.get(URLs.FORGOTPASSWORDURL);
    }
    public void clickEnterButton()
    {
        driver.findElement(ENTERBUTTON).click();
    }

}
