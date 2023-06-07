package at.fhtw.swen2.tourxultra.service.io;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MapQuestApiAssistantTest {

    @Autowired
    MapQuestApiAssistant mapQuestApiAssistant = new MapQuestApiAssistant();
    
    @Test
    void returnImgUrl() {
        System.out.println(mapQuestApiAssistant.returnImgUrl("Vienna", "Stuttgart"));
    }
}