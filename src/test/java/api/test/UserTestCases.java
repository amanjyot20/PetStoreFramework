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
	User userPayload;
	@BeforeClass
	public void setUpData()
	{
		faker = new Faker();
		userPayload = new User();
		userPayload.setId(faker.idNumber().hashCode());
		
	//	String name = faker.name().username();
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());	
	}
	@Test(priority=1)
	public void createPostUser()
	{
		Response response= UserEndPoints.createUser(userPayload);
		response.then().log().all()	;
		Assert.assertEquals(response.getStatusCode(),200);
	}
	@Test(priority=2)
	
	public void getUserByName()
	{
		//String name = this.userPayLoad.getUsername();
		Response response=UserEndPoints.readUser("Aman");
		response.then().log().all()	;
		Assert.assertEquals(response.getStatusCode(),200);
	}

@Test(priority=3)

public void updateUserDetails()
{
	userPayload.setUsername(faker.name().firstName());
	userPayload.setUsername(faker.name().lastName());
	userPayload.setUsername(faker.internet().safeEmailAddress());
	
	//String name = this.userPayLoad.getUsername();
	Response response=UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
	response.then().log().all()	;
	Assert.assertEquals(response.getStatusCode(),200);
	
	Response responseAfterupdate=UserEndPoints.readUser(this.userPayload.getUsername());
	response.then().log().all()	;
	Assert.assertEquals(response.getStatusCode(),200);
	
}


@Test(priority=4)

public void deleteUserbyName()
{
	Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
	//response.then().log().all()	;
	Assert.assertEquals(response.getStatusCode(),200);
	
	
}

}