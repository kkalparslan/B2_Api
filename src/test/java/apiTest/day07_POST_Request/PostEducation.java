package apiTest.day07_POST_Request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.hamcrest.Matchers.*;


public class PostEducation {

    @BeforeClass
    public void beforeClass() {

        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void postNewUser(){

        NewUserInfo newUserInfo=new NewUserInfo("azram", "azram@krafttechexlab.com",
                "azram1", "student", "5");
        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON) //serialization
                .and().body(newUserInfo)
                .when()
                .post("/allusers/register");
        assertEquals(response.statusCode(), 200);
        response.prettyPrint();
        String token=response.path("token");

        String educationBody="{\n" +
                "  \"school\": \"Krafttech\",\n" +
                "  \"degree\": \"BootCamp\",\n" +
                "  \"study\": \"QA/SDET\",\n" +
                "  \"fromdate\": \"2021-01-01\",\n" +
                "  \"todate\": \"2022-01-01\",\n" +
                "  \"current\": \"false\",\n" +
                "  \"description\": \"SDET BootCamp\"\n" +
                "}";
        response=given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(educationBody)
                .queryParam("token", token)
                .when().post("/education/add");

        assertEquals(response.statusCode(), 200);
        response.prettyPrint();
    }

    @Test
    public void postNewUserAndVerify(){
        String name="azra9";
        String email="azra9@krafttechexlab.com";
        String password="azra1234";
        String about="about me";
        String terms="3";

        Map<String, Object>requestMap=new HashMap<>();
        requestMap.put("name", name);
        requestMap.put("email", email);
        requestMap.put("password", password);
        requestMap.put("about", about);
        requestMap.put("terms", terms);

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON) //serialization
                .and().body(requestMap)
                .when()
                .post("/allusers/register");
        assertEquals(response.statusCode(), 200);
        //response.prettyPrint();
        String token=response.path("token");
       // String userid=response.path("id");
        Map<String,Object>educationBody=new HashMap<>();
        educationBody.put("school", "Krafttech");
        educationBody.put("degree", "BootCamp");
        educationBody.put("study", "QA/SDET");
        educationBody.put("fromdate", "2021-01-01");
        educationBody.put("todate", "2022-01-01");
        educationBody.put("current", "false");
        educationBody.put("description", "SDET BootCamp");

        response=given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(educationBody)
                .queryParam("token", token)
                .when().post("/education/add");
        response.prettyPrint();
        assertEquals(response.statusCode(), 200);

        //verify body
        int id=response.path("id");
        response=given().accept(ContentType.JSON)
                .and()
                .queryParam("token", token) // swaggerda querry yada headre olarak değişebiliyor. dikkat edilmeli
                .pathParam("id", id)
                .when()
                .get("/education/getbyid/{id}");
        response.prettyPrint();
        assertEquals(response.statusCode(), 200);

        //verify with path
       // System.out.println("userid = " + userid);
        System.out.println("educationid = " + id);
        //assertEquals(response.path("userid"), userid);
        assertEquals(response.path("school"), "Krafttech");

        //verify using hamcrest matcher
        given().accept(ContentType.JSON)
                .and()
                .queryParam("token", token) // swaggerda querry yada headre olarak değişebiliyor. dikkat edilmeli
                .pathParam("id", id)
                .when()
                .get("/education/getbyid/{id}")
                .then()
                .assertThat()
                .body("school", equalTo("Krafttech"),
                        "study", equalTo("QA/SDET")).log().all();
    }
}
