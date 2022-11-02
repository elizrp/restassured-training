import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class Application {

    final static String baseUrl = "https://api.publicapis.org/";
    final static String postUrl = "https://dummy.restapiexample.com/api/v1/create";

    public static void main(String[] args) {
//        getResponseBodyWithQueryParameters();
//        getResponseBodyWithoutQueryParameters();
//        verifyResponseStatusCodeValid();
//        verifyResponseStatusCodeInvalid();
//        getResponseBodyWithRequestHeader();
//        createPostRequest();
    }


    /**
     * Get a random entry from a query with parameters.
     */
    public static void getResponseBodyWithQueryParameters() {
        given().queryParam("title", "Pixela")
                .queryParam("auth", "X-Mashape-Key")
                .queryParam("https", "true")
                .when().get(baseUrl + "random").then().log()
                .body();
    }

    /**
     * Get all categories from a query with no parameters.
     */
    public static void getResponseBodyWithoutQueryParameters() {
        get(baseUrl + "categories").then().log().body();
    }

    /**
     * Get a random entry by sending a request with header.
     */
    public static void getResponseBodyWithRequestHeader() {
        given().header("Cache-Control", "no-cache").contentType("application/json")
                .when().get(baseUrl + "random").then().log().body();
    }

    /**
     * Verify response code from valid endpoint is 200 OK.
     */
    public static void verifyResponseStatusCodeValid() {
        given().when().get(baseUrl + "categories")
                .then().statusCode(200);
    }

    /**
     * Verify response code from non-existing endpoint is 404 NOT FOUND.
     */
    public static void verifyResponseStatusCodeInvalid() {
        given().when().get(baseUrl + "something")
                .then().statusCode(404);
    }

    /**
     * Create a new employee using a POST request and pass body as:
     * 1) json String
     * 2) Employee object
     */
    public static void createPostRequest() {

        // Create an object to use as request body
        Employee employee = new Employee("John Smith", 1230, 23);

        // Create a string to use as request body
        String json = "{\"name\":\"John Smith\",\"salary\":\"1230\",\"age\":\"23\"}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(employee)
                .when()
                .post(postUrl)
                .then()
                .statusCode(HttpStatus.SC_OK).extract().response();

        // Print the value of the name field from the response
        System.out.println(response.getBody().path("data.name").toString());
    }

    /**
     * Backup method for creating a POST request to another url in case the other one stops working.
     */
    public static void createPostRequest2() {

        // Create a string to use as request body
        String json = "{\"forward_url\":\"https://myservice.example.com/events-collector\",\"proxy_response\":false,\"insecure_tls\":false,\"expand_path\":true,\"capacity\": 250}";

        given().contentType(ContentType.JSON).body(json)
                .when()
                .post("https://rbaskets.in/api/baskets/hello")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }
}