package at.fhtw.swen2.tourxultra.service.impl;

import at.fhtw.swen2.tourxultra.persistence.entities.LogEntity;
import at.fhtw.swen2.tourxultra.persistence.entities.TourEntity;
import at.fhtw.swen2.tourxultra.persistence.repositories.LogRepository;
import at.fhtw.swen2.tourxultra.persistence.repositories.TourRepository;
import at.fhtw.swen2.tourxultra.service.LogService;
import at.fhtw.swen2.tourxultra.service.dto.Log;
import at.fhtw.swen2.tourxultra.service.dto.Tour;
import at.fhtw.swen2.tourxultra.service.mapper.LogMapper;
import at.fhtw.swen2.tourxultra.service.mapper.TourMapper;
import at.fhtw.swen2.tourxultra.service.util.ChildFriendlinessCalculation;
import at.fhtw.swen2.tourxultra.service.util.ChildFriendlinessCaluclationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private TourMapper tourMapper;

    private ChildFriendlinessCalculation childFriendlinessCalculation = new ChildFriendlinessCaluclationImpl();

    @Override
    public Log addNew(Log log) {
        if (log == null) {
            return null;
        }
        return handleAddUpdateLog(log);
    }

    @Override
    public void delete(Log log) {
        LogEntity entity = logMapper.toEntity(log);
        logRepository.delete(entity);
    }

    @Override
    public Log update(Log log) {
        if (log == null) {
            return null;
        }
        return handleAddUpdateLog(log);
    }

    @Override
    public List<Log> getLogList() {
        return logMapper.fromEntity(logRepository.findAll());
    }

    @Override
    public List<Log> getLogListByTour(Tour tour) {
        return logMapper.fromEntity(logRepository.findByTour(tourMapper.toEntity(tour)));
    }

    @Override
    public Long countLogsForTour(Tour tour) {
        return logRepository.countByTour(tourMapper.toEntity(tour));
    }

    @Override
    public Double getAVGdifficultyForTour(Tour tour) {
        return logRepository.getAverageDifficultyForTour(tourMapper.toEntity(tour));
    }

    @Override
    public Double getAVGdurationForTour(Tour tour) {
        return logRepository.getAverageDurationForTour(tourMapper.toEntity(tour));
    }

    @Override
    public Double getAVGratingForTour(Tour tour) {
        return logRepository.getAverageRatingForTour(tourMapper.toEntity(tour));
    }

    private Log handleAddUpdateLog(Log log) {
        // 1 save new log
        LogEntity logEntity = logRepository.save(logMapper.toEntity(log));

        // 2 collect all data to update tour
        //popularity
        Long newPopularity = logRepository.countByTour(tourMapper.toEntity(log.getTour()));
        //avg difficulty
        Double newAvgDifficulty = logRepository.getAverageDifficultyForTour(tourMapper.toEntity(log.getTour()));
        //avg duration
        Double newAvgDuration = logRepository.getAverageDurationForTour(tourMapper.toEntity(log.getTour()));
        //distance
        int distance = log.getTour().getDistance();

        // 3 calculate childFriendliness
        int newChildFriendliness = childFriendlinessCalculation.calculateNewChildFriendliness(distance, newAvgDifficulty, newAvgDuration);

        // 4 get Tour to update
        TourEntity tourEntityToUpdate = logEntity.getTour();

        // 5 set new values for this tour
        tourEntityToUpdate.setPopularity(Math.toIntExact(newPopularity));
        tourEntityToUpdate.setChildFriendliness(newChildFriendliness);

        // 6 save updated tour
        TourEntity tourResult = tourRepository.save(tourEntityToUpdate);

        // 7 update the tour for the log entity
        logEntity.setTour(tourResult);

        // 8 return new log
        return logMapper.fromEntity(logEntity);
    }
}
