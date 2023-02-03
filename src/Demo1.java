import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Demo1 {
    @Test
    void test_01() {
        Response getResult = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println(getResult.getHeaders());
        System.out.println("************************");
        System.out.println(getResult.getStatusCode());
        System.out.println("************************");
        System.out.println(getResult.getBody());
    }

    @Test
    public void test_02() {
        given().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200).
                body("data[0].id", equalTo(7)).
                log().
                all();
    }

    @Test
    public void test_03() {
        Map<String, String> payload = new HashMap<String, String>();
        payload.put("name", "morpheus");
        payload.put("job", "leader");
        JSONObject payloadData = new JSONObject(payload);

        given().
                header("Content-type", "application/json").
                body(payloadData.toJSONString()).
                when().
                post("https://reqres.in/api/users").
                then().
                statusCode(201).
                log().all();
    }

    @Test
    public void test_04() {
        Map<String, String> payload = new HashMap<String, String>();
        payload.put("name", "morpheus");
        payload.put("job", "leader");
        JSONObject payloadData = new JSONObject(payload);

        given().
                header("Content-type", "application/json").
                body(payloadData.toJSONString()).
                when().
                put("https://reqres.in/api/users/2").
                then().
                body("name", equalTo("morpheus")).
                body("job", equalTo("leader")).
                statusCode(200).
                log().all();
    }

    @Test
    public void test_05() {
        given().
                delete("https://reqres.in/api/users/2").
                then().
                statusCode(204).
                log().all();
    }
}
