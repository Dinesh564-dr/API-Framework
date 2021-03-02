package resources;

import java.io.IOException;

import io.cucumber.java.Before;
import stepDefinitions.StepDefinitions;

public class Hooks {

	@Before("@Deleplace")
	public void beforeScenario() throws IOException {
		StepDefinitions sd=new StepDefinitions();
		if(StepDefinitions.place_id==null) {
		sd.add_place_payload_with("rama", "maths", "asia");
		sd.user_calls_with_http_request("AddplaceAPI", "POST");
		sd.verify_place_id_created_maps_to_using("rama","getPlaceAPI");
	}
	}

}