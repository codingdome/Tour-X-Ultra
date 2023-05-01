package at.fhtw.swen2.tourxultra.service.mapper;

import at.fhtw.swen2.tourxultra.persistence.entities.TourEntity;
import at.fhtw.swen2.tourxultra.service.dto.Tour;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TourMapperTest {

    TourMapper tourMapper = new TourMapper();

    @Test
    void tourToTourEntityTest() {
        Tour tour = Tour.builder().id(1L).description("description").name("Tour Name").imgUrl("https://img").arrival("München").departure("Frankfurt").popularity(10).childFriendliness(0).transport("Airplane").distance(300).duration(400).build();
        TourEntity tourEntity = tourMapper.toEntity(tour);
        assertEquals("description", tourEntity.getDescription());
        assertEquals("München", tourEntity.getArrival());
    }

    @Test
    void tourEntityToTourTest() {
        TourEntity tourEntity = TourEntity.builder().id(1L).description("description").name("Tour Name").imgUrl("https://img").arrival("München").departure("Frankfurt").popularity(10).childFriendliness(0).transport("Airplane").distance(300).duration(400).build();
        Tour tour = tourMapper.fromEntity(tourEntity);
        assertEquals("description", tour.getDescription());
        assertEquals("München", tour.getArrival());
    }
}