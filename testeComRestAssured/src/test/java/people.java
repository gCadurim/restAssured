import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class people {

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "https://swapi.dev/api";
    }

    @Test
    public void testPeopleStarWars(){
        RestAssured.defaultParser = Parser.JSON;
     Response response =  given()
             .get("/people/1/")
             .then()
             .contentType(ContentType.JSON)
             .statusCode(200)
             .extract()
             .response();

        String nameRoot = response.jsonPath().getString("name");
       assertEquals("Luke Skywalker", nameRoot);
    }

    @Test
    public void testPeopleStarWarsError(){
        RestAssured.defaultParser = Parser.JSON;
        Response response =  given()
                .header("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .get("/people/1/")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .extract()
                .response();

        String nameRoot = response.jsonPath().getString("name");
        assertNotEquals("Luki Skywal", nameRoot);
    }
}
