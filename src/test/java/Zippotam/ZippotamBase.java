package Zippotam;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.basePath;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;


public class ZippotamBase {


    String filePath = "src/test/Testdata/";


    public ZippotamBase() throws IOException {
    }


    @BeforeAll
    public static void  basicsettings() {

    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    baseURI = "http://zippopotam.us";
    //basePath = "/us";

    RestAssured.requestSpecification = new RequestSpecBuilder().
            setContentType(ContentType.JSON).
            build();

    RestAssured.responseSpecification = new ResponseSpecBuilder().
            expectContentType(ContentType.JSON).
            build();

    }
}

