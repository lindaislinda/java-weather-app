import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Homework {
  
    // Nested interface
    public interface MyInterface {
        JSONObject getWeather();
    }
  
    public static void main(String[] args) {
  
        // Create an object of the class implementing the interface
        IWeatherService obj = new IWeatherService();
  
        // Call the method of the interface
        System.out.println(obj.getWeather());
    }
  
    // Class implementing the interface
    public static class IWeatherService implements MyInterface {
        public JSONObject getWeather() {
            String apiKey = "d908669f484525330f748c49c5a11dd7";
            String unit = "imperial";
            String lang = "en";
            String cityName = "Austin";
    
            // Create the URL for the API call
            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName +"&units=" + unit + "&lang=" + lang + "&appid=" + apiKey;
            System.out.println(url);
    
            // Create an HTTP client
            HttpClient httpClient = HttpClient.newHttpClient();
    
            // Create an HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
    
            try {
                // Send the HTTP request and receive the response
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    
                // Check the response status code, 200 = success
                if (response.statusCode() == 200) {
                    //JSONArray JSONArray = new JSONArray(response.body());
                    JSONObject jsonObject = new JSONObject(response.body());
                    //Get the first JSON Object from the array
                    // JSONObject jsonObject = JSONArray.getJSONObject(0);
                    
                    // Print the response body
                    System.out.println(response.body());
                    
                    JSONObject mainObject = new JSONObject(jsonObject, "main");
                    // String temp = mainObject.getString("temp");
                    System.out.println(mainObject);
                    double temperature = mainObject.getJSONObject("main").getDouble("temp");
                    System.out.println(temperature);
                    return mainObject;
                    
                } else {
                    System.out.println("Request failed with status code: " + response.statusCode());
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                
            }
            JSONObject jsonObject = new JSONObject();
                jsonObject.append("key", null);
                return jsonObject;
        }
    }
}
