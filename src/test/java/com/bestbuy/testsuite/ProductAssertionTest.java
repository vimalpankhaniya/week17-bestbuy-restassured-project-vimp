package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    //  Verify the if the total is equal to 51957

    @Test
    public void test001() {
        response.body("total", equalTo(51957));
    }

    //Verify the if the stores of limit is equal to 10

    @Test
    public void test002() {
        response.body("limit", equalTo(10));
    }


    //. Check the single ‘Name’ in the Array list (Duracell - AAA Batteries (4-Pack)
    @Test
    public void test003() {
        response.body("data.name", hasItem("Duracell - AAA Batteries (4-Pack)"));
    }
//Check the multiple ‘Names’ in the ArrayList (Duracell - AA 1.5V CopperTop Batteries (4-
//Pack), Duracell - AA Batteries (8-Pack), Energizer - MAX Batteries AA (4-Pack))

    @Test
    public void test004() {
        response.body("data.name", hasItems("(Duracell - AA 1.5V CopperTop Batteries (4-\n" +
                "Pack), Duracell - AA Batteries (8-Pack), Energizer - MAX Batteries AA (4-Pack)"));
    }


    //Verify the productId=150115 inside categories of the third name is “Household Batteries”
    @Test
    public void test005() {

        response.body("data[3].categories[2]", hasEntry("name", "Household Batteries"));
    }

    //Verify the categories second name = “Housewares” of productID = 333179
    @Test
    public void test006() {
        response.body("data[8].categories[1]", hasEntry("name", "Housewares"));
    }

    // Verify the price = 4.99 of forth product
//    @Test
//    public void test007() {
//        response.body("data[3].price",equalTo("4.99"));
//
//    }


//. Verify the Product name = Duracell - D Batteries (4-Pack) of 6th product

    @Test
    public void test008(){

        response.body("data[5]",hasEntry("name","Duracell - D Batteries (4-Pack)"));
    }

    //. Verify the ProductId = 333179 for the 9th product

    @Test
    public void test009(){

    response.body("data[8]",hasEntry("id",333179));
    }

//Verify the prodctId = 346575 have 5 categories
@Test
    public void test010(){

    response.body("data[9].categories",hasSize(5));

    }

}









