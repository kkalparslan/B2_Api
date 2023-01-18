package apiTest.day03;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserGetRequestWithPathMethod {

    @BeforeClass
    public void beforeClass(){

        baseURI="https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void testWithPathMethod(){
        Response response=given().accept(ContentType.JSON)
                .pathParam("id", 111)
                .when().log().all()
                .get("/allusers/getbyid/{id}");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");

        //print each value
        System.out.println("response.body().path(\"name\").toString() = " + response.body().path("name").toString());
        System.out.println("response.path(\"id\").toString() = " + response.path("id").toString());
        System.out.println("response.path(\"job\").toString() = " + response.path("job").toString());

        int id=response.path("id[0]");
        String name=response.path("name[0]");
        String jobName=response.path("job[0]");

        Assert.assertEquals(id, 111);
        Assert.assertEquals(name, "Thomas Eduson");
        Assert.assertEquals(jobName, "Developer");

    }

}
