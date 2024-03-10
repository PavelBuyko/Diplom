import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setupWebdriver()
    {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        this.driver = driver;
    }

    @After
    public void closeBrowser()
    {
        driver.close();
    }
}
