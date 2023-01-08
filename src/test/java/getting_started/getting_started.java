package getting_started;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class getting_started {

    @Test
    public void simpleGetRequest() {

        given().
                baseUri("https://restcountries.com/v3.1/name").
                when()
                .get("/USA").
                then()
                .statusCode(200);
    }

    @Test
    public void validate_Json_response() {

        given().
                baseUri("https://restcountries.com/v3.1").
                when()
                .get("/name/USA").
                then()
                .statusCode(200).body(
                        "name.official[0]",equalTo("United States of America"),
                "name.nativeName.eng[0].common",equalTo("United States"),
                "idd.suffixes[0]",hasItem("201")

                );
    }

    @Test
    public void extract_responseData(){
        Response res = given().
                baseUri("https://restcountries.com/v3.1/name").
                when()
                .get("/USA").
                then().extract().response();
        System.out.println(res.asString());

    }

    @Test
    public void singleValue(){
        String value = given().
                baseUri("https://restcountries.com/v3.1/name").
                when()
                .get("/USA").
                then().extract().path("demonyms.eng.f[0]");
        System.out.println(value + "value");

    }

    @Test
    public void verify_statusLine(){
        given().
                baseUri("https://restcountries.com/v3.1/name").
                when()
                .get("/USAd")
                .then().statusLine("HTTP/1.1 404 Not Found");
        System.out.println();
    }
}
