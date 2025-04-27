package api.test;
import org.testng.annotations.Test;
import com.github.javafaker.*;
import api.*;
import api.payload.*;
import api.utilities.DataProviders;
public class DDTests {
	
	@Test(priority = 1, dataProvider = "Data", dataProviderClass= DataProviders.class)
	public void createPostUser(String userId, String userName, String fname,String lname, String useremail, String pwd, String ph)
	{
	 User userPayload = new User();
	 userPayload.setId(Integer.parseInt(userId));	
	 userPayload.setUsername(userName);
	 userPayload.setFirstName(fname);
	 userPayload.setLastName(lname);
	 userPayload.setEmail(useremail);
	 userPayload.setPassword(pwd);
	 userPayload.setPhone(ph);	
	}
}