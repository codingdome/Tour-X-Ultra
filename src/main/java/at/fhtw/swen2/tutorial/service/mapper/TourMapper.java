package at.fhtw.swen2.tutorial.service.mapper;

import at.fhtw.swen2.tutorial.persistence.entities.PersonEntity;
import at.fhtw.swen2.tutorial.persistence.entities.TourEntity;
import at.fhtw.swen2.tutorial.service.dto.Tour;
import org.springframework.stereotype.Component;

@Component
public class TourMapper extends AbstractMapper<TourEntity, Tour> {
    @Override
    public Tour fromEntity(TourEntity entity) {
        return Tour.builder().id(entity.getId()).name(entity.getName()).description(entity.getDescription()).type(entity.getType()).origin(entity.getOrigin()).to(entity.getTo()).distance(entity.getDistance()).time(entity.getTime()).information(entity.getInformation()).build();
    }

    @Override
    public TourEntity toEntity(Tour tour) {
        return TourEntity.builder().id(tour.getId()).name(tour.getName()).description(tour.getDescription()).type(tour.getType()).origin(tour.getOrigin()).to(tour.getTo()).distance(tour.getDistance()).time(tour.getTime()).information(tour.getInformation()).build();
    }
}
