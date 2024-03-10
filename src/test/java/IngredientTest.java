import org.junit.Test;
import org.mockito.Mock;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

public class IngredientTest {

    private final String testName = "testName";
    private final float testPrice = 1000;
    @Mock
    IngredientType type;

    @Test
    public void IngredientGetPriceTest()
    {
        Ingredient ingredient = new Ingredient(type,testName,testPrice);
        assertEquals(ingredient.getPrice(),testPrice,0.01);

    }
    @Test
    public void IngredientGetNameTest()
    {
        Ingredient ingredient = new Ingredient(type,testName,testPrice);
        assertEquals("Названия не совпадают",ingredient.getName(),testName);

    }
    @Test
    public void IngredientGetTypeTest()
    {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING,testName,testPrice);
        assertEquals("Тип ингредиентов не совпадает",ingredient.getType(),IngredientType.FILLING);
    }
}
