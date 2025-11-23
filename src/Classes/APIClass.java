package Classes;

// Required imports for reading from API
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import Config.ProjectConfig;
import org.json.JSONArray;
import org.json.JSONObject;

public class APIClass {

    public static final String API_URL = ProjectConfig.NINJA_API_URL; //final because we do not want these two variable values to change
    private static final String API_KEY = ProjectConfig.getNinjaApiKey();

    private List<String> searchedAnimals;
    private String responseBody;

    public APIClass() {
        searchedAnimals = new ArrayList<>(); // do not need any code in this, for now. Just used to instantiate the class.
        responseBody = "";
    }

    public String getAnimalData(String animalName) {
        try{
            System.out.println("this is the animal name i received: "+ animalName);
            HttpClient client = HttpClient.newHttpClient();
            animalName = animalName.replace(" ", "%20");
            HttpRequest request = HttpRequest.newBuilder(URI.create(API_URL + animalName)).header("X-Api-Key", API_KEY).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                responseBody = response.body();

                //needed this check for filter logic
                if (responseBody == null || responseBody.trim().isEmpty() || responseBody.equals("[]")) {
                    System.err.println("API returned empty data for: " + animalName);
                    return null; // Return null instead of empty array
                }
                searchedAnimals.add(responseBody);
                System.out.println("I did get something with the ninja api and this is what: " + responseBody);
                return responseBody;
            } else {
                //return "Error"; // need a better way to show there's an error that the GUI can use
                System.out.println("API Error: " + response.statusCode() + " for: " + animalName);
                return null; // Return null for errors
            }
        } catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }

    public int numResults(){
        JSONArray animalsArray = new JSONArray(responseBody);
        //return animalsArray.length();
        if (animalsArray == null)
            return 0;
        return animalsArray.length();
    }
}