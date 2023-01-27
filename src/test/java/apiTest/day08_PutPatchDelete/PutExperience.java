package apiTest.day08_PutPatchDelete;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class PutExperience {

    @BeforeClass
    public void beforeClass() {

        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void updateExperience(){

        String expBody="{\n" +
                "  \"job\": \"Product Owner\",\n" +
                "  \"company\": \"Amazon\",\n" +
                "  \"location\": \"USA\",\n" +
                "  \"fromdate\": \"2015-02-02\",\n" +
                "  \"todate\": \"YYYY-MM-DD\",\n" +
                "  \"current\": \"true\",\n" +
                "  \"description\": \"exp. update\"\n" +
                "}";

        Response response=given().accept(ContentType.JSON)
                .queryParam("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMjkzIiwic3RhcnQiOjE2NzQ4Mzg3OTcsImVuZHMiOjE2NzU0NDM1OTd9.muia9E0wSrKEYYzaLqY83d-2ZEbibOc3dHXm5WRl-rfT5EShROp_CH-jZ_kZJDg22e68ncBxd1Xi9X5LUMBr4g")
                .queryParam("id", 244)
                .body(expBody)
                .when().log().all()
                .put("/experience/updateput").prettyPeek();
    }
}
