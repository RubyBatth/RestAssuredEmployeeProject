package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi_base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_Delete_Employee_Record extends TestBase {
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void deleteEmployee() throws InterruptedException 
	{
		
		logger.info("*****************Started TC005_Delete_Employee_Record**********");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		
		httpRequest=RestAssured.given();
		
		response=httpRequest.request(Method.GET,"/employees");
		JsonPath jsonpathEvaluator=response.jsonPath();
		
		String empID=jsonpathEvaluator.get("[0].id");
		response=httpRequest.request(Method.DELETE,"/delete/"+empID);
		
		Thread.sleep(3000);
}
	@Test
	void checkResponseBody() {
		String responsebody=response.getBody().asString();
		Assert.assertEquals(responsebody.contains("Successfully! Record has been deleted"),true);
	}
@Test
void checkStatuscode() {
	int statuscode=response.getStatusCode();
	Assert.assertEquals(statuscode,200);
	
}
@Test
void chechStatusLine() {
	String StatusLine=response.getStatusLine();
	Assert.assertEquals(StatusLine,"HTTP/1.1 200 OK");
}
@Test
void checkContentType() {
	String contentType=response.header("Content-Type");
	Assert.assertEquals(contentType,"application/json");
}
@Test
void checkserverType() {
	String serverType=response.header("Server");
	Assert.assertEquals(serverType, "Apache");
	
}

@AfterClass
void tearDown() {
logger.info("*************Finished TC005_Delete_Employee_Record*************");	
}
}