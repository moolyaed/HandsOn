package hands;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class restAssured {
String token;
	@Test
	public void authorization()
	{
		String aut="c2hvcHBpbmdfb2F1dGhfY2xpZW50OnNob3BwaW5nX29hdXRoX3NlY3JldA==";
		RestAssured.baseURI="http://rest-api.upskills.in";
		Response res = given()
				.auth().basic("nash1@vipmail.hu", "password")
				.contentType("application/json")
				//.header("authorization","Basic"+aut)
		.when().post("/api/rest/oauth2/token/client_credentials"+aut)
		.then().extract().response();
		JsonPath path = new JsonPath(res.asString());
		token = path.get("token");
		System.out.println(token);
	}
	
	@Test(enabled=false)
	public void register()
	{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("firstname", "Demo");
		hm.put("lastname", "User");
		hm.put("email", "nash1@vipmail.hu");
		hm.put("password", "password");
		hm.put("confirm", "password");
		hm.put("telephone", "1-541-754-3010");
		hm.put("customer_group_id", "1");
		hm.put("agree", "1");
		
		HashMap<String, Object> m = new HashMap<String, Object>();
		m.put("1", "+364545454");
		
		HashMap<String, Object> h = new HashMap<String, Object>();
		h.put("account", m);
		
		hm.put("custom_field", h);
		
		RestAssured.baseURI="http://rest-api.upskills.in";
		given().queryParam("token", token).body(hm).contentType("application/json")
		.when().post("/api/rest/register")
		.then();
	}
	
	@Test(enabled=false)
	public void login()
	{
		HashMap<String, Object> mm = new HashMap<String, Object>();
		mm.put("email", "demo@test.com");
		mm.put("password", "demo");
		RestAssured.baseURI="http://rest-api.upskills.in";
		given().body(mm).contentType("application/json").queryParam("token", token)
		.when().post("/api/rest/login")
		.then();
		
	}
	
	@Test(enabled=false)
	public void forgot()
	{
		HashMap<String, Object> mh = new HashMap<String, Object>();
		mh.put("email", "test@test.com");
		RestAssured.baseURI="http://rest-api.upskills.in";
		given().body(mh).contentType("application/json").queryParam("token", token)
		.when().post("/api/rest/forgotten")
		.then();
	}
	
	@Test(enabled=false)
	public void getUser()
	{
		RestAssured.baseURI="http://rest-api.upskills.in";
		Response resp = given().queryParam("token", token)
		.when().get("/api/rest/account")
		.then().extract().response();
		System.out.println(resp.toString());
	}
	
	@Test(enabled=false)
	public void update()
	{
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("firstname", "Demo");
		hm.put("lastname", "User");
		hm.put("email", "nash1@vipmail.hu");
		hm.put("telephone", "1-541-754-3010");
		
		HashMap<String, Object> m = new HashMap<String, Object>();
		m.put("1", "+364545454");
		
		HashMap<String, Object> h = new HashMap<String, Object>();
		h.put("account", m);
		
		hm.put("custom_field", h);
		
		RestAssured.baseURI="http://rest-api.upskills.in";
		given().queryParam("token", " ").body(hm).contentType("application/json").queryParam("token", token)
		.when().post("/api/rest/account")
		.then();
	}
	
	@Test(enabled=false)
	public void logout()
	{
		RestAssured.baseURI="http://rest-api.upskills.in";
		given().queryParam("token", token)
		.when().post("/api/rest/logout")
		.then();
	}
	
}
