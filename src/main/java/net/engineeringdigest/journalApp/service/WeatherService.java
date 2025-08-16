package net.engineeringdigest.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import net.engineeringdigest.journalApp.apiresponse.WeatherResponse;

@Component
public class WeatherService {

	private static final String apiKey = "4a3356225909ce6a3bc06f85d2b6b6b8";
	private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public WeatherResponse getWeather(String city)
	{
		String finalAPi = API.replace("CITY", city).replace("API_KEY", apiKey);
		ResponseEntity<WeatherResponse> response =  restTemplate.exchange(finalAPi, HttpMethod.GET,null,WeatherResponse.class);
		WeatherResponse body = response.getBody();
		return body;
		
				
	}
}
