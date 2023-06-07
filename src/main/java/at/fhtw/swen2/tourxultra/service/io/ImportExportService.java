package at.fhtw.swen2.tourxultra.service.io;

import at.fhtw.swen2.tourxultra.service.dto.SummarizeReport;
import at.fhtw.swen2.tourxultra.service.dto.Tour;
import at.fhtw.swen2.tourxultra.service.dto.TourReport;

import java.io.File;

public interface ImportExportService {

    void exportTour(Tour tour);

    File importFile();

    void exportTourReport(TourReport tourReport);

    void exportSummarizedReport(SummarizeReport summarizeReport) throws Exception;

    Tour importTour();
}
