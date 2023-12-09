package api.endpoints;

import java.util.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class userEndpoints {
	public static Response createUser(User payload)
	{
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(routes.post_url);
			
		return response;
	}
	public static Response readUser(String userName)
	{
		Response response=given()
		.pathParam("username", userName)
		.when()
		.get(routes.get_url);
			
		return response;
	}
	public static Response updateUser(String userName,User payload)
	{
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username", userName)
		.when()
		.put(routes.put_url);
			
		return response;
	}
	public static Response deleteUser(String userName)
	{
		Response response=given()
		
		.pathParam("username", userName)
		.when()
		.delete(routes.delete_url);
			
		return response;
	}


}
