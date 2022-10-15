package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi_base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Tc002_GetSingle_Employee_Record extends TestBase {
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void getEmployeeData() throws InterruptedException
	{
		logger.info("****************Started Tc002_Get_Single_Employee_Record************");
		
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/employee/"+empID);
		Thread.sleep(5000);
			
	}
	@Test
	void checkResponseBody() {
		String responseBody= response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empID), true);
	}
	@Test
	void checkStatusCode() {
		int statusCode=response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime()
	{
		long responseTime=response.getTime();
		Assert.assertTrue(responseTime<6000);
	}
	@Test
	void checkContentType() {
		
		String contentType=response.header("Content-Type");
		Assert.assertEquals(contentType,"application/json");
		
	}
	@Test
	void checkstatusLine() {
		String statusLine=response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	@Test
	void checkserverType()
	{
		String serverType=response.header("Server");
		Assert.assertEquals(serverType, "nginx/1.21.6");
	}
	@Test
	void checkContentLength()
	{
		String ContentLength=response.header("Content-Length");
		Assert.assertTrue(Integer.parseInt(ContentLength)<1500);
	}
	@AfterClass
	void tearDown()
	{
		logger.info("**************Finished Tc002_Get_Single_Employee_Record***************");
	}
	
}
