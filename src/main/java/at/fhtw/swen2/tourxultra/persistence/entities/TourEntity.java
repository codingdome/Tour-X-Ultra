package at.fhtw.swen2.tourxultra.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TourEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public void setId(Long id) {
        if (this.id == null) {
            this.id = id;
        }
    }
}
