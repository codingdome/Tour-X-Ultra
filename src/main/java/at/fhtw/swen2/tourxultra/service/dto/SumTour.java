package at.fhtw.swen2.tourxultra.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SumTour {
    Tour tour;
    double avgDuration;
    double avgRating;
    double avgDifficulty;
}
