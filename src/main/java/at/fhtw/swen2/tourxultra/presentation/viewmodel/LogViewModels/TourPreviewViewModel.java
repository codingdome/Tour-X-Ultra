package at.fhtw.swen2.tourxultra.presentation.viewmodel.LogViewModels;

import at.fhtw.swen2.tourxultra.service.dto.Log;
import at.fhtw.swen2.tourxultra.service.dto.Tour;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import org.springframework.stereotype.Component;

@Component
public class TourPreviewViewModel {

    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty departure = new SimpleStringProperty();
    private SimpleStringProperty arrival = new SimpleStringProperty();


    public TourPreviewViewModel() {
        this.name = new SimpleStringProperty(getName());
        this.departure = new SimpleStringProperty(getDeparture());
        this.arrival = new SimpleStringProperty(getArrival());
    }

    public void updatePreview(Tour tour) {
        name.set(tour.getName());
        departure.set(tour.getDeparture());
        arrival.set(tour.getArrival());
    }

    // -> getter and setter -> ...


    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDeparture() {
        return departure.get();
    }

    public SimpleStringProperty departureProperty() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure.set(departure);
    }

    public String getArrival() {
        return arrival.get();
    }

    public SimpleStringProperty arrivalProperty() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival.set(arrival);
    }
}
