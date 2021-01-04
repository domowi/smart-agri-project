package org.acme.rest.json;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PersonEndpointTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/C:/Users/odhiambod/AppData/Local/Programs/Git/")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}