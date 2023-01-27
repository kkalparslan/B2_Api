package apiTest.day05_HamcrestMatcher;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class HamcrestMatcher {
    /**
     * kendi içerisinde assert methodları var. ayrıca testNG methodları kullanmaya gerek yok
     */

    @BeforeClass
    public void beforeClass() {

        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }
    /**
     *         given accept type is json
     *         And path param id is 111
     *         When user sends a get request to /allusers/getbyid/{id}
     *         Then status code should be 200
     *         And content type should be application/json; charset=UTF-8
     *         And json data has following:
     *          "id"= 111
     *          "name"= "Thomas Eduson"
     *          "job"="Developer"
     */
    @Test
    public void oneUserWithHamcrest(){
        given().accept(ContentType.JSON)
                .and().pathParam("id", 111)
                .when().get("/allusers/getbyid/{id}")

                .then().statusCode(200)
                .and().assertThat().contentType("application/json; charset=UTF-8")
                .and().body("id[0]", equalTo(111),"name[0]", equalTo("Thomas Eduson")
                ,"job[0]", equalTo("Developer"));
    }
    /**
     *          given accept type is json
     *         And query param pagesize is 50
     *         And query param page is 1
     *         When user sends a get request to /allusers/alluser
     *         Then status code should be 200
     *         And content type should be application/json; charset=UTF-8
     *         And response header content-length should be 8653
     *         And response header server should be Apache/2
     *         And response headers has Date
     *         And json data should have "GHAN","aFm","Sebastian"
     *         And json data should have "QA" for job
     *         And json data should have "Junior Developer1" for first user's experience job
     */
    @Test
    public void allUsersWithHamcrest(){
        given().accept(ContentType.JSON)
                .and().queryParam("pagesize", 50)
                .and().queryParam("page", 1)
                .when().log().all() // burdaki log().all() requestteki sonuçları getiriyor
                .when().get("/allusers/alluser")
                .then().assertThat().statusCode(200)
                .assertThat().contentType(equalTo("application/json; charset=UTF-8"))
                .and().assertThat().header("content-length", equalTo("8653"))
                .and().assertThat().header("server", equalTo("Apache/2"))
                .and().assertThat().headers("Date", notNullValue())
                .body("name", hasItems("GHAN","aFm","Sebastian"))
                .body("job", hasItem("QA"))
                .body("experience.job[0]", hasItems("Junior Developer1"))
                .body("name[0]", equalTo("MercanS"))
                .log().all(); // burdaki log().all() responstaki sonuçları getiriyor
    }
}
