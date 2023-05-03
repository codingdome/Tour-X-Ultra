package at.fhtw.swen2.tourxultra.service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class SummarizeReport {
    Date date;
    List<SumTour> sumTours;
}
