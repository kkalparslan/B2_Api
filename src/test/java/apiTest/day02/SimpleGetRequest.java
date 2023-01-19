package apiTest.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleGetRequest {

    String petStoreUrl="https://petstore.swagger.io/v2";
    String exlabUrl="https://www.krafttechexlab.com/sw/api/v1";

    @Test
    public void test1(){
        Response response = RestAssured.get(petStoreUrl+"/store/inventory");

        // print status code
        System.out.println("response.statusCode() = " + response.statusCode());

        //print body
        response.prettyPrint();
    }

    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(petStoreUrl + "/store/inventory");

        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertEquals(response.statusCode(), 200);
        //response.prettyPrint();
        Assert.assertEquals(response.contentType(), "application/json");
    }

    @Test
    public void test3(){
        // verify test case with using RestAssured Library
        RestAssured.given().accept(ContentType.JSON)
                .when().get(petStoreUrl+"/store/inventory")
                .then()
                .assertThat().statusCode(200)
                .and()
                .contentType("application/json");
    }

    @Test
    public void test4(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(petStoreUrl + "/store/inventory");
        Assert.assertEquals(response.statusCode(), 200);
        //System.out.println("response.body().asString() = " + response.body().asString());
        //{"guarding":2,"sold":9,"string":546,"pending":10,"available":402,"not available":1,
        // "active":12,"good":1,"totvs1":1}

        Assert.assertTrue(response.body().asString().contains("sold"));

    }



}
