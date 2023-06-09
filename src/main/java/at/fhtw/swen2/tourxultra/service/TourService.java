package at.fhtw.swen2.tourxultra.service;

import at.fhtw.swen2.tourxultra.service.dto.SumTour;
import at.fhtw.swen2.tourxultra.service.dto.SummarizeReport;
import at.fhtw.swen2.tourxultra.service.dto.Tour;
import at.fhtw.swen2.tourxultra.service.dto.TourReport;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface TourService {

    Tour addNew(Tour tour);

    Tour update(Tour tour);

    Tour importTour(File file) throws IOException;

    void delete(Tour tour);

    List<Tour> getTourList();

    TourReport createTourReport(Tour tour) throws IOException;

    SummarizeReport createSummarizeReport();

}
