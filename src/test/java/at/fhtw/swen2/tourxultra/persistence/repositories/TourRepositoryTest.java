package at.fhtw.swen2.tourxultra.persistence.repositories;

import at.fhtw.swen2.tourxultra.service.dto.Tour;
import at.fhtw.swen2.tourxultra.service.mapper.TourMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TourRepositoryTest {

    @Autowired
    private TourRepository tourRepository;

    TourMapper tourMapper = new TourMapper();

    @Test
    void saveNewTourTest() {
        long current = tourRepository.count();
        Tour tour = Tour.builder().description("description").name("Tour Name").imgUrl("https://img").arrival("München").departure("Frankfurt").popularity(10).childFriendliness(0).transport("Airplane").distance(300).duration(400).build();
        tourRepository.save(tourMapper.toEntity(tour));
        assertEquals(current + 1, tourRepository.count());
    }
}