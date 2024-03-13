package Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public abstract class BaseHttpClient {

    private RequestSpecification baseRequestSpec()
    {
        return new RequestSpecBuilder()
                .setBaseUri(BaseURI.BASEURI)
                .addHeader("Content-Type", "application/json")
                .setRelaxedHTTPSValidation()
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new ErrorLoggingFilter())
                .build();
    }
    //Этот спек нужен для добавления хедера с токеном авторизации, которые используют в запросах к эндпоинту auth/user
    private RequestSpecification baseAuthRequestSpec(String accessToken)
    {
        return new RequestSpecBuilder()
                .setBaseUri(BaseURI.BASEURI)
                .addHeader("authorization",accessToken)
                .setRelaxedHTTPSValidation()
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new ErrorLoggingFilter())
                .build();
    }
    private RequestSpecification baseAuthAndBodyRequestSpec(String accessToken)
    {
        return new RequestSpecBuilder()
                .setBaseUri(BaseURI.BASEURI)
                .addHeader("Content-Type", "application/json")
                .addHeader("authorization",accessToken)
                .setRelaxedHTTPSValidation()
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new ErrorLoggingFilter())
                .build();
    }

    protected Response doAuthGetRequest(String path,String accessToken)
    {
        return given()
                .spec(baseAuthRequestSpec(accessToken))
                .get(path)
                .thenReturn();
    }
    protected Response doNotAuthGetRequest(String path)
    {
        return given()
                .spec(baseRequestSpec())
                .get(path)
                .thenReturn();
    }
    protected Response doPostRequest(String path, Object body)
    {
        return given()
                .spec(baseRequestSpec())
                .body(body)
                .post(path)
                .thenReturn();
    }
    protected Response doAuthPostRequest(String path, Object body,String accessToken)
    {
        return given()
                .spec(baseAuthAndBodyRequestSpec(accessToken))
                .body(body)
                .post(path)
                .thenReturn();
    }
    protected Response doDeleteRequest(String path,String accessToken)
    {
        return given()
                .spec(baseAuthRequestSpec(accessToken))
                .delete(path)
                .thenReturn();
    }

    protected Response doAuthPatchRequest(String path, Object body,String accessToken)
    {
        return given()
                .spec(baseAuthAndBodyRequestSpec(accessToken))
                .body(body)
                .patch(path)
                .thenReturn();
    }
    protected Response doNotAuthPatchRequest(String path, Object body)
    {
        return given()
                .spec(baseRequestSpec())
                .body(body)
                .patch(path)
                .thenReturn();
    }

}