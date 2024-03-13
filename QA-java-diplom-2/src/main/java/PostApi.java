import Resources.BaseHttpClient;
import io.restassured.response.Response;

public class PostApi extends BaseHttpClient {
    public Response doPostRequest(String path, Object body)
    {
        return super.doPostRequest(path,body);
    }
    public Response doAuthPostRequest(String path, Object body,String accessToken) {return super.doAuthPostRequest(path,body,accessToken);}

}