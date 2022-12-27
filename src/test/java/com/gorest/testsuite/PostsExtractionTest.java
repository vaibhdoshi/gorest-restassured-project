package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .get("/posts")
                .then().statusCode(200);
    }

    //Extract the title
    @Test
    public void test001() {
        List<String> title= response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract tle title : " + title);
        System.out.println("------------------End of Test---------------------------");
    }
    // Extract the total number of record
    @Test
    public void test002() {
        int size = response.extract().path("size()");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the total number of record: " +size);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the body of 15th record
    @Test
    public void test003() {
        String body = response.extract().path("[14].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the body of 15th record: " +body);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the user_id of all the records
    @Test
    public void test004() {
        List<String> user_id = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the user_id of all the records: " +user_id);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the title of all the records
    @Test
    public void test005() {
        List<String>title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the title of all the records : " +title);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the title of all records whose user_id = 5585
    @Test
    public void test006() {
        List<HashMap<String,?>> title = response.extract().path("findAll{it.user_id==5585}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the title of all records whose user_id = 5585 : " +title);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the body of all records whose id = 2670
    @Test
    public void test007() {
        List<HashMap<String,?>> body = response.extract().path("findAll{it.id==2670}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the body of all records whose id = 2670 : " +body);
        System.out.println("------------------End of Test---------------------------");
    }


}
