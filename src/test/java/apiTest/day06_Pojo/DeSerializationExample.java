package apiTest.day06_Pojo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class DeSerializationExample {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void teask1(){

        /**
         * end point==>/allusers/alluser
         * page size=50
         * page=2
         * The company in the 8. users experience part
         * verify this information
         * 1.company -> Ghan IT Com
         * 2.company -> GHAN II IT BV
         */

        Response response = given().accept(ContentType.JSON)
                .queryParam("pagesize", 50)
                .queryParam("page", 2)
                .when().get("/allusers/alluser");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");
         // de- serialization Json to java collection

        List<Map<String, Object>> allUsers=response.body().as(List.class);
        System.out.println(allUsers);

        List<Map<String, Object>> experienceUsers= (List<Map<String, Object>>) allUsers.get(7).get("experience");
        System.out.println(experienceUsers);
        String company1 = (String) experienceUsers.get(0).get("company");
        System.out.println("company1 = " + company1);
        Assert.assertEquals(company1, "Ghan IT Com", "FAILED check company1 name");

        String company2 = (String) experienceUsers.get(1).get("company");
        System.out.println("company2 = " + company2);
        Assert.assertEquals(company2, "GHAN II IT BV", "FAILED check company2 name");
    }
}
