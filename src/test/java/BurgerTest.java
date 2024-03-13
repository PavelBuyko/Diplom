import org.junit.Test;
import org.mockito.Mock;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BurgerTest {
    private final String BunName = "BulkaTest";
    private final float BulkaPrice = 1000;
    private final String TESTSAUSE = "testSauseName";
    @Mock
    Ingredient testIngredient;

    @Test
    public void BurgerSetBunsTest()
    {
        Bun bun = new Bun(BunName,BulkaPrice);
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertEquals(burger.bun, bun);
    }
    @Test
    public void BurgerAddIngredientTest()
    {   //создаем ожидаемый результат
        List<Ingredient> ExpectedList = new ArrayList<>();
        ExpectedList.add(this.testIngredient);
        //Получаем фактический результат
        Burger burger = new Burger();
        burger.addIngredient(this.testIngredient);
        assertEquals("Списки ингредиентов не совпадают",burger.ingredients,ExpectedList);
    }
    @Test
    public void BurgerRemoveIngredientTest()
    {
        Burger burger = new Burger();
        burger.addIngredient(this.testIngredient);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }
    @Test
    public void BurgerMoveIngredientTest()
    {
        Burger burger = new Burger();
        //Слот 0 - заглушка , слот 1 - объект со свойствами
        burger.addIngredient(this.testIngredient); //id 0
        burger.addIngredient(new Ingredient(IngredientType.SAUCE,TESTSAUSE,200)); //id 1
        //Перемещаем объект со свойствами в 0-й слот.
        burger.moveIngredient(1,0);
        //Проверяем, что название ингредиента в 0-м не пустое (Если замены не будет, то тест завалится, потому что getName от мока даст NullPointer).
        assertFalse(burger.ingredients.get(0).getName().isEmpty());

    }

}
