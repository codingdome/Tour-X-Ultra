package at.fhtw.swen2.tourxultra.service.mapper;

import at.fhtw.swen2.tourxultra.persistence.entities.TourEntity;
import at.fhtw.swen2.tourxultra.service.dto.Tour;
import org.springframework.stereotype.Component;

@Component
public class TourMapper extends AbstractMapper<TourEntity, Tour> {

    @Override
    public Tour fromEntity(TourEntity entity) {
        return Tour.builder().id(entity.getId()).name(entity.getName()).description(entity.getDescription()).departure(entity.getDeparture()).arrival(entity.getArrival()).transport(entity.getTransport()).distance(entity.getDistance()).duration(entity.getDuration()).imgUrl(entity.getImgUrl()).popularity(entity.getPopularity()).childFriendliness(entity.getChildFriendliness()).build();
    }

    @Override
    public TourEntity toEntity(Tour tour) {
        return TourEntity.builder().id(tour.getId()).name(tour.getName()).description(tour.getDescription()).departure(tour.getDeparture()).arrival(tour.getArrival()).transport(tour.getTransport()).distance(tour.getDistance()).duration(tour.getDuration()).imgUrl(tour.getImgUrl()).popularity(tour.getPopularity()).childFriendliness(tour.getChildFriendliness()).build();
    }
}
