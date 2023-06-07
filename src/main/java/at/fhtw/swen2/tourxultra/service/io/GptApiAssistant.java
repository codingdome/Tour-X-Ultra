package at.fhtw.swen2.tourxultra.service.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
@Transactional
public class GptApiAssistant {
    private static final String GPT_API_ENDPOINT = "https://tour-x-api-bofcsomukq-lm.a.run.app/tour-x";
    private static final String SYSTEM_STRING = "Schreib eine kompakte Liste an einfachen Reise-Möglichkeiten, basierend auf dem User-Input";


    public String requestGptResponse(String city, String transport) {
        String prompt = city + " " + transport;
        String requestBody = "{\"input\":[{\"role\":\"system\",\"content\":\"Schreib einen schnellen Satz für eine einfache Reise-Möglichkeit, basierend auf dem User-Input. Wenn möglich schlage ein Ziel für die Fahrt vor. Maximal 20 Wörter.\"},{\"role\":\"user\",\"content\":\"" + prompt + "\"}]}";
        MediaType mediaType = MediaType.parse("application/json");
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(100, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder().url(GPT_API_ENDPOINT).post(RequestBody.create(requestBody, mediaType)).build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            if (response.isSuccessful()) {
                String jsonResponse = response.body().string();
                System.out.println("API response:\n" + jsonResponse);
                return getContentFromApiResponse(jsonResponse);
            } else {
                System.out.println("API request failed. Response code: " + response.code());
                return "Something went wrong. Please try again.";
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return "Something went wrong. Please try again.";
    }


    public String inspireMeStringTest() {
        return "This will be GPT Inspiration";
    }

    public String inspireMeString() {
        // Construct the input prompt
        String prompt = "Provide a short text about outdoor activity inspiration for a route in Vienna using different types of vehicles like bikes, roller skates, motorcycles, skateboards, etc.";

        // Invoke the GPT API with the input prompt
        String generatedText = invokeGptApi(prompt);

        // Return the generated text
        return generatedText;
    }

    private String invokeGptApi(String prompt) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"input\":[{\"role\":\"system\",\"content\":\"Schreibe kurze Wegbeschreibungen, basierend auf dem User-Input\"},{\"role\":\"user\",\"content\":\"" + prompt + "\"}]}");

        Request request = new Request.Builder().put(body).url(GPT_API_ENDPOINT).post(body).addHeader("Content-Type", "application/json").build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                // Extract the generated text from the API response (assuming the response is in JSON format)
                String generatedText = extractGeneratedTextFromJson(responseBody);
                return generatedText;
            } else {
                // Handle non-successful response
                System.err.println("GPT API request failed with status code: " + response.code());
            }
        } catch (IOException e) {
            // Handle request execution or network errors
            e.printStackTrace();
        }

        return null;
    }

    // Helper method to extract generated text from JSON response
    private String extractGeneratedTextFromJson(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode responseNode = objectMapper.readTree(responseBody);
            JsonNode choicesNode = responseNode.get("choices");
            if (choicesNode != null && choicesNode.isArray() && choicesNode.size() > 0) {
                JsonNode textNode = choicesNode.get(0).get("text");
                if (textNode != null && textNode.isTextual()) {
                    return textNode.asText();
                }
            }
        } catch (IOException e) {
            // Handle JSON parsing error
            e.printStackTrace();
        }

        return null;
    }


    public String testAPICALL() {
        String prompt = "Fahrrad Wien"; // Replace with your actual prompt

        // Prepare the request body
        String requestBody = "{\"input\":[{\"role\":\"system\",\"content\":\"Schreib einen schnellen Satz für eine einfache Reise-Möglichkeit, basierend auf dem User-Input. Wenn möglich schlage ein Ziel für die Fahrt vor. Maximal 20 Wörter.\"},{\"role\":\"user\",\"content\":\"" + prompt + "\"}]}";
        MediaType mediaType = MediaType.parse("application/json");

        // Create the HTTP client and request
        //OkHttpClient client = new OkHttpClient();

        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(100, TimeUnit.SECONDS) // Set the timeout duration (30 seconds in this example)
                .build();

        Request request = new Request.Builder().url(GPT_API_ENDPOINT).post(RequestBody.create(requestBody, mediaType)).build();

        try {
            // Execute the request
            Response response = client.newCall(request).execute();

            System.out.println(response);


            // Check the response
            if (response.isSuccessful()) {
                String jsonResponse = response.body().string();
                System.out.println("API response:\n" + jsonResponse);

                //System.out.println(getContentFromApiResponse(jsonResponse));
                return getContentFromApiResponse(jsonResponse);

                // Handle the JSON response as needed
            } else {
                System.out.println("API request failed. Response code: " + response.code());
                return "Something went wrong. Please try again.";
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        return "Something went wrong. Please try again.";
    }

    public static String getContentFromApiResponse(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode contentNode = rootNode.path("response").path("message").path("content");
            return contentNode.asText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
