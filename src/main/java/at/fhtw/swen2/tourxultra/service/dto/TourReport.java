package at.fhtw.swen2.tourxultra.service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TourReport {
    Tour tour;
    List<Log> tourLogs;
}