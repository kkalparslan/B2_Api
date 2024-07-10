package apiTest.day06_Pojo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;

public class POJO_Deserialization {
    @BeforeClass
    public void beforeClass(){
        baseURI="https://petstore.swagger.io/v2";
    }
    /**
      {
          "id": 9222968140497198741,
          "username": "Jake23",
          "firstName": "Jake",
          "lastName": "Master",
          "email": "jake@gmail.com",
          "password": "Test1234",
          "phone": "55512345",
          "userStatus": 21
      }
     */

    @Test
    public void oneUserPetUser(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and()
                .pathParam("username", "Jake23")
                .when()
                .get("/user/{username}");
        System.out.println("response.statusCode() = " + response.statusCode());

        //json to our petStore object:
        PetStoreUser oneUser=response.body().as(PetStoreUser.class);

        // print all information:
        System.out.println("oneUser.getId() = " + oneUser.getId());
        System.out.println("oneUser.getUsername() = " + oneUser.getUsername());
        System.out.println("oneUser.getFirstname() = " + oneUser.getFirstName());
        System.out.println("oneUser.getLastname() = " + oneUser.getLastName());
        System.out.println("oneUser.getEmail() = " + oneUser.getEmail());
        System.out.println("oneUser.getPassword() = " + oneUser.getPassword());
        System.out.println("oneUser.getPhone() = " + oneUser.getPhone());
        System.out.println("oneUser.getUserStatus() = " + oneUser.getUserStatus());
        // verify all information:

        Assert.assertEquals(oneUser.getId(), 9.2229681404971991E18);
        Assert.assertEquals(oneUser.getUsername(), "Jake23");
        Assert.assertEquals(oneUser.getFirstName(), "Jake");
        Assert.assertEquals(oneUser.getLastName(), "Master");
        Assert.assertEquals(oneUser.getEmail(), "jake@gmail.com");
        Assert.assertEquals(oneUser.getPassword(), "Test1234");
        Assert.assertEquals(oneUser.getPhone(), "55512345");
        Assert.assertEquals(oneUser.getUserStatus(), 21);
    }

//    @Test
//    public void oneUserPetStore1(){
//        Response response = RestAssured.given().accept(ContentType.JSON)
//                .and()
//                .pathParam("username", "Jake23")
//                .when()
//                .get("/user/{username}");
//        System.out.println("response.statusCode() = " + response.statusCode());
//
//        //JSON to our petStore object
//        PetStoreUser oneUser=response.body().as(PetStoreUser.class);
//
//        //print all information
//        System.out.println("oneUser.getId() = " + oneUser.getId());
//        System.out.println("oneUser.getUsername() = " + oneUser.getUsername());
//        System.out.println("oneUser.getFirstname() = " + oneUser.getFirstname());
//        System.out.println("oneUser.getLastname() = " + oneUser.getLastname());
//        System.out.println("oneUser.getEmail() = " + oneUser.getEmail());
//        System.out.println("oneUser.getPassword() = " + oneUser.getPassword());
//        System.out.println("oneUser.getPhone() = " + oneUser.getPhone());
//        System.out.println("oneUser.getUserStatus() = " + oneUser.getUserStatus());
//
//        //verify all information
//    }
}
