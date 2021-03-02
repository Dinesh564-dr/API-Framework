package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	static RequestSpecification reqsb;

	public RequestSpecification requestspecification() throws IOException {
		if(reqsb== null) {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		reqsb = new RequestSpecBuilder().setBaseUri(getGlobalvalue("baseURl")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return reqsb;
	}
		return reqsb;
	}
	public static String getGlobalvalue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Gangadhar\\eclipse-workspace\\APIFramework_BDD\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		

	}
	public String getJsonpath(Response response,String key) {
		String res = response.asString();
		JsonPath jsp = new JsonPath(res);
		return jsp.get(key).toString();
	}
}
