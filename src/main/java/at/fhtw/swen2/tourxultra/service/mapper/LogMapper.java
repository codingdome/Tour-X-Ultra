package at.fhtw.swen2.tourxultra.service.mapper;

import at.fhtw.swen2.tourxultra.persistence.entities.LogEntity;
import at.fhtw.swen2.tourxultra.persistence.entities.TourEntity;
import at.fhtw.swen2.tourxultra.service.dto.Log;
import at.fhtw.swen2.tourxultra.service.dto.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogMapper extends AbstractMapper<LogEntity, Log> {

    @Autowired
    TourMapper tourMapper;

    @Override
    public Log fromEntity(LogEntity entity) {
        return Log.builder().id(entity.getId()).tour(tourMapper.fromEntity(entity.getTour())).date(entity.getDate()).comment(entity.getComment()).difficulty(entity.getDifficulty()).duration(entity.getDuration()).rating(entity.getRating()).build();
    }

    @Override
    public LogEntity toEntity(Log log) {
        return LogEntity.builder().id(log.getId()).tour(tourMapper.toEntity(log.getTour())).date(log.getDate()).comment(log.getComment()).difficulty(log.getDifficulty()).duration(log.getDuration()).rating(log.getRating()).build();
    }
}
