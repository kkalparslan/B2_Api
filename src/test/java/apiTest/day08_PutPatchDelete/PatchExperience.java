package apiTest.day08_PutPatchDelete;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class PatchExperience {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void patchExperience() {
        String body = "{\n" +
                "  \"job\": \"SDET\",\n" +
               // "  \"company\": \"Amazon\",\n" +
               // "  \"location\": \"TR\",\n" +
                "  \"fromdate\": \"2015-02-02\",\n" +
                "  \"todate\": \"2020-11-11\",\n" +
                "  \"current\": \"false\",\n" +
                "  \"description\": \"exp. update\"\n" +
                "}";

        Response response=given().accept(ContentType.JSON)
                .pathParam("id", 244)
                .queryParam("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMjkzIiwic3RhcnQiOjE2NzQ4Mzg3OTcsImVuZHMiOjE2NzU0NDM1OTd9.muia9E0wSrKEYYzaLqY83d-2ZEbibOc3dHXm5WRl-rfT5EShROp_CH-jZ_kZJDg22e68ncBxd1Xi9X5LUMBr4g")
                .body(body)
                .when().log().all()
                .patch("/experience/updatepatch/{id}").prettyPeek();
        assertEquals(response.statusCode(), 200);
    }
}
