import PageObjects.LoginPage;
import PageObjects.MainPage;
import PageObjects.RecoverPasswordPage;
import PageObjects.RegistrationPage;
import Resources.setupTestData;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Resources.URLs;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginUiTest extends BaseTest {
    private final String EXPECTEDPASSWORDERRORTEXT = "Некорректный пароль";
    private final String VIOLETBUTTONAFTERLOGINEXPECTEDTEXT = "Оформить заказ";

    @Test
    @DisplayName("вход через кнопку «Личный кабинет")
    public void basicLoginTest()
    {
        //Идем до страницы логина
        MainPage mainPage = new MainPage(super.driver);
        mainPage.getMainPage();
        mainPage.clickAccount(super.driver);
        //Логинимся и проверяем результат.
        setupTestData TestData = checkPageAndDoLogin();
        checkLoginResult(TestData);
    }

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    public void loginFromMainPageTest()
    {
        //Идем до страницы логина
        MainPage mainPage = new MainPage(super.driver);
        mainPage.getMainPage();
        mainPage.violetButtonClick();
        //Логинимся и проверяем результат.
        setupTestData TestData = checkPageAndDoLogin();
        checkLoginResult(TestData);

    }
    @Test
    @DisplayName("вход через кнопку в форме регистрации")
    public void loginFromRegPageTest()
    {
        RegistrationPage regPage = new RegistrationPage(super.driver);
        regPage.getRegPage();
        regPage.clickEnterButton();
        //Логинимся и проверяем результат.
        setupTestData TestData = checkPageAndDoLogin();
        checkLoginResult(TestData);
    }
    @Test
    @DisplayName("вход через кнопку в форме восстановления пароля")
    public void loginFromRecoverPasswordPageTest()
    {
        RecoverPasswordPage recPage = new RecoverPasswordPage(super.driver);
        recPage.getRecoverPage();
        recPage.clickEnterButton();
        //Логинимся и проверяем результат.
        setupTestData TestData = checkPageAndDoLogin();
        checkLoginResult(TestData);
    }
    @Test
    public void shortPasswordLoginTest()
    {
        setupTestData TestData = new setupTestData();
        TestData.createTestUser(5,9,9);
        LoginPage loginPage = new LoginPage(super.driver);
        loginPage.login(TestData.json.getEmail(),TestData.json.getPassword());
        assertTrue("Текст ошибки не корректен",super.driver.findElement(loginPage.PASSWORDERRORTEST).getText().equals(EXPECTEDPASSWORDERRORTEXT));
    }

    @Step("Проверяем URL, создаем пользователя и заходим")
    private setupTestData checkPageAndDoLogin()
    {
        assertTrue("Перехода на страницу логина не произошло",driver.getCurrentUrl().equals(URLs.LOGINPAGEURL));
        setupTestData TestData = new setupTestData();
        TestData.createTestUser(9,9,9);
        LoginPage loginPage = new LoginPage(super.driver);
        loginPage.login(TestData.json.getEmail(),TestData.json.getPassword());
        return TestData;
    }
    @Step("Проверяем результат")
    private void checkLoginResult(setupTestData testData)
    {
        try
        {new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlToBe(URLs.MAINPAGEURL));
        }catch (RuntimeException e)
        {   //Если перехода не произошло, то удаляем пользователя и заваливаем тест
            testData.deleteTestUser();
            assertTrue("Переход на другую страницу не был осуществлен",driver.getCurrentUrl().equals(URLs.MAINPAGEURL));
        }
        //Проверяем текст на кнопке
        MainPage mainPage = new MainPage(super.driver);
        if (mainPage.getVoiletButtonText().equals(VIOLETBUTTONAFTERLOGINEXPECTEDTEXT))
        {
            testData.deleteTestUser();
        }
        else
        {
            testData.deleteTestUser();
            assertTrue("Текст на фиолетовой кнопке не корректен", false);
        }
    }
}
