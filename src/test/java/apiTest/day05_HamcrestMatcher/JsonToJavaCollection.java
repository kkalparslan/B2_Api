package apiTest.day05_HamcrestMatcher;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.*;

public class JsonToJavaCollection {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void userToMap(){
        Response response=given().accept(ContentType.JSON)
                .when().get("https://demoqa.com/Account/v1/User/11%22");
        Assert.assertEquals(response.statusCode(), 401);
        Map<String, Object>jsonMap=response.body().as(Map.class);
        System.out.println("jsonMap = " + jsonMap);

        // verify message
        String message= (String) jsonMap.get("message");
        System.out.println("message = " + message);
        Assert.assertEquals(message, "User not authorized!");

        // verify code
        String code= (String) jsonMap.get("code");
        System.out.println("code = " + code);
        Assert.assertEquals(code, "1200");
    }

    @Test
    public void allUsersToMap(){
        Response response=given().accept(ContentType.JSON)
                .queryParam("pagesize", 50)
                .queryParam("page", 1)
                .when().get("/allusers/alluser");
        Assert.assertEquals(response.statusCode(), 200);
        // we need to de-serialiaze Json response to java collection
        // birden fazla json body bulundurduğundan list of map yapmamız gerekiyor
        List<Map<String, Object>>allUserMap=response.body().as(List.class);
        System.out.println("allUserMap = " + allUserMap);

        //2. kullanıcının adını assert ediyoruz
        System.out.println("name = " + allUserMap.get(1).get("name"));
        String name = (String) allUserMap.get(1).get("name");
        Assert.assertEquals(name, "isa akyuz");

        System.out.println(allUserMap.get(0).get("skills"));
        List<String>skills= (List<String>) allUserMap.get(0).get("skills");
        Assert.assertEquals(skills.get(0), "PHP");
        System.out.println(skills);

        List<Map<String, Object>>experienceListMap= (List<Map<String, Object>>) allUserMap.get(0).get("experience");
        System.out.println(experienceListMap);
        System.out.println(experienceListMap.get(2).get("job"));
    }
}
