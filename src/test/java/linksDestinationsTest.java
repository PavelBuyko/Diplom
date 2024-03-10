import PageObjects.LoginPage;
import PageObjects.MainPage;
import PageObjects.PersonalAccountPage;
import Resources.URLs;
import Resources.setupTestData;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class linksDestinationsTest extends BaseTest{

    @Test
    @DisplayName("Переход по клику на «Конструктор» из личного кабинета")
    public void constructorClickDestinationTest()
    {
        setupTestData userData = startFromAccountPage();
        PersonalAccountPage accPage = new PersonalAccountPage(super.driver);
        accPage.clickConstructor(super.driver);
        checkRedirectResult(userData,URLs.MAINPAGEURL); //ассерт внутри
    }
    @Test
    @DisplayName("Переход по клику на логотип Stellar Burgers из личного кабинета")
    public void logoClickDestinationTest()
    {
        setupTestData userData = startFromAccountPage();
        PersonalAccountPage accPage = new PersonalAccountPage(super.driver);
        accPage.clickLogo(super.driver);
        checkRedirectResult(userData,URLs.MAINPAGEURL); //ассерт внутри
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет» для авторизированного пользователя")
    public void AuthUserPersonalAccountButtonClickTest()
    {
        setupTestData userData = startFromAccountPage();
        //переходим на стартовую страницу
        MainPage mainPage = new MainPage(super.driver);
        mainPage.getMainPage();
        //Кликаем на кнопку и проверяем результат
        mainPage.clickAccount(super.driver);
        checkRedirectResult(userData,URLs.PERSONALACCOUTURL); //ассерт внутри

    }
    @Test
    @DisplayName("Переход по клику на «Личный кабинет» для не авторизированного пользователя")
    public void NotAuthUserPersonalAccountButtonClickTest()
    {
        MainPage mainPage = new MainPage(super.driver);
        mainPage.getMainPage();
        //Кликаем на кнопку и проверяем результат
        mainPage.clickAccount(super.driver);
        assertTrue("Переход на другую страницу не был осуществлен",driver.getCurrentUrl().equals(URLs.LOGINPAGEURL));
    }

    @Step("Создаем стартовое окружение: Страница аккаунта")
    private setupTestData startFromAccountPage()
    {
        setupTestData testData = new setupTestData();
        testData.createTestUser(8,8,8);
        LoginPage loginPage = new LoginPage(super.driver);
        loginPage.login(testData.json.getEmail(),testData.json.getPassword());
        return testData;
    }

    @Step("Проверяем результат")
    private void checkRedirectResult(setupTestData testData,String target)
    {
        try
        {new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlToBe(target));
        }catch (RuntimeException e)
        {   //Если перехода не произошло, то удаляем пользователя и заваливаем тест
            testData.deleteTestUser();
            assertTrue("Переход на другую страницу не был осуществлен",driver.getCurrentUrl().equals(target));
        }
        testData.deleteTestUser();
    }

}
