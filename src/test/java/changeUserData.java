import Resources.JSONBuilder;
import Resources.RandomString;
import com.google.gson.Gson;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;

public class changeUserData extends BaseTest {
    private final String APIUSERPATH = "auth/user";
    private final String UNAUTHERRORTEXT = "You should be authorised";

    @Test
    @DisplayName("Смена имени пользователя с авторизацией")
    public void changeNameWithAuthTest()
    {   //создаем новое имя
        String expectedName = RandomString.generateRandomString(8);
        //создаем новый JSON
        JSONBuilder newJson = new JSONBuilder();
        newJson.setName(expectedName);
        //Отправляем запрос
        PatchApi patchApi = new PatchApi();
        Response responce = patchApi.doAuthPatchRequest(APIUSERPATH,newJson,super.json.getAccessToken());
        responce.then().assertThat().statusCode((200));
        //Достаем имя из ответа
        Gson gson = new Gson();
        JSONBuilder responceJson = gson.fromJson(responce.getBody().prettyPrint(),JSONBuilder.class);
        //сравниваем
        String actualName = responceJson.getUser().get("name");
        assertThat("Имя не совпадает с тем, которое было отправлено в запросе для замены",actualName.equals(expectedName));

    }
//    @Test
//    public void changePasswordWithAuthTest()
//    {   //создаем новый пароль
//        String expectedPassword = RandomString.generateRandomString(10);
//        //создаем новый JSON
//        JSONBuilder newJson = new JSONBuilder();
//        newJson.setPassword(expectedPassword);
//        //Отправляем запрос
//        PatchApi patchApi = new PatchApi();
//        Response response = patchApi.doAuthPatchRequest(APIUSERPATH,newJson,this.json.getAccessToken());
//        response.then().assertThat().statusCode((200));
//        //Достаем имя из ответа
//        Gson gson = new Gson();
//        JSONBuilder responceJson = gson.fromJson(response.getBody().prettyPrint(),JSONBuilder.class);
//        //сравниваем
//        String actualPassword = responceJson.getUser().get("password");
//        assertThat("Пароль не совпадает с тем, который был отправлен в запросе для замены",actualPassword.equals(expectedPassword));
//
//    }
    @Test
    @DisplayName("Смена электронной почты с авторизацией")
    public void changeEmailWithAuthTest()
    {   //создаем новый пароль
        String expectedEmail = RandomString.generateRandomString(10)+"@gmail.com";;
        //создаем новый JSON
        JSONBuilder newJson = new JSONBuilder();
        newJson.setEmail(expectedEmail);
        //Отправляем запрос
        PatchApi patchApi = new PatchApi();
        Response responce = patchApi.doAuthPatchRequest(APIUSERPATH,newJson,super.json.getAccessToken());
        //Достаем почту из ответа
        Gson gson = new Gson();
        JSONBuilder responceJson = gson.fromJson(responce.getBody().prettyPrint(),JSONBuilder.class);
        //сравниваем
        responce.then().assertThat().statusCode((200));
        String actualEmail = responceJson.getUser().get("email");
        assertThat("Почта не совпадает с той, которая была отправлена в запросе для замены",actualEmail.equals(expectedEmail.toLowerCase()));
    }
    @Test
    @DisplayName("Смена имени пользователя без авторизации")
    public void changeNameWithoutAuthTest()
    {   //создаем новое имя
        String expectedName = RandomString.generateRandomString(8);
        //создаем новый JSON
        JSONBuilder newJson = new JSONBuilder();
        newJson.setName(expectedName);
        //Отправляем запрос
        PatchApi patchApi = new PatchApi();
        Response responce = patchApi.doNotAuthPatchRequest(APIUSERPATH,newJson);
        //Достаем имя из ответа
        responce.then().assertThat().statusCode((401));
        Gson gson = new Gson();
        JSONBuilder responceJson = gson.fromJson(responce.getBody().prettyPrint(),JSONBuilder.class);
        //Проверка сообщения об ошибке
        String message = responceJson.getMessage();
        assertThat("Сообщение об ошибке некорректно",message.equals(UNAUTHERRORTEXT));
        assertFalse("success равен True",responceJson.isSuccess());

    }
    @Test
    @DisplayName("Смена пароля без авторизации")
    public void changePasswordWithoutAuthTest()
    {   //создаем новый пароль
        String expectedPassword = RandomString.generateRandomString(10);
        //создаем новый JSON
        JSONBuilder newJson = new JSONBuilder();
        newJson.setPassword(expectedPassword);
        //Отправляем запрос
        PatchApi patchApi = new PatchApi();
        Response response = patchApi.doNotAuthPatchRequest(APIUSERPATH,newJson);
        response.then().assertThat().statusCode((401));
        //Достаем имя из ответа
        Gson gson = new Gson();
        JSONBuilder responceJson = gson.fromJson(response.getBody().prettyPrint(),JSONBuilder.class);
        //Проверка сообщения об ошибке
        String message = responceJson.getMessage();
        assertThat("Сообщение об ошибке некорректно",message.equals(UNAUTHERRORTEXT));
        assertFalse("success равен True",responceJson.isSuccess());

    }
    @Test
    @DisplayName("Смена электронной почты без авторизации")
    public void changeEmailWithoutAuthTest()
    {   //создаем новый пароль
        String expectedEmail = RandomString.generateRandomString(10)+"@gmail.com";;
        //создаем новый JSON
        JSONBuilder newJson = new JSONBuilder();
        newJson.setEmail(expectedEmail);
        //Отправляем запрос
        PatchApi patchApi = new PatchApi();
        Response response = patchApi.doNotAuthPatchRequest(APIUSERPATH,newJson);
        response.then().assertThat().statusCode((401));
        //Достаем почту из ответа
        Gson gson = new Gson();
        JSONBuilder responceJson = gson.fromJson(response.getBody().prettyPrint(),JSONBuilder.class);
        //Проверка сообщения об ошибке
        String message = responceJson.getMessage();
        assertThat("Сообщение об ошибке некорректно",message.equals(UNAUTHERRORTEXT));
        assertFalse("success равен True",responceJson.isSuccess());
    }

}
