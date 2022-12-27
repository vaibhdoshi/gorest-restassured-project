package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {
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

    //Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("size", equalTo(25));
    }

    //Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demittocentum.”
    @Test
    public void test002() {
        response.body("findAll{it.id==2730}.title", hasItem("Ad ipsa coruscus ipsam eos demitto centum."));
    }

    //Check the single user_id in the Array list (5522)
    @Test
    public void test003() {
        List<Integer> userId=response.extract().path("user_id");
        userId.contains("2737");
    }

    // Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test004() {
        response.body("findAll{it.id}", hasItems(2673,2684,2681));
    }

    /* Verify the body of userid =2737 is equal “Demitto tres sit. Catena ver triginta. Super viscus sponte. Cernuus sed tabernus. Iste calcar tumultus. Turpis vinco administratio. Depereo ad vivo. Tendo conitor canonicus. Verus anser cras. Claro deporto succurro. Nihil suspendo volva. Clamo consequatur ut. Taceo degusto hic. Usque et canis. Vinum copiose sustineo. Uterque toties cunae.*/

    @Test
    public void test005() {
        response.body("findAll{it.id==2737}.body", hasItem("Demitto tres sit. Catena ver triginta. Super viscus sponte. Cernuus sed tabernus. Iste calcar tumultus. Turpis vinco administratio. Depereo ad vivo. Tendo conitor canonicus. Verus anser cras. Claro deporto succurro. Nihil suspendo volva. Clamo consequatur ut. Taceo degusto hic. Usque et canis. Vinum copiose sustineo. Uterque toties cunae."));
    }
}
