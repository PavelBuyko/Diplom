import Resources.JSONBuilder;
import Resources.OrderResponeJSON;
import Resources.RandomString;
import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;

public class getOrderListTest {

    private JSONBuilder json;
    private final String APIREGPATH = "auth/register";
    private final String APIUSERPATH = "auth/user";
    private final String UNAUTHERRORTEXT = "You should be authorised";
    private final String APIORDERPATH = "/orders";
    private final List<String> TESTINGREDIENTLIST = Arrays.asList("61c0c5a71d1f82001bdaaa6d","61c0c5a71d1f82001bdaaa6f");

    @Before
    @Step("Создаем пользователя")
    public void createTestUser() {   //Создаем данные для регистрации
        String password = RandomString.generateRandomString(8);
        String name = RandomString.generateRandomString(6);
        String email = RandomString.generateRandomString(8) + "@yandex.ru";
        //Создаем и заполняем JSON
        JSONBuilder json = new JSONBuilder();
        json.setEmail(email);
        json.setName(name);
        json.setPassword(password);
        this.json = json;
        //Регистрируемся
        PostApi api = new PostApi();
        Response response = api.doPostRequest(APIREGPATH, json);
        //разбираем ответ
        Gson gson = new Gson();
        JSONBuilder responceJson = gson.fromJson(response.getBody().prettyPrint(), JSONBuilder.class);
        //сохраняем токен авторизации
        this.json.setAccessToken(responceJson.getAccessToken());
    }
    @Test
    @DisplayName("Получение списка заказов с авторизацией")
    public void getOrderListWithAuth()
    {   //создаем заказ
        JSONBuilder orderJson = new JSONBuilder();
        orderJson.setIngredents(TESTINGREDIENTLIST);
        PostApi postApi = new PostApi();
        //отправляем заказ
        postApi.doAuthPostRequest(APIORDERPATH,orderJson,this.json.getAccessToken());
        //получаем список заказов
        GetApi getApi = new GetApi();
        Response response = getApi.doAuthGetRequest(APIORDERPATH,this.json.getAccessToken());
        response.then().assertThat().statusCode((200));
        //разбираем ответ
        Gson gson = new Gson();
        OrderResponeJSON responceJson = gson.fromJson(response.getBody().prettyPrint(), OrderResponeJSON.class);
        assertFalse("Итоговое количество заказов отсуствует в ответе",responceJson.getTotal().isEmpty());

    }

    @Test
    @DisplayName("Получение списка заказов без авторизации")
    public void getOrderListWithoutAuth()
    {
        //создаем заказ
        JSONBuilder orderJson = new JSONBuilder();
        orderJson.setIngredents(TESTINGREDIENTLIST);
        PostApi postApi = new PostApi();
        //отправляем заказ
        postApi.doAuthPostRequest(APIORDERPATH,orderJson,this.json.getAccessToken()).then().assertThat().statusCode((200));
        //получаем список заказов
        GetApi getApi = new GetApi();
        Response response = getApi.doNOTAuthGetRequest(APIORDERPATH);
        response.then().assertThat().statusCode((401));
        //разбираем ответ
        Gson gson = new Gson();
        JSONBuilder responceJson = gson.fromJson(response.getBody().prettyPrint(), JSONBuilder.class);
        assertFalse("В ответе пришел True",responceJson.getSuccess());
        assertThat("Текст сообщения об ошибке не совпадает",responceJson.getMessage().equals(UNAUTHERRORTEXT));

    }

    @After
    @Step("Удаляем пользователя")
    public void deleteTestUser()
    {   String token = json.getAccessToken();
        if(token != null)
        {
            DeleteApi deleteApi = new DeleteApi();
            deleteApi.doDeleteRequest(APIUSERPATH,json.getAccessToken());
        }
    }
}
