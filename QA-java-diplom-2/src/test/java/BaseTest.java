import Resources.JSONBuilder;
import Resources.RandomString;
import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;

public abstract class BaseTest {
    public JSONBuilder json;
    private final String APIREGPATH = "auth/register";
    private final String APIUSERPATH = "auth/user";

    @Before
    @Step("Создаем пользователя")
    public void createTestUser()
    {   //Создаем данные для регистрации
        String password = RandomString.generateRandomString(8);
        String name = RandomString.generateRandomString(6);
        String email = RandomString.generateRandomString(8)+"@yandex.ru";
        //Создаем и заполняем JSON
        JSONBuilder json = new JSONBuilder();
        json.setEmail(email);
        json.setName(name);
        json.setPassword(password);
        this.json = json;
        //Регистрируемся
        PostApi api = new PostApi();
        Response response = api.doPostRequest(APIREGPATH,json);
        //разбираем ответ
        Gson gson = new Gson();
        JSONBuilder responceJson = gson.fromJson(response.getBody().prettyPrint(),JSONBuilder.class);
        //сохраняем токен авторизации для будущего удаления
        this.json.setAccessToken(responceJson.getAccessToken());
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
