import org.junit.Test;
import org.mockito.Mock;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {
    private final String EXPECTEDSAUSE = "SAUCE";
    private final String EXPECTEDFILLING = "FILLING";

    @Mock
    private String name;
    @Mock
    private float price;

    @Test
    public void IngredientTypeSauceTest()
    {
        String testType = String.valueOf(IngredientType.SAUCE);
        assertEquals("Название типа 'соус' не совпадает",testType,EXPECTEDSAUSE);
    }
    @Test
    public void IngredientTypeFillingTest()
    {
        String testType = String.valueOf(IngredientType.FILLING);
        assertEquals("Название типа 'начинка' не совпадает",testType,EXPECTEDFILLING);
    }
}
