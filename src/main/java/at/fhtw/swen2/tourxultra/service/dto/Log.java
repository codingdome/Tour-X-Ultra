package at.fhtw.swen2.tourxultra.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Log {
    private Long id;
    private Tour tour;
    private String date;
    private String comment;
    private int difficulty;
    private int duration;
    private int rating;
}
