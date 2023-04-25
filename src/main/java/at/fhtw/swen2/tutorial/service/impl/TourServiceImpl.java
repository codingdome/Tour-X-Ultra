package at.fhtw.swen2.tutorial.service.impl;

import at.fhtw.swen2.tutorial.persistence.entities.PersonEntity;
import at.fhtw.swen2.tutorial.persistence.entities.TourEntity;
import at.fhtw.swen2.tutorial.persistence.entities.TourLogEntity;
import at.fhtw.swen2.tutorial.persistence.repositories.PersonRepository;
import at.fhtw.swen2.tutorial.persistence.repositories.TourLogRepository;
import at.fhtw.swen2.tutorial.persistence.repositories.TourRepository;
import at.fhtw.swen2.tutorial.service.PersonService;
import at.fhtw.swen2.tutorial.service.TourService;
import at.fhtw.swen2.tutorial.service.dto.Person;
import at.fhtw.swen2.tutorial.service.dto.Tour;
import at.fhtw.swen2.tutorial.service.mapper.PersonMapper;
import at.fhtw.swen2.tutorial.service.mapper.TourMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private TourMapper tourMapper;

    @Autowired
    private TourLogRepository tourLogRepository;

    @Override
    public List<Tour> getTourList() {
        return tourMapper.fromEntity(tourRepository.findAll());
    }

    @Override
    public Tour addNew(Tour tour) {
        if (tour == null) {
            return null;
        }

        //hier muss API call passieren
        //hier noch ein String mit URL
        //das hier erst wenn API fertig ->
        TourEntity entity = tourRepository.save(tourMapper.toEntity(tour));

        TourLogEntity tourLogEntity = TourLogEntity.builder().time("sdflk").tour(entity).build();

        tourLogRepository.save(tourLogEntity);

        tourLogRepository.findByTour(entity);

        return tourMapper.fromEntity(entity);
    }

}
