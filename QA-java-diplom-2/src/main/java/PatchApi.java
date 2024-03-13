import Resources.BaseHttpClient;
import io.restassured.response.Response;

public class PatchApi extends BaseHttpClient {
    public Response doAuthPatchRequest(String path, Object body,String accessToken)
    {
        return super.doAuthPatchRequest(path,body,accessToken);
    }
    public Response doNotAuthPatchRequest(String path, Object body)
    {
        return super.doNotAuthPatchRequest(path,body);
    }

}