package at.fhtw.swen2.tourxultra.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tour {
    private Long id;
    private String name;
    private String description;
    private String departure;
    private String arrival;
    private String transport;
    private int distance;
    private int duration;
    private String imgUrl;
    private int popularity;
    private int childFriendliness;
}
