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
        //collect all values for updating tour:
        Long currentPopularity = logRepository.countByTour(tourMapper.toEntity(log.getTour()));

        //DISTANCE:
        int distance = log.getTour().getDistance();
        //AVG DURATION + DIFFICULT after save log:
        //save new log
        LogEntity logEntity = logRepository.save(logMapper.toEntity(log));

        Double averageDifficulty = logRepository.getAverageDifficulty();
        Double averageDuration = logRepository.getAverageDuration();


        //update tour
        TourEntity updatetTourEntity = logEntity.getTour();

        updatetTourEntity.setPopularity((int) (currentPopularity + 1));
        updatetTourEntity.setChildFriendliness(childFriendlinessCalculation.calculateNewChildFriendliness(distance, averageDifficulty, averageDuration));

        tourRepository.save(updatetTourEntity);

        System.out.println(logRepository.findAll());
        System.out.println(tourRepository.findAll());

        logEntity.setTour(updatetTourEntity);

        return logMapper.fromEntity(logEntity);
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
        LogEntity logEntity = logRepository.save(logMapper.toEntity(log));
        return logMapper.fromEntity(logEntity);
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
}
