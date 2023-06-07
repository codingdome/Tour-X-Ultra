package at.fhtw.swen2.tourxultra.persistence.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EntityBuilderTest {

    @Test
    void tourEntityBuilderTest() {
        TourEntity tour = TourEntity.builder().id(1L).description("description").name("Tour Name").imgUrl("https://img").arrival("München").departure("Frankfurt").popularity(10).childFriendliness(0).transport("Airplane").distance(300).duration(400).build();
        assertEquals(1L, tour.getId());
        assertEquals("description", tour.getDescription());
        assertEquals("München", tour.getArrival());
    }

    @Test
    void tourEntityBuilderIncompleteTest() {
        TourEntity tour = TourEntity.builder().name("only name set").build();
        assertNull(tour.getArrival());
        assertEquals("only name set", tour.getName());
    }

    @Test
    void tourEntityBuilderEmptyTest() {
        TourEntity tour = TourEntity.builder().build();
        assertNull(tour.getName());
    }

    @Test
    void logEntityBuilderTest() {
        LogEntity logEntity = LogEntity.builder().comment("comment").date("date").difficulty(1).duration(1).rating(1).tour(null).build();
        assertEquals(1, logEntity.getRating());
        assertEquals(1, logEntity.getDuration());
        assertNull(logEntity.getTour());
    }

    @Test
    void logEntityBuilderIncompleteTest() {
        LogEntity logEntity = LogEntity.builder().comment("comment").build();
        assertEquals("comment", logEntity.getComment());
        assertNull(logEntity.getTour());
    }

    @Test
    void logEntityBuilderEmptyTest() {
        LogEntity logEntity = LogEntity.builder().build();
        assertNull(logEntity.getTour());
    }
}
