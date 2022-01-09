package Basics;

/* This is the superclass for any API tests you want to run within this project. It can contain methods and variables to be used in subclasses, i.e. things you need to use in all or most
tests can be added here. Currently a basepath to locate filepaths is added and a method for performing a get request. These
can be reused in any of the API tests in this project
 */

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Base {




    public static String filePath = "src/test/Testdata/";
    public String body;
    public Integer expectedStatusCode = 200;
    public String expectedContentType = "application/json";
    private static ResponseSpecification responseSpec;

    public ResponseSpecification responseSpecifications() {

        responseSpec = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                expectStatusCode(expectedStatusCode).
                build();
        return responseSpec;
    }




    public  void getRequest (String pathParameters){

        RequestSpecification httpRequest = given();
        Response response = httpRequest.get(pathParameters);


        System.out.println("Body: " + response.getBody().asString());
        System.out.println("Statuscode: " + response.statusCode());
        System.out.println("Headers: " + response.headers());

        int statusCode = response.getStatusCode();
        String contentType = response.contentType();
        String body = response.getBody().asString();


        Assertions.assertEquals(expectedContentType, contentType);
        Assertions.assertEquals(expectedStatusCode, statusCode);
        this.body = body;




    }

    public void getRequestGherkin (String pathParameters, String expectedResponse) throws JSONException {

        responseSpecifications();
        Response resp = given().
        when().
                get(pathParameters).

        then().
                assertThat().
                spec(responseSpec).
                extract().response();
                JSONAssert.assertEquals(expectedResponse, resp.getBody().asString(), JSONCompareMode.STRICT);
                System.out.println("Body: " + resp.getBody().asString());
                System.out.println("Statuscode: " + resp.statusCode());
                System.out.println("Headers: " + resp.headers());

    }


    public String getBody() {

        return this.body;

    }

    public void setExpectedStatusCode(int expectedStatusCode) {

        this.expectedStatusCode = expectedStatusCode;



    }

    public void setExpectedContentType(String expectedContentType) {

        this.expectedContentType = expectedContentType;



    }



}
