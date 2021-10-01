package Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class RestAssuredTest   {

	String placeId = null;
	String newAddress = "Summer Walk, Africa";
	@Test(priority=1)
	public  void createPlace() {
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response=given()
				.log().all()
				.queryParam("key", "qaclick123")
				.header("Content-Type","application/json")
		        .body(payload.AddPlace())
		        .when()
		        .post("maps/api/place/add/json")
		        .then() 
		        .assertThat() 
		        .statusCode(200) 
		        .body("scope", equalTo("APP"))
		        .header("server", "Apache/2.4.18 (Ubuntu)") 
		        .extract().response().asString();
		
		System.out.println(response);
		JsonPath js=new JsonPath(response); //for parsing Json
		 placeId=js.getString("place_id");
		
		System.out.println(placeId);
	}
	
	@Test(priority=2)
	public void updatePlace() {

		
	
	given() 
	.log() 
	.all()
	.queryParam("key", "qaclick123")
	.header("Content-Type","application/json")
	.body("{\r\n" + 
			"\"place_id\":\""+placeId+"\",\r\n" +  
			"\"address\":\""+newAddress+"\",\r\n" + 
			"\"key\":\"qaclick123\"\r\n" + 
			"}")
	.when() 
	.put("maps/api/place/update/json")
	.then()
	.assertThat()
	.log().all()
	.statusCode(200)
	.body("msg", equalTo("Address successfully updated"));
	
}
	@Test(priority=3)
	public void getPlace() {
	
	
	  String getPlaceResponse=	given() 
	    		.log().all() 
	    		.queryParam("key", "qaclick123")
		        .queryParam("place_id",placeId)
		        .when()
		        .get("maps/api/place/get/json")
		        .then()
		        .assertThat()
		        .log().all() 
		        .statusCode(200)
		        .extract().response().asString();
	
	    JsonPath js1=ReUsableMethods.rawToJson(getPlaceResponse);
	    String actualAddress =js1.getString("address");
	    System.out.println(actualAddress);
	    Assert.assertEquals(actualAddress, newAddress);
	
	
	
	
	
	
	}	
	
	
	
	
	
}