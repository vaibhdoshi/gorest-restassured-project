package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users")
                .then().statusCode(200);
    }

    // Extract the All Ids
    @Test
    public void test001() {
        List<Integer> ids = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the All Ids : " + ids);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the all Names
    @Test
    public void test002() {
        List<String> names = response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the all Names : " + names);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the name of 5th object
    @Test
    public void test003() {
        String name = response.extract().path("[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the name of 5th object : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List<HashMap<String, ?>> objectNames = response.extract().path("findAll{it.status=='inactive'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the names of all object whose status = inactive: " + objectNames);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<HashMap<String, ?>> gender = response.extract().path("findAll{it.status=='active'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the gender of all the object whose status = active: " + gender);
        System.out.println("------------------End of Test---------------------------");
    }

    // Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<HashMap<String, ?>> femaleName = response.extract().path("findAll{it.gender=='female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Print the names of the object whose gender = female: " + femaleName);
        System.out.println("------------------End of Test---------------------------");
    }

    // Get all the emails of the object where status = inactive
    @Test
    public void test007() {
        List<HashMap<String, ?>> email = response.extract().path("findAll{it.status=='inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the emails of the object where status = inactive : " + email);
        System.out.println("------------------End of Test---------------------------");
    }

    // Get the ids of the object where gender = male
    @Test
    public void test008() {
        List<HashMap<String, ?>> maleIds = response.extract().path("findAll{it.gender=='male'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the gender of all the object whose gender=female: " + maleIds);
        System.out.println("------------------End of Test---------------------------");
    }

    // Get all the status
    @Test
    public void test009() {
        List<HashMap<String, ?>> status = response.extract().path("findAll{it.status}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the status: " + status);
        System.out.println("------------------End of Test---------------------------");
    }

    // Get email of the object where name = Karthik Dubashi IV
    @Test
    public void test010() {
        List<String> email = response.extract().path("findAll{it.name=='Vimala Kakkar'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get email of the object where name =Vimala Kakkar : " + email);
        System.out.println("------------------End of Test---------------------------");
    }

    // Get gender of id = 5313
    @Test
    public void test011() {
        List<String>genderId = response.extract().path("findAll{it.id==5313}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get gender of id = 5313: " + genderId);
        System.out.println("------------------End of Test---------------------------");
    }


}
