package Zippotam;


import Basics.Base;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.tngtech.junit.dataprovider.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static org.hamcrest.Matchers.equalTo;

public class FirstTest extends ZippotamBase {

    String body;

    public FirstTest() throws IOException {
    }

    /*This test uses data in csv format, the pathparameters specifying a country code and zipcode are passed from csv source and being appended to the full URL
    The first value passes the variable i. This variable is used to refer to the correct txt file with the expected response. JSONAssert library is used to compare the
    full response body with the expected response body. This is less laboursome compared to using JSONPath to verify the value of each key
     */
    @ParameterizedTest
    @CsvSource(value = {"1, /us/90210", "2, /us/12345"  })
    public void  requestUszpicode90210_prinEntireBodyAndCompareJSON(Integer i, String pathParameters) throws IOException, JSONException {


        String fileName = filePath + i + "expectedresponse.txt";
        String expectedResponse = new String(readAllBytes(get(fileName)));


        getRequest(pathParameters);



        JSONAssert.assertEquals(expectedResponse, getBody(), JSONCompareMode.STRICT);



        }






// Same test as above with one difference, the testdata is fetched from a csv file.

    @ParameterizedTest
    @CsvFileSource(files ="src/test/Testdata/csvFiles/zipcodes.csv",
            numLinesToSkip = 1)
    public void requestUszpicode90210_prinEntireBodyAndCompareJSONDataFromCSV(String i, String pathParameters) throws IOException, JSONException {


        String fileName = filePath + i + "expectedresponse.txt";
        String expectedResponse = new String(readAllBytes(get(fileName)));

        getRequestGherkin(pathParameters, expectedResponse);
        //JSONAssert.assertEquals(expectedResponse,getRequest(pathParameters), JSONCompareMode.STRICT);






    };





     }







