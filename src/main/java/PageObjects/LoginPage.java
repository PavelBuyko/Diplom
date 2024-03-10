package PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Resources.URLs;

public class LoginPage {
    protected WebDriver driver;
    private final By EMAILFIELD = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    private final By PASSWORDFIELD = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    private final By SUBMITBUTTON = By.xpath("//*[@id=\"root\"]/div/main/div/form/button");
    public final By PASSWORDERRORTEST = By.xpath("//*[@id='root']/div/main/div/form/fieldset[2]/div/p");

    public LoginPage(WebDriver driver) {this.driver = driver;}
    public void login(String email,String password)
    {
        getLoginPage();
        fillEmail(email);
        fillPassword(password);
        Submit();
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
    public void getLoginPage()
    {
        driver.get(URLs.LOGINPAGEURL);
    }


}
