import Resources.JSONBuilder;
import Resources.RandomString;
import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CreateUserTest {

    private JSONBuilder json;
    private final String SAMEUSERERRORTEXT = "User already exists";
    private final String EMPTYFIELDERRORTEXT = "Email, password and name are required fields";
    private final String APIREGPATH = "auth/register";
    private final String APIDELETEPATH = "auth/user";


    @Before
    @Step("Создаем тестовые данные для JSON")
    public void makeJson()
    {
        String password = RandomString.generateRandomString(8);
        String name = RandomString.generateRandomString(6);
        String email = RandomString.generateRandomString(8)+"@yandex.ru";
        JSONBuilder json = new JSONBuilder();
        json.setEmail(email);
        json.setName(name);
        json.setPassword(password);
        this.json = json;
    }
    @Test
    @DisplayName("Создание пользователя с корректными данными")
    public void createBasicUser()
    {   PostApi api = new PostApi();
        Response response = api.doPostRequest(APIREGPATH,json);
        response.then().assertThat().statusCode((200));
        //разбираем ответ
        Gson gson = new Gson();
        JSONBuilder responceJson = gson.fromJson(response.getBody().prettyPrint(),JSONBuilder.class);
        this.json.setAccessToken(responceJson.getAccessToken());
        assertTrue("Success не равен True",responceJson.isSuccess());
        assertFalse("Не пришел токен авторизации",responceJson.getAccessToken().isEmpty());
        assertFalse("Не пришел рефреш токен",responceJson.getRefreshToken().isEmpty());
    }
    @Test
    @DisplayName("Создание такого же пользователя")
    public void createSameUser()
    {   //создали 1-го пользователя
        PostApi api = new PostApi();
        api.doPostRequest(APIREGPATH,json);
        //Попытка создать кого-же
        Response response = api.doPostRequest(APIREGPATH,json);
        response.then().assertThat().statusCode((403));
        //разбираем ответ
        Gson gson = new Gson();
        JSONBuilder responceJson = gson.fromJson(response.getBody().prettyPrint(),JSONBuilder.class);
        assertFalse("Success равен True",responceJson.isSuccess());
        assertThat("Текст сообщения об ошибке не корректен",responceJson.getMessage().equals(SAMEUSERERRORTEXT));

    }
    @Test
    @DisplayName("Создание пользователя без электронной почты")
    public void createUserWithoutEmail()
    {
        json.setEmail(null);
        PostApi api = new PostApi();
        Response response = api.doPostRequest(APIREGPATH,json);
        response.then().assertThat().statusCode((403));
        //разбираем ответ
        Gson gson = new Gson();
        JSONBuilder responceJson = gson.fromJson(response.getBody().prettyPrint(),JSONBuilder.class);
        assertFalse("Success равен True",responceJson.isSuccess());
        assertThat("Текст сообщения об ошибке не корректен",responceJson.getMessage().equals(EMPTYFIELDERRORTEXT));
    }
    @Test
    @DisplayName("Создание пользователя без пароля")
    public void createUserWithoutPassword()
    {
        json.setPassword(null);
        PostApi api = new PostApi();
        Response response = api.doPostRequest(APIREGPATH,json);
        response.then().assertThat().statusCode((403));
        //разбираем ответ
        Gson gson = new Gson();
        JSONBuilder responceJson = gson.fromJson(response.getBody().prettyPrint(),JSONBuilder.class);
        assertFalse("Success равен True",responceJson.isSuccess());
        assertThat("Текст сообщения об ошибке не корректен",responceJson.getMessage().equals(EMPTYFIELDERRORTEXT));

    }
    @Test
    @DisplayName("Создание пользователя без имени")
    public void createUserWithoutName()
    {
        json.setName(null);
        PostApi api = new PostApi();
        Response response = api.doPostRequest(APIREGPATH,json);
        response.then().assertThat().statusCode((403));
        //разбираем ответ
        Gson gson = new Gson();
        JSONBuilder responceJson = gson.fromJson(response.getBody().prettyPrint(),JSONBuilder.class);
        assertFalse("Success равен True",responceJson.isSuccess());
        assertThat("Текст сообщения об ошибке не корректен",responceJson.getMessage().equals(EMPTYFIELDERRORTEXT));

    }

    @After
    @Step("Удаляем пользователя")
    public void deleteCreatedTestUser()
    {   String token = json.getAccessToken();
        if(token != null)
       {
           DeleteApi deleteApi = new DeleteApi();
           deleteApi.doDeleteRequest(APIDELETEPATH,json.getAccessToken());
      }
    }


}
