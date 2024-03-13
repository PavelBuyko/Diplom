import Resources.BaseHttpClient;
import io.restassured.response.Response;

public class DeleteApi extends BaseHttpClient {
    public Response doDeleteRequest(String path, String accessToken)
    {
        return super.doDeleteRequest(path,accessToken);
    }

}