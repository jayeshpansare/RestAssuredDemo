import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Demo3 {

    @Test
    public void test_01() {
        /**
         * Add
         *
         * **/
        Map<String, String> payload = new HashMap<String, String>();
        payload.put("name", "morpheus");
        payload.put("job", "leader");
        JSONObject payloadData = new JSONObject(payload);

        Response response = given().
                header("Content-type", "application/json").
                body(payloadData.toJSONString()).
                when().
                post("https://reqres.in/api/users").
                then().
                statusCode(201).
                log().body()
                .extract().response();

        String getUserId = response.path("id");
        System.out.println(getUserId);
        /**
         * Update user
         *
         * */
        Map<String, String> payloadUpdate = new HashMap<String, String>();
        payload.put("name", "Jayesh");
        payload.put("job", "leader");
        JSONObject payloadUpdateData = new JSONObject(payload);

        given().
                header("Content-type", "application/json").
                body(payloadUpdateData.toJSONString()).
                when().
                put("https://reqres.in/api/users/" + getUserId).
                then().
                body("name", equalTo("Jayesh")).
                body("job", equalTo("leader")).
                statusCode(200).
                log().body();
        /**
         *
         * Delete user
         * **/
        given().
                delete("https://reqres.in/api/users/" + getUserId).
                then().
                statusCode(204).
                log().all();
    }
}
