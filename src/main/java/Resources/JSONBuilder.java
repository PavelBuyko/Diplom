package Resources;

import java.util.HashMap;
import java.util.List;

public class JSONBuilder {

    //создание пользователя
    private String email;
    private String password;
    private String name;
    //создание заказа
    private List<String> ingredients = null;
    //Ответ от сервера после создания пользователя
    private Boolean success;
    private HashMap<String,String> user;
    private String accessToken;
    private String refreshToken;
    private String message;
    private HashMap<String,String> order;

    public JSONBuilder(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredents() {
        return ingredients;
    }

    public void setIngredents(List<String> ingredents) {
        this.ingredients = ingredents;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public HashMap<String, String> getUser() {
        return user;
    }

    public void setUser(HashMap<String, String> user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public HashMap<String, String> getOrder() {
        return order;
    }

    public void setOrder(HashMap<String, String> order) {
        this.order = order;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
