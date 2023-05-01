package at.fhtw.swen2.tourxultra.service.impl;

import at.fhtw.swen2.tourxultra.presentation.viewmodel.TourViewModels.TourListViewModel;
import at.fhtw.swen2.tourxultra.service.LogService;
import com.google.gson.Gson;
import at.fhtw.swen2.tourxultra.persistence.entities.TourEntity;
import at.fhtw.swen2.tourxultra.persistence.repositories.TourRepository;
import at.fhtw.swen2.tourxultra.service.TourService;
import at.fhtw.swen2.tourxultra.service.dto.Tour;
import at.fhtw.swen2.tourxultra.service.mapper.TourMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourMapper tourMapper;

    @Autowired
    private LogService logService;

    @Override
    public Tour addNew(Tour tour) {
        if (tour == null) {
            return null;
        }
        TourEntity tourEntity = tourRepository.save(tourMapper.toEntity(tour));
        System.out.println(tourRepository.findAll());
        return tourMapper.fromEntity(tourEntity);
    }

    @Override
    public Tour update(Tour tour) {
        if (tour == null) {
            return null;
        }
        tour.setPopularity(Math.toIntExact(logService.countLogsForTour(tour)));
        TourEntity tourEntity = tourRepository.save(tourMapper.toEntity(tour));
        System.out.println(tourRepository.findAll());
        return tourMapper.fromEntity(tourEntity);
    }

    @Override
    public void delete(Tour tour) {
        TourEntity entity = tourMapper.toEntity(tour);
        tourRepository.delete(entity);
    }

    @Override
    public List<Tour> getTourList() {
        return tourMapper.fromEntity(tourRepository.findAll());
    }

    @Override
    public Tour importTour(File file) throws IOException {

        //print path of file
        System.out.println(file.getAbsolutePath());
        //get tour from file
        Tour importTour = fileToTour(file);
        //check if tour exists (for list view)
//        tourRepository.save(tourMapper.toEntity(importTour));
        if (tourRepository.findById(importTour.getId()).isPresent()) {
            //it already exists:
            TourEntity tourEntity = tourRepository.save(tourMapper.toEntity(importTour));
        } else {
            //it does not exist:
            TourEntity tourEntity = tourRepository.save(tourMapper.toEntity(importTour));
        }
        return null;
    }


    private Tour fileToTour(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        String json = sb.toString();
        Gson gson = new Gson();
        Tour tour = gson.fromJson(json, Tour.class);
        return tour;
    }
}
