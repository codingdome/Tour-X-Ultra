package at.fhtw.swen2.tourxultra.persistence.repositories;


import at.fhtw.swen2.tourxultra.persistence.entities.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<TourEntity, Long> {

}
