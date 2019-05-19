package com.dlg.deop.stepDefinition;

import com.dlg.deop.testAPIs.rtsMockApiTest;
import com.dlg.deop.artifacts.*;
import com.dlg.deop.utilities.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class rtsValidation extends rtsMockApiTest {

    RequestSpecification reqSpec;
    ResponseSpecification resSpec;

    @BeforeClass
    public void setup() {
        reqSpec = RestUtilities.getRequestSpecification();
        reqSpec.basePath(Path.ENVIRONMENT);

        resSpec = RestUtilities.getResponseSpecification();

    }

    @Test
    public void postTweet() {
        Response response =
                given()
                        .spec(RestUtilities.createQueryParam(reqSpec, "status", "My First Tweet"))
                        .when()
                        .post(EndPoint.RTSENDPOINT)
                        .then()
                        .spec(resSpec)
                        .extract()
                        .response();
        JsonPath jsPath = RestUtilities.getJsonPath(response);
        DocumentIdentifier = jsPath.get("id_str");
        System.out.println("The response.path: " + DocumentIdentifier);
    }
}
