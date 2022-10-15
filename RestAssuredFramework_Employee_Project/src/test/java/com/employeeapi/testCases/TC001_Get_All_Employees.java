package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi_base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Employees extends TestBase {

	@BeforeClass
	void getAllEmployees() throws InterruptedException {
		logger.info("************Started TC001_Get_All_Employees**********");
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/employees");
		
		Thread.sleep(5000);
	}
	
@Test
void checkResponseBody()
{
	logger.info("*******************Checking Response Body ********************");
	
	String responsebody= response.getBody().asString();
	logger.info("Response Body==>"+responsebody);
	Assert.assertTrue(responsebody!=null);
}

@Test
void checkStatusCode()
{
	logger.info("*****************Checking Status Code******************");
	
	int statuscode= response.getStatusCode();
	logger.info("Status code is ==>"+statuscode);
	Assert.assertEquals(statuscode, 200);
}

@Test
void checkResponseTime()
{
	logger.info("**************** Checking Response Time **************");
			
long responseTime=response.getTime();
logger.info("Response Time is ==>"+responseTime);


if(responseTime>2000)
logger.warn("Response Time is greater than 2000");
Assert.assertTrue(responseTime<2000);
}
@Test
void checkstatusLine()
{
logger.info("************Checking Status Line *************");	
String statusLine= response.getStatusLine();
logger.info("Status Line is ==>"+statusLine);
Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");

}
@Test
void checkContentType() {
	
logger.info("*************Checking Content Type *****************");

String contentType=response.header("Content-Type");
logger.info("Content type is=>"+contentType);
Assert.assertEquals(contentType, "application/json");
}
@Test
void checkserverType() {
	logger.info("*************** checking server Type***********");
	
	String serverType=response.header("Server");
	logger.info("Server Type is =>"+serverType);
		Assert.assertEquals(serverType, "nginx/1.21.6");
	
	}
@Test
 void checkcontentEncoding() {
	logger.info("**************** Checking Content Encoding***********");
	String contentEncoding= response.header("Content-Encoding");
	logger.info("Content Lenght is==>"+contentEncoding);
	Assert.assertEquals(contentEncoding, "gzip");
}
/*@Test
void CheckContentLength() throws InterruptedException  {
	logger.info("**************** Checking Content Length***********");
	
	
	
String ContentLength= response.header("Content-Length");
	logger.info("Content Length is==>"+ContentLength);
	if(Integer.parseInt(ContentLength)<500)
	logger.warn("Content Length is less than 500");
	Assert.assertTrue(Integer.parseInt(ContentLength)>1000);
}*/
@Test
void checkCookies() {
	logger.info("**************Checking Cookies*************");
	String cookie= response.getCookie("PHPSESSID")	;
	}
@AfterClass
void tearDown() {
	logger.info("**********************Finished TC001_Get_All_Employees*********");
	
}
}
