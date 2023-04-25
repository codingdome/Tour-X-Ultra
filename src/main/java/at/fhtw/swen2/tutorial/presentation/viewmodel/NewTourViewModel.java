package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.service.TourService;
import at.fhtw.swen2.tutorial.service.dto.Person;
import at.fhtw.swen2.tutorial.service.dto.Tour;
import javafx.beans.property.*;
import net.bytebuddy.build.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class NewTourViewModel {

    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty description = new SimpleStringProperty();
    private SimpleStringProperty type = new SimpleStringProperty();
    private SimpleStringProperty origin = new SimpleStringProperty();
    private SimpleStringProperty to = new SimpleStringProperty();
    private SimpleStringProperty distance = new SimpleStringProperty();
    private SimpleStringProperty time = new SimpleStringProperty();
    private SimpleStringProperty information = new SimpleStringProperty();

    private Tour tour;

    @Autowired
    private TourService tourService;

    @Autowired
    private TourListViewModel tourListViewModel;

    public NewTourViewModel() {

    }

    public NewTourViewModel(Tour tour) {
        this.tour = tour;
        this.id = new SimpleLongProperty(tour.getId());
        this.name = new SimpleStringProperty(tour.getName());
        this.description = new SimpleStringProperty(tour.getDescription());
        this.type = new SimpleStringProperty(tour.getType());
        this.origin = new SimpleStringProperty(tour.getOrigin());
        this.to = new SimpleStringProperty(tour.getTo());
        this.distance = new SimpleStringProperty(tour.getDistance());
        this.time = new SimpleStringProperty(tour.getTime());
        this.information = new SimpleStringProperty(tour.getInformation());
    }

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

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getOrigin() {
        return origin.get();
    }

    public SimpleStringProperty originProperty() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin.set(origin);
    }

    public String getTo() {
        return to.get();
    }

    public SimpleStringProperty toProperty() {
        return to;
    }

    public void setTo(String to) {
        this.to.set(to);
    }

    public String getDistance() {
        return distance.get();
    }

    public SimpleStringProperty distanceProperty() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance.set(distance);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getInformation() {
        return information.get();
    }

    public SimpleStringProperty informationProperty() {
        return information;
    }

    public void setInformation(String information) {
        this.information.set(information);
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public void addNewTour() {

        Tour tour = Tour.builder().name(getName()).description(getDescription()).type(getType()).origin(getOrigin()).to(getTo()).distance(getDistance()).time(getTime()).information(getInformation()).build();
        tour = tourService.addNew(tour);
        tourListViewModel.addItem(tour);
        System.out.println(tourService.getTourList());
    }
}
