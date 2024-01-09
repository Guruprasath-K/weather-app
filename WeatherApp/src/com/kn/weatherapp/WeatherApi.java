package com.kn.weatherapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;


public class WeatherApi {

	public static void main(String[] args) {
		String apiKey = "eeee2a0cd335a304dadb893c4d132fd7";
		String city = "London"; // Replace with the desired city name

		try {
			// Build the URL for the OpenWeatherMap API
			String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;
			URL url = new URL(apiUrl);

			// Create an HTTP connection
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// Read the response
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;

			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();

			// Parse the JSON response
			JSONObject jsonResponse = new JSONObject(response.toString());

			// Extract and print relevant weather information
			String cityName = jsonResponse.getString("name");
			JSONObject main = jsonResponse.getJSONObject("main");
			double temperature = main.getDouble("temp");
			int humidity = main.getInt("humidity");

			System.out.println("Weather in " + cityName + ":");
			System.out.println("Temperature: " + temperature + " K");
			System.out.println("Humidity: " + humidity + "%");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
