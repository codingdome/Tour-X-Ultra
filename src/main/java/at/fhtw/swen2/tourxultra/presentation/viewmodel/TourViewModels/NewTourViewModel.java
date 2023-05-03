package at.fhtw.swen2.tourxultra.presentation.viewmodel.TourViewModels;

import at.fhtw.swen2.tourxultra.service.TourService;
import at.fhtw.swen2.tourxultra.service.dto.Tour;
import at.fhtw.swen2.tourxultra.service.io.MapQuestApiAssistant;
import javafx.beans.property.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewTourViewModel {
    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty description = new SimpleStringProperty();
    private SimpleStringProperty departure = new SimpleStringProperty();
    private SimpleStringProperty arrival = new SimpleStringProperty();
    private SimpleStringProperty transport = new SimpleStringProperty();
    private SimpleIntegerProperty distance = new SimpleIntegerProperty();
    private SimpleIntegerProperty duration = new SimpleIntegerProperty();
    private SimpleStringProperty feedback = new SimpleStringProperty();
    private Tour tour;

    @Autowired
    private TourListViewModel tourListViewModel;

    @Autowired
    private TourService tourService;

    MapQuestApiAssistant mapQuestApiAssistant = new MapQuestApiAssistant();

    public NewTourViewModel() {
    }

    public NewTourViewModel(Tour tour) {
        this.tour = tour;
        this.id = new SimpleLongProperty(tour.getId());
        this.name = new SimpleStringProperty(tour.getName());
        this.description = new SimpleStringProperty(tour.getDescription());
        this.departure = new SimpleStringProperty(tour.getDeparture());
        this.arrival = new SimpleStringProperty(tour.getArrival());
        this.transport = new SimpleStringProperty(tour.getTransport());
        this.distance = new SimpleIntegerProperty(tour.getDistance());
        this.duration = new SimpleIntegerProperty(tour.getDuration());
    }

    public void addNewTour() {
        Tour tour = Tour.builder().name(getName()).description(getDescription()).departure(getDeparture()).arrival(getArrival()).transport(getTransport()).distance(getDistance()).duration(getDuration()).build();
        mapQuestApiAssistant.retrieveData(tour.getDeparture(), tour.getArrival());
        tour.setImgUrl(mapQuestApiAssistant.getImageUrl());
        //1 add tour
        tour = tourService.addNew(tour);
        if (tour != null) {
            resetAllTextfieldsAndFeedback();
        }
        //2 update ui
        tourListViewModel.addItem(tour);
    }

    private void resetAllTextfieldsAndFeedback() {
        setName("");
        setDescription("");
        setDeparture("");
        setArrival("");
        setTransport("");
        setDistance(0);
        setDuration(0);
        setFeedback("Successfully added!");
    }

    // -> getter and setter -> ...
    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
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

    public String getTransport() {
        return transport.get();
    }

    public SimpleStringProperty transportProperty() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport.set(transport);
    }

    public int getDistance() {
        return distance.get();
    }

    public SimpleIntegerProperty distanceProperty() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance.set(distance);
    }

    public int getDuration() {
        return duration.get();
    }

    public SimpleIntegerProperty durationProperty() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration.set(duration);
    }

    public String getFeedback() {
        return feedback.get();
    }

    public SimpleStringProperty feedbackProperty() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback.set(feedback);
    }
}
