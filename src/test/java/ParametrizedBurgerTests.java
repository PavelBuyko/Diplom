import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Database;
import praktikum.Ingredient;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParametrizedBurgerTests {

    public List<Ingredient> ingredients = new ArrayList<Ingredient>();
    private Bun bun;
    private static final List<Bun> BUNLIST = new Database().availableBuns();
    private static final List<Ingredient> INGREDIENTLIST = new Database().availableIngredients();

    // Альтернативный вариант решения: можно было замокать содержимое базы, чтобы проверить исключительно корректность расчета
    // (тип и название не важно - подставлять можно было бы только цены)

    public ParametrizedBurgerTests(int[] ingredientsID, int bunId)
    {
        setupBunById(bunId);
        for(int i : ingredientsID)
        {
            setupIngredientById(i);
        }
    }

    //метод, который добавляет в список булку по её id в базе
    public void setupBunById(int x)
    {
        try
        {
            this.bun = BUNLIST.get(x);
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Такого id булки нет. Автозамена на 0 "+e);
            this.bun = BUNLIST.get(0);
        }
    }
    //метод, который добавляет в список ингредиент по его id в базе
    public void setupIngredientById(int x)
    {
        try
        {
            this.ingredients.add(INGREDIENTLIST.get(x));
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Такого id ингредиента нет. Автозамена на 0 "+e);
            this.ingredients.add(INGREDIENTLIST.get(0));
        }
    }

    @Parameterized.Parameters
    public static Object[][] getIngredientList() {

        return new Object[][] {
                {new int[]{0,1, 2, 3, 4, 5},2},
                {new int[]{0},0},
                {new int[]{1},1},
                {new int[]{2},2},
                {new int[]{3},0},
                {new int[]{4},1},
                {new int[]{5},2},
                {new int[]{6},0},
                {new int[]{1,1,2,0},0},
                {new int[]{},0},
                {new int[]{2},4},
        };
    }

    @Test
    public void burgerGetPriceTest()
    {
        Burger burger = new Burger();
        burger.bun = this.bun;
        burger.ingredients = this.ingredients;
       // System.out.println("Ожидаемая цена:"+expectedprice);
        assertEquals(burger.getPrice(),getPriceForExpected(burger),0.01);
    }
    @Test
    public void burgerGetReceiptTest()
    {
        Burger burger = new Burger();
        burger.bun = this.bun;
        burger.ingredients = this.ingredients;
        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", bun.getName()));

        for (Ingredient ingredient : ingredients) {
            receipt.append(String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(),
                    ingredient.getName()));
        }

        receipt.append(String.format("(==== %s ====)%n", bun.getName()));
        receipt.append(String.format("%nPrice: %f%n", getPriceForExpected(burger)));
        assertEquals("Чеки не совпадают",burger.getReceipt(),receipt.toString());
    }
    public float getPriceForExpected(Burger burger)
    {

        float expectedprice=burger.bun.getPrice() * 2;
        for (Ingredient ingredient : burger.ingredients) {
            expectedprice += ingredient.getPrice();
        }
        return expectedprice;
    }










}
