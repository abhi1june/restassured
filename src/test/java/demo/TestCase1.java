package demo;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestCase1 {
	
	// Weather API - Validate status code & Status line
	
	/*  Test Case 1) Weather API - Validate status code & Status line
	  https://api.openweathermap.org/data/2.5/weather?q=Delhi&appid=05d8bc5ea187326dcdfd26dca298594c
	  
	  {
	"coord": {
		"lon": 77.22,
		"lat": 28.67
	},
	"weather": [{
		"id": 721,
		"main": "Haze",
		"description": "haze",
		"icon": "50n"
	}],
	"base": "stations",
	"main": {
		"temp": 304.18,
		"feels_like": 301.82,
		"temp_min": 302.15,
		"temp_max": 306.48,
		"pressure": 1007,
		"humidity": 25
	},
	"visibility": 3500,
	"wind": {
		"speed": 2.94,
		"deg": 307
	},
	"clouds": {
		"all": 20
	},
	"dt": 1586970636,
	"sys": {
		"type": 1,
		"id": 9165,
		"country": "IN",
		"sunrise": 1586910314,
		"sunset": 1586956594
	},
	"timezone": 19800,
	"id": 1273294,
	"name": "Delhi",
	"cod": 200
}
	  
	  
	  
    */
	
	@Test
	public void testcase1() {
	
		//Specify base URL
		RestAssured.baseURI = "https://api.openweathermap.org/data/2.5/weather";
		
		//Request Object
		RequestSpecification request = RestAssured.given();
		
		//Response Object
		Response response = request.queryParam("q", "Delhi").queryParam("appid", "05d8bc5ea187326dcdfd26dca298594c").request(Method.GET);
	
		
		//Checking status code
		 int statusCode=response.getStatusCode();
		 System.out.println("Status code is: "+statusCode);
		 Assert.assertEquals(statusCode, 200);
		  
		 //Printing the response
		 System.out.println(response.asString());
		 
		 //Checking the Status Line
		 String StatusLine=response.getStatusLine();
		 System.out.println("Status line is "+StatusLine);
		 Assert.assertEquals(StatusLine, "HTTP/1.1 200 OK");
		 
		 //Getting all headers
		 int i=1;
		 Headers headers = response.getHeaders();
		 for(Header header:headers) {
			 System.out.print(i+" Header Name "+header.getName()+"||");
			 System.out.println(" Header Value "+header.getName());
			 i++;
		 }
		 
		 //Check whether city name is Delhi
		 JsonPath jsonPath = response.jsonPath();
		 String cityname=jsonPath.getString("name");
		 System.out.println("City name is "+cityname);
		 Assert.assertEquals(cityname, "Delhi");
		 
		 //Check whether country is IN
		 String countryname=jsonPath.getString("sys.country");
		 System.out.println("Country name is "+countryname);
		 Assert.assertEquals(countryname, "IN");
		
	}
	
	
}
