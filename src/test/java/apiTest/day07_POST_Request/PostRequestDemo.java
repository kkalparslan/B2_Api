package apiTest.day07_POST_Request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class PostRequestDemo {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void postNewUser(){
        String jsonBody="{\n" +
                "  \"name\": \"Azra1\",\n" +
                "  \"email\": \"azra1@krafttechexlab.com\",\n" +
                "  \"password\": \"467\",\n" +
                "  \"about\": \"student\",\n" +
                "  \"terms\": \"3\"\n" +
                "}";

        Response response=given().accept(ContentType.JSON)// hey api send me body as json format
                .and()
                .contentType(ContentType.JSON) //hey api I am sending json body
                .body(jsonBody)
                .when().post("/allusers/register");
        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));
    }

    @Test
    public void postNewUser2(){
        Map<String,Object>requestMap=new HashMap<>();
        requestMap.put("name", "Azra2");
        requestMap.put("email", "azra2@krafttechexlab.com");
        requestMap.put("password", "4444");
        requestMap.put("about", "structer");
        requestMap.put("terms", "3");
        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON) //serialization
                .and().body(requestMap)
                .when()
                .post("/allusers/register");
        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));
    }

    @Test
    public void postNewUser3(){
        NewUserInfo newUserInfo=new NewUserInfo();
        newUserInfo.setName("azra3");
        newUserInfo.setEmail("azra3@krafttechexlab.com");
        newUserInfo.setPassword("azra3");
        newUserInfo.setAbout("student");
        newUserInfo.setTerms("4");

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON) //serialization
                .and().body(newUserInfo)
                .when()
                .post("/allusers/register");
        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));
    }

    @Test
    public void postNewUser4(){
        NewUserInfo newUserInfo=new NewUserInfo("azra4", "azra4@krafttechexlab.com",
                "azra4", "student", "5");
        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON) //serialization
                .and().body(newUserInfo)
                .when()
                .post("/allusers/register");
        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));
    }
}
