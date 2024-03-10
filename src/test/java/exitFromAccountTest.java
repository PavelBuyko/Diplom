import PageObjects.LoginPage;
import PageObjects.MainPage;
import PageObjects.PersonalAccountPage;
import Resources.URLs;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import Resources.setupTestData;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;


public class exitFromAccountTest extends BaseTest{
    private final String UNAUTHBUTTONTEXT = "Войти в аккаунт";

    @Test
    @DisplayName("Переход по клику на кнопку «Выйти» в личном кабинете")
    public void ExitFromPersonalAccountClickTest()
    {
        //создаем пользователя
        setupTestData testData = new setupTestData();
        testData.createTestUser(7,7,7);
        //Авторизуемся
        LoginPage loginPage = new LoginPage(super.driver);
        loginPage.login(testData.json.getEmail(),testData.json.getPassword());
        //Переходим в личный кабинет
        MainPage mainPage = new MainPage(super.driver);
        mainPage.clickAccount(super.driver);
        //Жмем на кнопку выход
        PersonalAccountPage accountPage = new PersonalAccountPage(super.driver);
        try
        {new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(accountPage.EXITBUTTON));
                 accountPage.exitButtonClick();
        }catch (RuntimeException e)
        {   //Если перехода не произошло, то удаляем пользователя и заваливаем тест
            testData.deleteTestUser();
            assertTrue("Кнопка Выход не была найдена",driver.findElement(accountPage.EXITBUTTON).isDisplayed());
        }
        //Проверяем переход на страницу логина, после нажатия на выход
        testData.deleteTestUser();
        assertTrue("Переход на страницу логина не произошел",driver.getCurrentUrl().equals(URLs.LOGINPAGEURL));
    }
}
