package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Resources.URLs;


public class RegistrationPage {
    protected WebDriver driver;
    private final By NAMEFIELD = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    private final By EMAILFIELD = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    private final By PASSWORDFIELD = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/div/input");
    private final By SUBMITBUTTON = By.xpath("//*[@id=\"root\"]/div/main/div/form/button");
    public final By PASSWORDERRORTEST = By.xpath("//*[@id='root']/div/main/div/form/fieldset[3]/div/p");
    private final By ENTERBUTTON = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");

    public RegistrationPage(WebDriver driver) {this.driver = driver;}

    public void register(String name,String email,String password)
    {
        getRegPage();
        fillName(name);
        fillEmail(email);
        fillPassword(password);
        Submit();
    }
    public void fillName(String name)
    {
        driver.findElement(NAMEFIELD).sendKeys(name);
    }
    public void fillEmail(String email)
    {
        driver.findElement(EMAILFIELD).sendKeys(email);
    }
    public void fillPassword(String password)
    {
        driver.findElement(PASSWORDFIELD).sendKeys(password);
    }
    public void Submit()
    {
        driver.findElement(SUBMITBUTTON).click();
    }
    public void getRegPage()
    {
        driver.get(URLs.REGISTRATIONURL);
    }
    public void clickEnterButton(){driver.findElement(ENTERBUTTON).click();}
}
