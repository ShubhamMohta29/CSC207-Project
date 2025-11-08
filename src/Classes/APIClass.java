package Classes;

// Required imports for reading from API
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class APIClass {

    public static final String API_URL = "https://api.api-ninjas.com/v1/animals?name="; //final because we do not want these two variable values to change
    private static final String API_KEY = "yenuWSGz+AZ0FMtDkHfNbw==jJFN4ywYAj6fJZhh";

    private List<String> searchedAnimals;
    private String responseBody;

    public APIClass() {
        searchedAnimals = new ArrayList<>(); // do not need any code in this, for now. Just used to instantiate the class.
        responseBody = "";
    }

    public String getAnimalData(String animalName) {
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create(API_URL + animalName)).header("X-Api-Key", API_KEY).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                responseBody = response.body();
                searchedAnimals.add(responseBody);
                return responseBody;
            } else {
                return "Error"; // need a better way to show there's an error that the GUI can use
            }
        } catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }

    public int numResults(){
        JSONArray animalsArray = new JSONArray(responseBody);
    }
}
