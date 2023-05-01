package at.fhtw.swen2.tourxultra.service;

import at.fhtw.swen2.tourxultra.service.dto.Tour;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface TourService {

    Tour addNew(Tour tour);

    Tour update(Tour tour);

    Tour importTour(File file) throws IOException;

    void delete(Tour tour);

    List<Tour> getTourList();

}
