package at.fhtw.swen2.tourxultra.service.io;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MapQuestApiAssistantTest {

    @Autowired
    MapQuestApiAssistant mapQuestApiAssistant = new MapQuestApiAssistant();

    @Test
    void returnImgUrl() {
        System.out.println(mapQuestApiAssistant.returnImgUrl("Vienna", "Stuttgart"));
    }

    @Test
    void getImage() throws IOException {
        mapQuestApiAssistant.getImage("Wien", "Frankfurt");
    }

    @Test
    void returnImageBytes() throws IOException {
        byte[] bytes = mapQuestApiAssistant.returnImageBytes("Wien", "Frankfurt");
        System.out.println(bytes.length);
        //System.out.println(Arrays.toString(bytes));
    }
}