package api.test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class userTests {
	Faker faker;
	User userpayload;
	public Logger logger;
	@BeforeClass
	public void setup()
	{
		
		faker=new Faker();
		userpayload=new User();
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5, 10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		//logs
		logger =LogManager.getLogger(this.getClass());
	}
	@Test(priority=1)
	public void testPostuser()
	
	{
		logger.info("***** creating user ******");
		Response response=userEndpoints.createUser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("***** created user ******");
	}
	@Test(priority=2)
	public void testGetuser()
	{
		logger.info("***** reading user info******");
		Response response=userEndpoints.readUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("*****  user info displayed******");
	}
	@Test(priority=3)
	public void testUpdateuser()
	{
		
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		Response response=userEndpoints.updateUser(this.userpayload.getUsername(),userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("***** user updated******");
		//after update
		Response responseaftupd=userEndpoints.readUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(responseaftupd.getStatusCode(),200);
		logger.info("***** after updating user ******");
	}
	@Test(priority=4)
	public void testDeluser()
	{
		logger.info("***** deleting user ******");
		Response response=userEndpoints.deleteUser(this.userpayload.getUsername());
		
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("*****  user deleted******");
	}

}
