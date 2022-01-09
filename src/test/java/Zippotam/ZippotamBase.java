package Zippotam;

import Basics.Base;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

import static java.nio.file.Paths.get;


/* This class' this is the superclass for any tests with the Zippotam API. It extends from the baseClass class. Currently
this class only contains a baseURI, if the Zippotam API would be extended with more pathparameters it is possible to use
this baseURI in different classes with test, i.e. if the baseURI would need to be changed you can do that in this class
and it will work in all testclasses.
 */

public class ZippotamBase extends Base {

    private static RequestSpecification requestSpecs;







    @BeforeAll
    public static void  basicsettings() {

    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    baseURI = "http://zippopotam.us";







    }



    }



