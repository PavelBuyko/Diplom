import Resources.JSONBuilder;
import com.google.gson.Gson;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class createOrderTest extends BaseTest {
    private final String APIORDERPATH = "/orders";
    private final List<String> TESTINGREDIENTLIST = Arrays.asList("61c0c5a71d1f82001bdaaa6d","61c0c5a71d1f82001bdaaa6f");
    private final String NOIDINGREDIENTERRORTEXT = "Ingredient ids must be provided";


    @Test
    @DisplayName("Создание заказа с авторизацией")
    public void makeAuthOrder()
    {
        JSONBuilder orderJson = new JSONBuilder();
        orderJson.setIngredents(TESTINGREDIENTLIST);
        PostApi postApi = new PostApi();
        Response response = postApi.doAuthPostRequest(APIORDERPATH,orderJson,super.json.getAccessToken());
        response.then().assertThat().statusCode((200));
    }
    @Test
    @DisplayName("Создание заказа без авторизации")
    public void makeNotAuthOrder()
    {
        JSONBuilder orderJson = new JSONBuilder();
        orderJson.setIngredents(TESTINGREDIENTLIST);
        PostApi postApi = new PostApi();
        Response response = postApi.doPostRequest(APIORDERPATH,orderJson);
        response.then().assertThat().statusCode((200));
        //разбираем ответ
        Gson gson = new Gson();
        JSONBuilder responceJson = gson.fromJson(response.getBody().prettyPrint(),JSONBuilder.class);
        assertTrue("Success не равен True",responceJson.isSuccess());
        assertFalse("Не пришло название бургера",responceJson.getName().isEmpty());

    }
    @Test
    @DisplayName("Создание заказа без ингредиентов")
    public void makeOrderWithNoIngredients()
    {
        JSONBuilder orderJson = new JSONBuilder();
        PostApi postApi = new PostApi();
        Response response = postApi.doPostRequest(APIORDERPATH,orderJson);
        response.then().assertThat().statusCode((400));
        //разбираем ответ
        Gson gson = new Gson();
        JSONBuilder responceJson = gson.fromJson(response.getBody().prettyPrint(),JSONBuilder.class);
        String actualMessage = responceJson.getMessage();
        assertFalse("Success равен True",responceJson.isSuccess());
        assertThat("Текст сообщения об ошибке не корректен",actualMessage.equals(NOIDINGREDIENTERRORTEXT));

    }
    @Test
    @DisplayName("Создание заказа с невалидными ингредиентами")
    public void makeOrderWithInvalidIngredient()
    {
        JSONBuilder orderJson = new JSONBuilder();
        orderJson.setIngredents(Arrays.asList("1","2"));
        PostApi postApi = new PostApi();
        Response response = postApi.doPostRequest(APIORDERPATH,orderJson);
        response.then().assertThat().statusCode((500));

    }

}
