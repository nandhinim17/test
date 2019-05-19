package com.dlg.deop.stepDefinition;


import com.dlg.deop.testAPIs.RtsMockApiTest;
import com.dlg.deop.utilities.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class rtsValidation extends RtsMockApiTest {

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
        /*DocumentIdentifier = jsPath.get("id_str");
        System.out.println("The response.path: " + DocumentIdentifier);*/
    }

    @Test
    public void post_xml_test() {
        Response response = given().
                contentType(ContentType.XML)
                .accept(ContentType.XML)
                .body(RtsMockApiTest.buildRtsRequest("Short", 31))
                .when()
                .post("https://sdhsgud0rg.execute-api.eu-west-2.amazonaws.com/uat/rts");
        System.out.println("POST Response\n" + response.asString());
        // tests
        Assert.assertEquals(response.getStatusCode(),201);
        response.then().body("Response.Metadata.DocumentIdentifier", Matchers.is("31"));
        /*response.then().body("empResponse.name", Matchers.is("PK"));*/

        XmlMapper xmlMapper = new XmlMapper();
        com.dlg.deop.artifacts.rtsMockResPackage.Response rtsResponse = null;
        com.dlg.deop.artifacts.rtsMockReqPackage.Request rtsReueset = null;
        com.dlg.deop.artifacts.rtsMockReqPackage.Request rtsReueset1 = null;

        try {
            //convert to java class
            rtsReueset = RtsMockApiTest.buildRtsRequest("123", 41);
            String reqXml = xmlMapper.writeValueAsString(rtsReueset);
            System.out.println("reqXml: " + reqXml);
            rtsReueset1 = xmlMapper.readValue(reqXml, com.dlg.deop.artifacts.rtsMockReqPackage.Request.class);
            String reqXml1 = xmlMapper.writeValueAsString(rtsReueset1);
            System.out.println("reqXml1: " + reqXml1);
            rtsResponse = xmlMapper.readValue(response.asString(), com.dlg.deop.artifacts.rtsMockResPackage.Response.class);
            String resp1 = xmlMapper.writeValueAsString(rtsResponse);
            System.out.println("resp1: " + resp1);
            System.out.println("rtsResponse.getMetaData: " + rtsResponse.getMetadata().toString());
            /*response.then().body(String.valueOf(rtsResponse.getMetadata().getDocumentIdentifier()), Matchers.is("2"));
            System.out.println("rtsResponse.getMetadata().getDocumentIdentifier(): " + rtsResponse.getMetadata().getDocumentIdentifier());*/
        } catch (Exception e) {
            e.printStackTrace();
            try {
                System.out.println("New XML: " + xmlMapper.writeValueAsString(rtsResponse));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }
}
