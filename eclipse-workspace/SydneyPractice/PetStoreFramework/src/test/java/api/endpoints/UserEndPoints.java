package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.response.Response;

import io.restassured.http.ContentType;

//UserEndPoints.java class is created to perform CRUD-Create Retrieve Update Delete
public class UserEndPoints {

	public static Response createUser(User payload)
	{
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_url);
		//.then()
		return response;
		
	}
	public static Response readUser(String userName)
	{
		Response response=given()
		.pathParam("username", userName) 
	.when()
		.get(Routes.get_url);
	//.then()
	return response;
	}
	
	public static Response updateUser(String userName, User payload)
	{
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName) 
			.body(payload)
		.when()
			.post(Routes.update_url);
		//.then()
		return response;
	}
	public static Response deleteUser(String userName)
	{
		Response response=given()
				.pathParam("username", userName) 
			.when()
				.get(Routes.delete_url);
		return response;
	}
}
