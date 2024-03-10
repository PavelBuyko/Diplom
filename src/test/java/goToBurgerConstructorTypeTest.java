import PageObjects.MainPage;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.Point;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class goToBurgerConstructorTypeTest extends BaseTest{

    private final String BUNEXPECTEDNAME = "Булки";
    private final String SAUCEEXPECTEDNAME = "Соусы";
    private final String INGREDIENTEXPECTEDNAME = "Начинки";

    @Test
    @DisplayName("Переходы к разделу «Булки»")
    public void scrollToBunTest()
    {
        MainPage mainPage = new MainPage(super.driver);
        mainPage.getMainPage();
        //выбираем другую категорию
        mainPage.sauceButtonClick();
        //выбираем проверяемую категорию
        mainPage.bunButtonClick();
        assertEquals("Скролл отработал не корректно",mainPage.getSelectedTab(),BUNEXPECTEDNAME);
    }
    @Test
    @DisplayName("Переходы к разделу «Соусы»")
    public void scrollToSauceTest()
    {
        MainPage mainPage = new MainPage(super.driver);
        mainPage.getMainPage();
        //выбираем другую категорию
        mainPage.sauceButtonClick();
        assertEquals("Скролл отработал не корректно",mainPage.getSelectedTab(),SAUCEEXPECTEDNAME);

    }
    @Test
    @DisplayName("Переходы к разделу «Начинки»")
    public void scrollToIngredientsTest()
    {
        MainPage mainPage = new MainPage(super.driver);
        mainPage.getMainPage();
        //выбираем другую категорию
        mainPage.ingredientButtonClick();
        assertEquals("Скролл отработал не корректно",mainPage.getSelectedTab(),INGREDIENTEXPECTEDNAME);

    }

}
