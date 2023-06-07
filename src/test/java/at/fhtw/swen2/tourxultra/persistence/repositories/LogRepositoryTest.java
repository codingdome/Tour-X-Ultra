package at.fhtw.swen2.tourxultra.persistence.repositories;

import at.fhtw.swen2.tourxultra.persistence.entities.LogEntity;
import at.fhtw.swen2.tourxultra.persistence.entities.TourEntity;
import at.fhtw.swen2.tourxultra.service.dto.Log;
import at.fhtw.swen2.tourxultra.service.dto.Tour;
import at.fhtw.swen2.tourxultra.service.mapper.LogMapper;
import at.fhtw.swen2.tourxultra.service.mapper.TourMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LogRepositoryTest {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourMapper tourMapper;

    @Autowired
    private LogMapper logMapper;


    @Test
    void saveNewLog() {
        Tour tour = Tour.builder().description("description").name("Tour Name").imgUrl("https://img").arrival("München").departure("Frankfurt").popularity(10).childFriendliness(0).transport("Airplane").distance(300).duration(400).build();
        TourEntity tourEntity = tourMapper.toEntity(tour);
        LogEntity logEntity = LogEntity.builder().comment("comment").date("12345").difficulty(1).duration(1).rating(1).tour(tourEntity).build();

        tourRepository.save(tourEntity);
        logRepository.save(logEntity);
    }

    @Test
    void countLogByTour() {
        Tour tour = Tour.builder().description("description").name("Tour Name").imgUrl("https://img").arrival("München").departure("Frankfurt").popularity(10).childFriendliness(0).transport("Airplane").distance(300).duration(400).build();
        TourEntity tourEntity = tourMapper.toEntity(tour);
        LogEntity logEntity = LogEntity.builder().comment("comment").date("12345").difficulty(1).duration(1).rating(1).tour(tourEntity).build();

        tourRepository.save(tourEntity);
        logRepository.save(logEntity);

        assertEquals(1, logRepository.countByTour(tourEntity));
    }

    @Test
    void getAvgDifficulty() {
        Tour tour = Tour.builder().description("description").name("Tour Name").imgUrl("https://img").arrival("München").departure("Frankfurt").popularity(10).childFriendliness(0).transport("Airplane").distance(300).duration(400).build();
        TourEntity tourEntity = tourMapper.toEntity(tour);

        LogEntity logEntity = LogEntity.builder().comment("comment").date("12345").difficulty(10).duration(1).rating(1).tour(tourEntity).build();
        LogEntity logEntity2 = LogEntity.builder().comment("comment").date("12345").difficulty(100).duration(1).rating(1).tour(tourEntity).build();
        LogEntity logEntity3 = LogEntity.builder().comment("comment").date("12345").difficulty(1000).duration(1).rating(1).tour(tourEntity).build();

        tourRepository.save(tourEntity);
        logRepository.save(logEntity);
        logRepository.save(logEntity2);
        logRepository.save(logEntity3);

        assertEquals(370, logRepository.getAverageDifficultyForTour(tourEntity));
    }

    @Test
    void getAvgDuration() {
        Tour tour = Tour.builder().description("description").name("Tour Name").imgUrl("https://img").arrival("München").departure("Frankfurt").popularity(10).childFriendliness(0).transport("Airplane").distance(300).duration(400).build();
        TourEntity tourEntity = tourMapper.toEntity(tour);

        LogEntity logEntity = LogEntity.builder().comment("comment").date("12345").difficulty(10).duration(10).rating(1).tour(tourEntity).build();
        LogEntity logEntity2 = LogEntity.builder().comment("comment").date("12345").difficulty(100).duration(100).rating(1).tour(tourEntity).build();
        LogEntity logEntity3 = LogEntity.builder().comment("comment").date("12345").difficulty(1000).duration(1000).rating(1).tour(tourEntity).build();

        tourRepository.save(tourEntity);
        logRepository.save(logEntity);
        logRepository.save(logEntity2);
        logRepository.save(logEntity3);

        assertEquals(370, logRepository.getAverageDurationForTour(tourEntity));
    }

    @Test
    void getAvgRating() {
        Tour tour = Tour.builder().description("description").name("Tour Name").imgUrl("https://img").arrival("München").departure("Frankfurt").popularity(10).childFriendliness(0).transport("Airplane").distance(300).duration(400).build();
        TourEntity tourEntity = tourMapper.toEntity(tour);

        LogEntity logEntity = LogEntity.builder().comment("comment").date("12345").difficulty(10).duration(10).rating(10).tour(tourEntity).build();
        LogEntity logEntity2 = LogEntity.builder().comment("comment").date("12345").difficulty(100).duration(100).rating(100).tour(tourEntity).build();
        LogEntity logEntity3 = LogEntity.builder().comment("comment").date("12345").difficulty(1000).duration(1000).rating(1000).tour(tourEntity).build();

        tourRepository.save(tourEntity);
        logRepository.save(logEntity);
        logRepository.save(logEntity2);
        logRepository.save(logEntity3);

        assertEquals(370, logRepository.getAverageRatingForTour(tourEntity));
    }

    @Test
    void findByTour() {
        Tour tour = Tour.builder().description("description").name("Tour Name").imgUrl("https://img").arrival("München").departure("Frankfurt").popularity(10).childFriendliness(0).transport("Airplane").distance(300).duration(400).build();
        TourEntity tourEntity = tourMapper.toEntity(tour);

        LogEntity logEntity = LogEntity.builder().comment("comment").date("12345").difficulty(10).duration(10).rating(10).tour(tourEntity).build();
        LogEntity logEntity2 = LogEntity.builder().comment("comment").date("12345").difficulty(100).duration(100).rating(100).tour(tourEntity).build();
        LogEntity logEntity3 = LogEntity.builder().comment("comment").date("12345").difficulty(1000).duration(1000).rating(1000).tour(tourEntity).build();

        tourRepository.save(tourEntity);
        logRepository.save(logEntity);
        logRepository.save(logEntity2);
        logRepository.save(logEntity3);

        List<LogEntity> results = logRepository.findByTour(tourEntity);
        assertEquals(3, results.size());
    }

}