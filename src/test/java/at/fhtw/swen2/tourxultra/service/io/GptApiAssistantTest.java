package at.fhtw.swen2.tourxultra.service.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class GptApiAssistantTest {

    GptApiAssistant gptApiAssistant = new GptApiAssistant();

    @Test
    void testApiCallDefault() {
        System.out.println(gptApiAssistant.inspireMeString());
    }

    @Test
    void testApiCall2() {
        System.out.println(gptApiAssistant.testAPICALL());
    }

    @Test
    void requestImplementationApiWienFahrradTest() {
        String city = "Wien";
        String transport = "Fahrrad";
        String response = gptApiAssistant.requestGptResponse(city, transport);
        //assertTrue(response.contains("Wien"));
        //assertTrue(response.contains("Fahrrad"));
    }

    @Test
    void requestImplementationApiFrankfurtAutoTest() {
        String city = "Frankfurt";
        String transport = "Auto";
        String response = gptApiAssistant.requestGptResponse(city, transport);
    }
}