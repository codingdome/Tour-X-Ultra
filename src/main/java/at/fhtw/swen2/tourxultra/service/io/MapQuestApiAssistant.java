package at.fhtw.swen2.tourxultra.service.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapQuestApiAssistant {
    private static final String MAPQUEST_API_KEY = "WwtPSYRcTQ3d3iNg7PSjfvQmezzV1pQe";
    private static final String DIRECTIONS_API_URL = "http://www.mapquestapi.com/directions/v2/route?key=" + MAPQUEST_API_KEY + "&from=%s&to=%s";
    private static final String STATIC_MAP_API_URL = "http://www.mapquestapi.com/staticmap/v5/map?key=" + MAPQUEST_API_KEY + "&locations=%s,%s|%s,%s&size=600,400";

    private static final String MAPQUEST_API_URL_2 = "https://www.mapquestapi.com/staticmap/v5/map?start=%s&end=%s&size=%d,%d@2x&format=png&key=%s";

    private String imageUrl;
    private double distance;
    private int time;

    public void retrieveData(String fromLocation, String toLocation) {
        RestTemplate restTemplate = new RestTemplate();

        //String url = String.format(MAPQUEST_API_URL_2, fromLocation, toLocation, 500, 170, MAPQUEST_API_KEY);

        // Retrieve distance and time using Directions API
        String directionsUrl = String.format(DIRECTIONS_API_URL, fromLocation, toLocation);

        ResponseEntity<String> response = restTemplate.getForEntity(directionsUrl, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            try {

                System.out.println(response);

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

    public String returnImgUrl(String fromLocation, String toLocation) {
        return String.format(MAPQUEST_API_URL_2, fromLocation, toLocation, 1200, 400, MAPQUEST_API_KEY);
    }

    public byte[] returnImageBytes(String origin, String destination) throws IOException {
        return getImageBytes(origin, destination);
    }

    public Image getImage(String origin, String destination) throws IOException {
        byte[] imageBytes = getImageBytes(origin, destination);
        Image image = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            image = new Image(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    private byte[] getImageBytes(String origin, String destination) throws IOException {
        String apiURL = "https://www.mapquestapi.com/staticmap/v5/map?start=%s&end=%s&size=%d,%d@2x&format=png&key=%s";
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(apiURL, origin, destination, 1200, 400, MAPQUEST_API_KEY);
        byte[] imageBytes = restTemplate.getForObject(url, byte[].class);

        FileOutputStream fos = new FileOutputStream("map.png");
        fos.write(imageBytes);
        fos.close();
        return imageBytes;
    }


}