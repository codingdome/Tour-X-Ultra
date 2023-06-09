package at.fhtw.swen2.tourxultra.presentation.viewmodel.TourViewModels;

import at.fhtw.swen2.tourxultra.presentation.view.ApplicationView;
import at.fhtw.swen2.tourxultra.presentation.viewmodel.LogViewModels.TourPreviewViewModel;
import at.fhtw.swen2.tourxultra.service.TourService;
import at.fhtw.swen2.tourxultra.service.dto.Tour;
import at.fhtw.swen2.tourxultra.service.impl.ImportExportServiceImpl;
import at.fhtw.swen2.tourxultra.service.impl.TourServiceImpl;
import at.fhtw.swen2.tourxultra.service.io.ImportExportService;
import com.google.gson.Gson;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class TourDetailViewModel {
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty description = new SimpleStringProperty();
    private SimpleStringProperty departure = new SimpleStringProperty();
    private SimpleStringProperty arrival = new SimpleStringProperty();
    private SimpleStringProperty transport = new SimpleStringProperty();
    private SimpleStringProperty imgUrl = new SimpleStringProperty();
    private SimpleIntegerProperty distance = new SimpleIntegerProperty();
    private SimpleIntegerProperty duration = new SimpleIntegerProperty();
    private SimpleIntegerProperty popularity = new SimpleIntegerProperty();
    private SimpleIntegerProperty childFriendliness = new SimpleIntegerProperty();
    private SimpleStringProperty feedback = new SimpleStringProperty();

    @Autowired
    private TourService tourService;

    @Autowired
    private TourListViewModel tourListViewModel;

    @Autowired
    private TourPreviewViewModel tourPreviewViewModel;

    @Autowired
    public ApplicationView applicationView;

    private ImportExportService importExportService = new ImportExportServiceImpl();

    private Tour tour;

    private File file;

    public TourDetailViewModel() {

    }

    public TourDetailViewModel(Tour tour) {
        this.tour = tour;
        this.name = new SimpleStringProperty(tour.getName());
        this.description = new SimpleStringProperty(tour.getDescription());
        this.departure = new SimpleStringProperty(tour.getDeparture());
        this.arrival = new SimpleStringProperty(tour.getArrival());
        this.transport = new SimpleStringProperty(tour.getTransport());
        this.imgUrl = new SimpleStringProperty(tour.getImgUrl());
        this.distance = new SimpleIntegerProperty(tour.getDistance());
        this.duration = new SimpleIntegerProperty(tour.getDuration());
        this.popularity = new SimpleIntegerProperty(tour.getPopularity());
        this.childFriendliness = new SimpleIntegerProperty(tour.getChildFriendliness());
        this.feedback = new SimpleStringProperty();
    }

    public void updateTour() {
        System.out.println(tour);
        Tour updatedTour = Tour.builder().id(tour.getId()).name(getName()).description(getDescription()).departure(getDeparture()).arrival(getArrival()).transport(getTransport()).distance(getDistance()).duration(getDuration()).imgUrl(getImgUrl()).popularity(getPopularity()).childFriendliness(getChildFriendliness()).build();
        System.out.println(updatedTour);
        System.out.println(tourService.update(updatedTour));
        tourListViewModel.updateList(updatedTour);
        tourPreviewViewModel.updatePreview(updatedTour);
        System.out.println(tourService.getTourList());
        feedback.set("Update Successfully!");
    }

    public void deleteTour() {
        tourService.delete(tour);
        tourListViewModel.deleteTour(tour);
        System.out.println(tourService.getTourList());
        applicationView.mainTabPane.getSelectionModel().select(1);
        applicationView.tourDetailsTab.setDisable(true);
        applicationView.tourLogsTab.setDisable(true);
        applicationView.logDetailsTab.setDisable(true);
    }

    public void importTour() throws IOException {
        //Tour tour = fileToTour(file);
        Tour importedTour = tourService.importTour(file);
        if (tourListViewModel.getTourListItems().contains(importedTour)) {
            tourListViewModel.updateList(importedTour);
        } else {
            tourListViewModel.addItem(importedTour);
        }
        //tourService.importTour(file);
    }

    public void exportTour() {
        importExportService.exportTour(tour);
    }

    public void createTourReport() throws IOException {
//        System.out.println(tourService.createTourReport(tour));
        importExportService.exportTourReport(tourService.createTourReport(tour));
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

    public String getImgUrl() {
        return imgUrl.get();
    }

    public SimpleStringProperty imgUrlProperty() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl.set(imgUrl);
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

    public int getPopularity() {
        return popularity.get();
    }

    public SimpleIntegerProperty popularityProperty() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity.set(popularity);
    }

    public int getChildFriendliness() {
        return childFriendliness.get();
    }

    public SimpleIntegerProperty childFriendlinessProperty() {
        return childFriendliness;
    }

    public void setChildFriendliness(int childFriendliness) {
        this.childFriendliness.set(childFriendliness);
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    private Tour fileToTour(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        String json = sb.toString();
        Gson gson = new Gson();
        Tour tour = gson.fromJson(json, Tour.class);
        return tour;
    }
}
