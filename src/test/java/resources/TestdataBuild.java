package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Addplace;
import pojo.Location;

public class TestdataBuild {
	public Addplace addPlacePayload(String name, String language, String address) {
		pojo.Addplace adpl = new pojo.Addplace();

		adpl.setAccuracy(50);
		adpl.setAddress(address);
		adpl.setLanguage(language);
		adpl.setName(name);
		adpl.setPhone_number("(+91) 983 893 3937");
		adpl.setWebsite("http://google.com");

		List<String> list = new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		adpl.setTypes(list);

		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		adpl.setLocation(loc);
		return adpl;
	}

	public String deleteplacePayload(String placeid) {
		return "{\r\n    \"place_id\":\""+placeid+"\"\r\n}";
	}
}
