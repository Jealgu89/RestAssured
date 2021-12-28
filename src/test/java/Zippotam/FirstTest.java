package Zippotam;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import com.tngtech.junit.dataprovider.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static org.hamcrest.Matchers.equalTo;

public class FirstTest extends ZippotamBase {


    public FirstTest() throws IOException {
    }




    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills() {



        given().
        when().
        get("/us/90210").
        then().
        assertThat().
        body("places[0].'place name'", equalTo("Beverly Hills"));


    }


    //parameterized test using CsvSource
    //@ParameterizedTest
    //@CsvSource(value = "/us/90210", )




    @ParameterizedTest
    @CsvSource(value = {"1, /us/90210", "2, /us/12345"  })
    public void  requestUszpicode90210_prinEntireBodyAndCompareJSON(Integer i, String pathParameters) throws IOException, JSONException {

        //int i = 1;
        String fileName = filePath + i + "expectedresponse.txt";
        String expectedResponse = new String(readAllBytes(get(fileName)));


        //String pathParameters = "/us/90210";

        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get(pathParameters);
        ResponseBody body = response.getBody();

        String actualResponse = body.asString();
        System.out.println(body.asString());

        JSONAssert.assertEquals(expectedResponse,actualResponse, JSONCompareMode.STRICT);





        };


// parameterize with file

    @ParameterizedTest
    @CsvFileSource(files ="src/test/Testdata/csvFiles/zipcodes.csv",
            numLinesToSkip = 1)
    public void requestUszpicode90210_prinEntireBodyAndCompareJSONDataFromCSV(String i, String pathParameters) throws IOException, JSONException {


        String fileName = filePath + i + "expectedresponse.txt";
        String expectedResponse = new String(readAllBytes(get(fileName)));




        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get(pathParameters);
        ResponseBody body = response.getBody();

        String actualResponse = body.asString();
        System.out.println(body.asString());

        JSONAssert.assertEquals(expectedResponse,actualResponse, JSONCompareMode.STRICT);





    };





     }







