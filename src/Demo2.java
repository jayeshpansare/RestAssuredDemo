import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Demo2 {
    @Test
    public void test_01() {
        given().
                get("https://reqres.in/api/users?page=2").
                then().
                assertThat().
                body(matchesJsonSchemaInClasspath("../RestAssuredDemo/target/classes/dataFiles/test.json"));
    }
}