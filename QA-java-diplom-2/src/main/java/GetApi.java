import Resources.BaseHttpClient;
import io.restassured.response.Response;

public class GetApi extends BaseHttpClient {
    public Response doAuthGetRequest(String path, String accessToken)
    {
        return super.doAuthGetRequest(path,accessToken);
    }
    public Response doNOTAuthGetRequest(String path)
    {
        return super.doNotAuthGetRequest(path);
    }

}