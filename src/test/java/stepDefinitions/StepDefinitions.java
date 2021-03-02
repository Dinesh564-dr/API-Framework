package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestdataBuild;
import resources.Utils;

public class StepDefinitions extends Utils {
	RequestSpecification res;
	ResponseSpecification resbul;
	Response response;
	public static String place_id;
	TestdataBuild tdb = new TestdataBuild();

	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {

		res = given().spec(requestspecification()).body(tdb.addPlacePayload(name, language, address));
	}

	@When("user calls {string} with {string} HTTP request")
	public void user_calls_with_http_request(String resources, String method) {
		APIResources apiresourse = APIResources.valueOf(resources);
		System.out.println(apiresourse.getResource());
		resbul = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (method.equalsIgnoreCase("POST")) {
			response = res.when().post(apiresourse.getResource());

		} else if (method.equalsIgnoreCase("GET")) {
			response = res.when().get(apiresourse.getResource());
		}

	}

	@Then("Verify the response cose is {int}")
	public void verify_the_response_cose_is(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String status, String Expstatusval) {

		assertEquals(getJsonpath(response, status), Expstatusval);

	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String nameexp, String resources) throws IOException {
		 place_id = getJsonpath(response, "place_id");
		res = given().spec(requestspecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resources, "GET");

		String actname = getJsonpath(response, "name");
		assertEquals(actname, nameexp);

	}
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
		res= given().spec(requestspecification()).body(tdb.deleteplacePayload(place_id));
	}
}
