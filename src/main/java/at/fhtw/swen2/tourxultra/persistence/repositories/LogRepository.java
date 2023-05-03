package at.fhtw.swen2.tourxultra.persistence.repositories;


import at.fhtw.swen2.tourxultra.persistence.entities.LogEntity;
import at.fhtw.swen2.tourxultra.persistence.entities.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LogRepository extends JpaRepository<LogEntity, Long> {

    List<LogEntity> findByTour(TourEntity tourEntity);

    void deleteByTour(TourEntity tourEntity);

    Long countByTour(TourEntity tourEntity);

    @Query("SELECT AVG(l.difficulty) FROM LogEntity l")
    Double getAverageDifficulty();

    @Query("SELECT AVG(l.duration) FROM LogEntity l")
    Double getAverageDuration();

    @Query("SELECT COALESCE(AVG(l.duration), 0) FROM LogEntity l WHERE l.tour = :tour")
    Double getAverageDurationForTour(@Param("tour") TourEntity tour);

    @Query("SELECT COALESCE(AVG(l.rating), 0) FROM LogEntity l WHERE l.tour = :tour")
    Double getAverageRatingForTour(@Param("tour") TourEntity tour);

    @Query("SELECT COALESCE(AVG(l.difficulty), 0) FROM LogEntity l WHERE l.tour = :tour")
    Double getAverageDifficultyForTour(@Param("tour") TourEntity tour);
}
