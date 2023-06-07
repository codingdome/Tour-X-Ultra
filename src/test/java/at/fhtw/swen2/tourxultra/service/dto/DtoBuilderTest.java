package at.fhtw.swen2.tourxultra.service.dto;

import at.fhtw.swen2.tourxultra.service.dto.Log;
import at.fhtw.swen2.tourxultra.service.dto.SumTour;
import at.fhtw.swen2.tourxultra.service.dto.Tour;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DtoBuilderTest {

    @Test
    void logBuilderTest() {
        Log log = Log.builder().id(1L).tour(null).date("23/12/1996").comment("Ein Kommentar").duration(3000).difficulty(5).rating(2).build();
        assertEquals(1L, log.getId());
        assertEquals("Ein Kommentar", log.getComment());
    }

    @Test
    void logBuilderIncompleteTest() {
        Log log = Log.builder().date("23/12/1996").comment("Ein Kommentar").duration(3000).difficulty(5).rating(2).build();
        assertNull(log.getId());
        assertNull(log.getTour());
    }

    @Test
    void logBuilderEmptyTest() {
        Log log = Log.builder().build();
        assertNull(log.getId());
    }

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

    @Test
    void sumTourBuilderTest() {
        Tour tour = Tour.builder().id(1L).description("description").name("Tour Name").imgUrl("https://img").arrival("München").departure("Frankfurt").popularity(10).childFriendliness(0).transport("Airplane").distance(300).duration(400).build();
        SumTour sumTour = SumTour.builder().tour(tour).avgDifficulty(1.1).avgDuration(1.1).avgRating(1.1).build();
        assertEquals("München", sumTour.getTour().getArrival());
    }

    @Test
    void sumTourBuilderIncompleteTest() {
        SumTour sumTour = SumTour.builder().avgDifficulty(1.1).avgDuration(1.1).avgRating(1.1).build();
        assertNull(sumTour.getTour());
    }

    @Test
    void sumTourBuilderEmptyTest() {
        SumTour sumTour = SumTour.builder().build();
        assertNull(sumTour.getTour());
    }

}
