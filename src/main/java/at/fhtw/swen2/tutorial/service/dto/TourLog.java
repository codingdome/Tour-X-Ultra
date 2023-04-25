package at.fhtw.swen2.tutorial.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TourLog {

    private Long id;

    private Long tourId;
    private String time;
    private String comment;
    private String difficulty;
    private String duration;
    private String rating;

}
