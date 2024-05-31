package apiTest.day06_Pojo;

import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.util.Map;

public class GsonTest {
    /**
      [
        {
          "id": 0,
          "username": "miky",
          "firstName": "mike",
          "lastName": "masters",
          "email": "mike@gmail.com",
          "password": "Test1234",
          "phone": "string",
          "userStatus": 0
        }
      ]
     */

    @Test
    public void jsonToMap(){
        Gson gson=new Gson();
        String myJsonBody= "{\n" +
                "    \"id\": 9222968140497198741,\n" +
                "    \"username\": \"Jake23\",\n" +
                "    \"firstName\": \"Jake\",\n" +
                "    \"lastName\": \"Master\",\n" +
                "    \"email\": \"jake@gmail.com\",\n" +
                "    \"password\": \"Test1234\",\n" +
                "    \"phone\": \"55512345\",\n" +
                "    \"userStatus\": 21\n" +
                "}";

        System.out.println("myJsonBody = " + myJsonBody);

        // gson converting to map
        Map<String, Object>dataMap=gson.fromJson(myJsonBody, Map.class);
        System.out.println("dataMap = " + dataMap);

        // gson converting to object class
        PetStoreUser oneUser=gson.fromJson(myJsonBody,PetStoreUser.class);
        System.out.println("oneUser = " + oneUser);

        //Serialization
        //Java collection or POJO to Json
        PetStoreUser petStoreUser=new PetStoreUser(11,"Jake2",
                "Jakyl","Masters","jaky@gmail.com",
                "Test1234","55512345",22);

        String jsonUser=gson.toJson(petStoreUser);
        System.out.println("jsonUser = " + jsonUser);
    }
}
