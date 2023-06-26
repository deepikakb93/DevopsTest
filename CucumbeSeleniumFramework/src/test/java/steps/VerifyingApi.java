package steps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.json.JSONArray;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import io.cucumber.core.cli.Main;
import io.cucumber.core.gherkin.messages.internal.gherkin.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.core.options.CurlOption.HttpMethod;
public class VerifyingApi {



	private String apiUrl;
	private int statusCode;
	private String firstName;
	
	
	@Given("the API endpoint {string}")
	public void setApiUrl(String apiUrl) {
	this.apiUrl = apiUrl;
	    }

	@When("a GET request is sent")
	public void sendGetRequest() throws Exception {
	// Create an HttpClient instance
	CloseableHttpClient httpClient = HttpClients.createDefault();

	// Create an HTTP GET request object with the API URL
	HttpGet request = new HttpGet(apiUrl);

	// Send the GET request
	HttpResponse response = httpClient.execute(request);

	// Get the response status code
	statusCode = response.getStatusLine().getStatusCode();
	System.out.println(statusCode);
	System.out.println(response.getStatusLine());
	// Close the HttpClient
	httpClient.close();
	    }

	@Then("the response status code should be {int}")	
	public void verifyStatusCode(int expectedStatusCode) throws ClientProtocolException, IOException {
	// Verify the response status code
		System.out.println(expectedStatusCode);
	Assertions.assertEquals(expectedStatusCode, statusCode);
	
	}
	
	@Then("the response should not contain the name {string}")
	public void the_response_should_not_contain_the_name(String firstName) throws ClientProtocolException, IOException {
		this.firstName = firstName;
		// Create an HTTP GET request object with the API URL
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet request = new HttpGet(apiUrl);

		// Send the GET request
		HttpResponse response = httpClient.execute(request);
		
		HttpEntity entity = response.getEntity();
		String responseBody = EntityUtils.toString(entity);

		// Parse the response body as JSON
		JSONObject responseJson = new JSONObject(responseBody);
		JSONArray data = responseJson.getJSONArray("data");

		// Check if any user has a first name with "Jack"
		boolean containsJack = false;
	    for (int i = 0; i < data.length(); i++) {
	        JSONObject user = data.getJSONObject(i);
	        firstName = user.getString("first_name");
	        if ("Jack".equals(firstName)) {
	            containsJack = true;
	            break;
	        }
	    }

		// Assert that no user has a first name with "Jack"
		Assertions.assertFalse(containsJack); 
		    }
	
	@Then("the response should return empty data array")
	public void the_response_should_return_empty_data_array() {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// Create an HTTP GET request object with the API URL
		HttpGet request = new HttpGet(apiUrl);

		// Send the GET request
		HttpResponse response;
		try {
			response = httpClient.execute(request);
			HttpEntity entity=response.getEntity();
			String response_entity= EntityUtils.toString(entity);
			String [] responseKeys= {"page","per_page","total","total_pages","data","support"};
			ObjectMapper mapper = new ObjectMapper();
			Map<String,Object> responseData = mapper.readValue(response_entity,Map.class);
            
            Iterator<String> key   = responseData.keySet().iterator();

            while(key.hasNext()){
              String aKey   = key.next();
              Object  aValue = responseData.get(aKey);
              
            Assertions.assertTrue(responseData.containsKey(aKey));
              
              if(aKey.equals("data")) {
            	 ArrayList data=(ArrayList) aValue;
            	 Assertions.assertTrue(data.isEmpty());
            	 
              }
            }
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
