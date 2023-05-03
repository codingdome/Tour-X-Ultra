package at.fhtw.swen2.tourxultra.service.impl;

import at.fhtw.swen2.tourxultra.service.LogService;
import at.fhtw.swen2.tourxultra.service.SummarizeReportService;
import at.fhtw.swen2.tourxultra.service.TourService;
import at.fhtw.swen2.tourxultra.service.dto.SumTour;
import at.fhtw.swen2.tourxultra.service.dto.SummarizeReport;
import at.fhtw.swen2.tourxultra.service.dto.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SummarizeReportServiceImpl implements SummarizeReportService {

    @Autowired
    TourService tourService;

    @Autowired
    LogService logService;

    @Override
    public SummarizeReport createSummarizeReport() {
        LocalDate today = LocalDate.now();
        Date date = Date.from(today.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant());
        List<Tour> tours = tourService.getTourList();
        List<SumTour> sumTours = new ArrayList<>();
        for (Tour tour : tours) {
            sumTours.add(SumTour.builder().tour(tour).avgDifficulty(logService.getAVGdifficultyForTour(tour)).avgDuration(logService.getAVGdurationForTour(tour)).avgRating(logService.getAVGratingForTour(tour)).build());
        }
        return SummarizeReport.builder().sumTours(sumTours).date(date).build();
    }
}
