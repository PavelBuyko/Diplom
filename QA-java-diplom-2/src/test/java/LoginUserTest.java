import Resources.JSONBuilder;
import com.google.gson.Gson;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginUserTest extends BaseTest {
    private final String EMPTYFIELDERRORTEXT = "email or password are incorrect";
    private final String APILOGINPATH = "auth/login";

    @Test
    @DisplayName("Логин с корректными данными")
    public void basicLoginTest()
    {   //формирование JSON с логином и паролем
        JSONBuilder loginJson = new JSONBuilder();
        loginJson.setEmail(super.json.getEmail());
        loginJson.setPassword(super.json.getPassword());
        //отправка запроса
        PostApi postApi = new PostApi();
        Response response = postApi.doPostRequest(APILOGINPATH,loginJson);
        response.then().assertThat().statusCode((200));
        //Обработка ответа
        Gson gson = new Gson();
        JSONBuilder responceJson = gson.fromJson(response.getBody().prettyPrint(),JSONBuilder.class);
        assertTrue("Success не равен True",responceJson.isSuccess());
        assertFalse("Не пришел токен авторизации",responceJson.getAccessToken().isEmpty());
        assertFalse("Не пришел рефреш токен",responceJson.getRefreshToken().isEmpty());

    }
    @Test
    @DisplayName("Логин без почты")
    public void noUserNameLoginTest()
    {
        //формирование JSON с паролем
        JSONBuilder loginJson = new JSONBuilder();
        loginJson.setPassword(super.json.getPassword());
        //отправка запроса
        PostApi postApi = new PostApi();
        Response response = postApi.doPostRequest(APILOGINPATH,loginJson);
        response.then().assertThat().statusCode((401));
        //обработка ответа
        Gson gson = new Gson();
        JSONBuilder responceJson = gson.fromJson(response.getBody().prettyPrint(),JSONBuilder.class);
        assertFalse("Success равен True",responceJson.isSuccess());
        assertThat("Текст сообщения об ошибке не корректен",responceJson.getMessage().equals(EMPTYFIELDERRORTEXT));

    }
    @Test
    @DisplayName("Логин без пароля")
    public void noPasswordLoginTest()
    {   //формирование JSON с логином
        JSONBuilder loginJson = new JSONBuilder();
        loginJson.setEmail(super.json.getEmail());
        //отправка запроса
        PostApi postApi = new PostApi();
        Response response = postApi.doPostRequest(APILOGINPATH,loginJson);
        response.then().assertThat().statusCode((401));
        Gson gson = new Gson();
        JSONBuilder responceJson = gson.fromJson(response.getBody().prettyPrint(),JSONBuilder.class);
        assertFalse("Success равен True",responceJson.isSuccess());
        assertThat("Текст сообщения об ошибке не корректен",responceJson.getMessage().equals(EMPTYFIELDERRORTEXT));

    }

}
