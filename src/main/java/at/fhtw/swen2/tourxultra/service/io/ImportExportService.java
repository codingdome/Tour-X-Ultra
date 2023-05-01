package at.fhtw.swen2.tourxultra.service.io;

import at.fhtw.swen2.tourxultra.service.dto.Tour;

import java.io.File;

public interface ImportExportService {

    void exportTour(Tour tour);

    File importFile();

    Tour importTour();
}
