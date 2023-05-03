package at.fhtw.swen2.tourxultra.service;

import at.fhtw.swen2.tourxultra.service.dto.Log;
import at.fhtw.swen2.tourxultra.service.dto.Tour;

import java.util.List;

public interface LogService {

    Log addNew(Log log);

    void delete(Log log);

    Log update(Log log);

    Long countLogsForTour(Tour tour);

    List<Log> getLogList();

    List<Log> getLogListByTour(Tour tour);

    Double getAVGdifficultyForTour(Tour tour);

    Double getAVGdurationForTour(Tour tour);

    Double getAVGratingForTour(Tour tour);

}
