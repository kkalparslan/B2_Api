package apiTest.day04_jsonpath;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.baseURI;

public class UserWithJ_sonPath {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }
    /**
     * TASK
     * Given accept type is json
     * And Path param user id is 111
     * When user sends a GET request to /allusers/getbyid/{id}
     * Then the status Code should be 200
     * And Content type json should be "application/json; charset=UTF-8"
     * And user's name should be Thomas Eduson
     * And user's id should be 111
     * And user's email should be thomas@test.com
     */
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 111)
                .when()
                .get("/allusers/getbyid/{id}");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");
        JsonPath jsonPath = response.jsonPath();
        int idJson = jsonPath.getInt("id[0]");
        System.out.println("idJson = " + idJson);
        String nameJson = jsonPath.getString("name[0]");
        System.out.println("nameJson = " + nameJson);
        String emailJson = jsonPath.getString("email[0]");
        System.out.println("emailJson = " + emailJson);
        Assert.assertEquals(idJson, 111);
        Assert.assertEquals(nameJson, "Thomas Eduson");
        Assert.assertEquals(emailJson, "thomas@test.com");
    }

    /**
     * TASK
     * Given accept type is json
     * When user sends a GET request to /allusers/alluser
     * Then the status Code should be 200
     * And Content type json should be "application/json; charset=UTF-8"
     * And second name should be isa akyuz
     * And first user's experience jobs should be Junior Developer1, Junior Developer1, Junior Developer
     */

    @Test
    public void isaAkyuzTest() {
        Response response = given().accept(ContentType.JSON)
                .queryParam("pagesize", 5)
                .queryParam("page", 1)
                .when()
                .get("/allusers/alluser/");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");

        JsonPath jsonPath = response.jsonPath();

        String secondName = jsonPath.getString("name[1]");
        List<String> jobs = jsonPath.getList("experience.job[0]"); // Gpathsyntex parantez
        // içinin ismi. Parent dan child a gidiş
        System.out.println("jobs = " + jobs);
        Assert.assertEquals(secondName, "isa akyuz");
        List<String>jobsList=new ArrayList<>();
        jobsList.add("Junior Developer1");
        jobsList.add("Junior Developer1");
        jobsList.add("Junior Developer");
        System.out.println("jobsList = " + jobsList);
        Assert.assertEquals(jobs, jobsList);
    }

    /**
     * TASK
     * Given accept type is json
     * And Path param user id is 111
     * When user sends a GET request to /allusers/getbyid/{id}
     * Then the status Code should be 200
     * And Content type json should be "application/json; charset=UTF-8"
     * Get user skills
     * And user's first skill should be PHP
     */

    @Test
    public void test111() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 111)
                .when()
                .get("/allusers/getbyid/{id}");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");
        JsonPath jsonPath = response.jsonPath();
        String skillsPath=response.path("skills");//
        String skills = jsonPath.getString("skills");
        //List<String>skills=jsonPath.getList("skills");
        System.out.println("skills = " + skills);
        String firstSkill=jsonPath.getString("skills[0][0]");
        System.out.println("firstSkill = " + firstSkill);
    }
}
