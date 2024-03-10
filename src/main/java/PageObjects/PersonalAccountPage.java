package PageObjects;

import Resources.URLs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccountPage extends HeaderOfPages{
    protected WebDriver driver;
    public final By EXITBUTTON = By.xpath("//*[@id='root']/div/main/div/nav/ul/li[3]/button");

    public PersonalAccountPage(WebDriver driver) {this.driver = driver;}

    public void exitButtonClick()

    {
        driver.findElement(EXITBUTTON).click();
    }
    public void getAccountPage()
    {
        driver.get(URLs.PERSONALACCOUTURL);
    }
}
