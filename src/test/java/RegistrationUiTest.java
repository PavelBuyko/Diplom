import PageObjects.LoginPage;
import PageObjects.RegistrationPage;
import Resources.URLs;
import Resources.setupTestData;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class RegistrationUiTest extends BaseTest{
    private final String EXPECTEDPASSWORDERRORTEXT = "Некорректный пароль";

    @Test
    @DisplayName("Успешная регистрация")
    public void basicRegTest()
    {
        setupTestData TestData = new setupTestData();
        TestData.createRegData(9,9,9);
        RegistrationPage regPage = new RegistrationPage(super.driver);
        regPage.register(TestData.json.getName(),TestData.json.getEmail(),TestData.json.getPassword());
        try
        {new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.urlToBe(URLs.LOGINPAGEURL));
        }catch (RuntimeException e)
        {   //Если перехода не произошло, то удаляем пользователя и заваливаем тест
            TestData.deleteTestUser();
            assertTrue("Переход на другую страницу не был осуществлен",driver.getCurrentUrl().equals(URLs.LOGINPAGEURL));
        }
        //пробуем зайти
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TestData.json.getEmail(),TestData.json.getPassword());
        try
        {new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlToBe(URLs.MAINPAGEURL));
        }catch (RuntimeException e)
        {   //Если перехода не произошло, то удаляем пользователя и заваливаем тест
            TestData.deleteTestUser();
            assertTrue("Переход на другую страницу не был осуществлен",driver.getCurrentUrl().equals(URLs.MAINPAGEURL));
        }
    }
    @Test
    @DisplayName("Ошибка для некорректного пароля")
    public void shortPasswordRegTest()
    {
        setupTestData TestData = new setupTestData();
        TestData.createRegData(4,9,9);
        RegistrationPage regPage = new RegistrationPage(super.driver);
        regPage.register(TestData.json.getName(),TestData.json.getEmail(),TestData.json.getPassword());
        assertTrue("Текст ошибки не корректен",super.driver.findElement(regPage.PASSWORDERRORTEST).getText().equals(EXPECTEDPASSWORDERRORTEXT));
    }
}
