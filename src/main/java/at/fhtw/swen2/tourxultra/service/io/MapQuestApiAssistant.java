package at.fhtw.swen2.tourxultra.service.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapQuestApiAssistant {
    private static final String MAPQUEST_API_KEY = "WwtPSYRcTQ3d3iNg7PSjfvQmezzV1pQe";
    private static final String DIRECTIONS_API_URL = "http://www.mapquestapi.com/directions/v2/route?key=" + MAPQUEST_API_KEY + "&from=%s&to=%s";
    private static final String STATIC_MAP_API_URL = "http://www.mapquestapi.com/staticmap/v5/map?key=" + MAPQUEST_API_KEY + "&locations=%s,%s|%s,%s&size=600,400";

    private String imageUrl;
    private double distance;
    private int time;

    public void retrieveData(String fromLocation, String toLocation) {
        RestTemplate restTemplate = new RestTemplate();

        // Retrieve distance and time using Directions API
        String directionsUrl = String.format(DIRECTIONS_API_URL, fromLocation, toLocation);
        ResponseEntity<String> response = restTemplate.getForEntity(directionsUrl, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(response.getBody());
                JsonNode routeNode = rootNode.path("route");

                distance = routeNode.path("distance").asDouble();
                time = routeNode.path("time").asInt();

                // Retrieve coordinates for the image
                JsonNode locationsNode = routeNode.path("locations");
                String fromLat = locationsNode.get(0).path("latLng").path("lat").asText();
                String fromLng = locationsNode.get(0).path("latLng").path("lng").asText();
                String toLat = locationsNode.get(1).path("latLng").path("lat").asText();
                String toLng = locationsNode.get(1).path("latLng").path("lng").asText();

                // Retrieve image using Static Map API
                imageUrl = String.format(STATIC_MAP_API_URL, fromLat, fromLng, toLat, toLng);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
