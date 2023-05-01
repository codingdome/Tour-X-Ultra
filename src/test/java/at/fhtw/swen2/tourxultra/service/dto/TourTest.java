package at.fhtw.swen2.tourxultra.service.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TourTest {

    @Test
    void tourBuilderTest() {
        Tour tour = Tour.builder().id(1L).description("description").name("Tour Name").imgUrl("https://img").arrival("München").departure("Frankfurt").popularity(10).childFriendliness(0).transport("Airplane").distance(300).duration(400).build();
        assertEquals(1L, tour.getId());
        assertEquals("description", tour.getDescription());
        assertEquals("München", tour.getArrival());
    }

    @Test
    void tourBuilderIncompleteTest() {
        Tour tour = Tour.builder().name("only name set").build();
        assertNull(tour.getArrival());
        assertEquals("only name set", tour.getName());
    }

    @Test
    void tourBuilderEmptyTest() {
        Tour tour = Tour.builder().build();
        assertNull(tour.getName());
    }
}