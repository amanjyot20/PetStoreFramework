package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTestCases {
	Faker faker;
	User userPayLoad;
	@BeforeClass
	public void setUpData()
	{
		faker = new Faker();
		userPayLoad = new User();
		userPayLoad.setId(faker.idNumber().hashCode());
		userPayLoad.setUsername(faker.name().username());
		userPayLoad.setUsername(faker.name().firstName());
		userPayLoad.setUsername(faker.name().lastName());
		userPayLoad.setUsername(faker.internet().safeEmailAddress());
		userPayLoad.setUsername(faker.internet().password(5,10));
		userPayLoad.setUsername(faker.phoneNumber().cellPhone());	
	}
	@Test(priority=1)
	public void createPostUser()
	{
		Response response= UserEndPoints.createUser(userPayLoad);
		response.then().log().all()	;
		Assert.assertEquals(response.getStatusCode(),200);
	}
	@Test(priority=2)
	
	public void getUserByName()
	{
		Response response=UserEndPoints.readUser(this.userPayLoad.getUsername());
		//Response response= UserEndPoints.createUser(userPayLoad);
		response.then().log().all()	;
		//response.getStatusCode();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	

}
