package Resources;

import Resources.BaseHttpClient;
import Resources.JSONBuilder;
import Resources.RandomString;
import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class setupTestData {
    public JSONBuilder json;
    private final String APIREGPATH = "auth/register";
    private final String APIUSERPATH = "auth/user";
    private final String LOGINPATH = "auth/login";
    public setupTestData(){}

    @Step("Создание пользователя")
    public void createTestUser(int passwordLength,int nameLength,int EmailLength)
    {   //Создаем данные для регистрации
        String password = RandomString.generateRandomString(passwordLength);
        String name = RandomString.generateRandomString(nameLength);
        String email = RandomString.generateRandomString(EmailLength)+"@yandex.ru";
        //Создаем и заполняем JSON
        JSONBuilder json = new JSONBuilder();
        json.setEmail(email);
        json.setName(name);
        json.setPassword(password);
        this.json = json;
        //Регистрируемся
        BaseHttpClient api = new BaseHttpClient();
        Response response = api.doPostRequest(APIREGPATH,json);
    }
    @Step("Создание данных для регистрации")
    public void createRegData(int passwordLength,int nameLength,int EmailLength)
    {   //Создаем данные для регистрации
        String password = RandomString.generateRandomString(passwordLength);
        String name = RandomString.generateRandomString(nameLength);
        String email = RandomString.generateRandomString(EmailLength)+"@yandex.ru";
        //Создаем и заполняем JSON
        JSONBuilder json = new JSONBuilder();
        json.setEmail(email);
        json.setName(name);
        json.setPassword(password);
        this.json = json;
    }
    @Step("Удаление пользователя")
    public void deleteTestUser()
    {   //формируем JSON для логина
        JSONBuilder loginJson = new JSONBuilder();
        loginJson.setEmail(this.json.getEmail());
        loginJson.setPassword(this.json.getPassword());
        //Отправляем запрос логина
        BaseHttpClient api = new BaseHttpClient();
        Response response = api.doPostRequest(LOGINPATH,loginJson);
        //Достаем из ответа токен
        Gson gson = new Gson();
        JSONBuilder responceJson = gson.fromJson(response.getBody().prettyPrint(),JSONBuilder.class);
        String token = responceJson.getAccessToken();
        System.out.println("пользователь успешно удален");
        //удаляем пользователя, если он был создан
        if(token != null)
        {
            BaseHttpClient deleteApi = new BaseHttpClient();
            deleteApi.doDeleteRequest(APIUSERPATH,token);
        }
    }
}
