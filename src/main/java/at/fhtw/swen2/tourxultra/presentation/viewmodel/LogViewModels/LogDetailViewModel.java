package at.fhtw.swen2.tourxultra.presentation.viewmodel.LogViewModels;

import at.fhtw.swen2.tourxultra.presentation.view.ApplicationView;
import at.fhtw.swen2.tourxultra.presentation.viewmodel.TourViewModels.TourListViewModel;
import at.fhtw.swen2.tourxultra.service.LogService;
import at.fhtw.swen2.tourxultra.service.TourService;
import at.fhtw.swen2.tourxultra.service.dto.Log;
import at.fhtw.swen2.tourxultra.service.dto.Tour;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogDetailViewModel {
    private SimpleStringProperty tour_name = new SimpleStringProperty();
    private SimpleStringProperty tour_description = new SimpleStringProperty();
    private SimpleStringProperty date = new SimpleStringProperty();
    private SimpleStringProperty comment = new SimpleStringProperty();
    private SimpleIntegerProperty difficulty = new SimpleIntegerProperty();
    private SimpleIntegerProperty duration = new SimpleIntegerProperty();
    private SimpleIntegerProperty rating = new SimpleIntegerProperty();
    private SimpleStringProperty feedback = new SimpleStringProperty();

    @Autowired
    private TourService tourService;

    @Autowired
    private LogService logService;

    @Autowired
    private TourListViewModel tourListViewModel;

    @Autowired
    private TourPreviewViewModel tourPreviewViewModel;

    @Autowired
    private LogListViewModel logListViewModel;

    @Autowired
    public ApplicationView applicationView;
    
    private Log log;

    private Tour tour;

    public LogDetailViewModel() {

    }

    public LogDetailViewModel(Log log) {
        this.log = log;
        this.date = new SimpleStringProperty(log.getDate());
        this.comment = new SimpleStringProperty(log.getComment());
        this.difficulty = new SimpleIntegerProperty(log.getDifficulty());
        this.duration = new SimpleIntegerProperty(log.getDuration());
        this.rating = new SimpleIntegerProperty(log.getRating());
        this.feedback = new SimpleStringProperty();
        this.tour_name = new SimpleStringProperty(log.getTour().getName());
        this.tour_description = new SimpleStringProperty(log.getTour().getDescription());
    }

    public void updateLog() {
        System.out.println(log);
        Log updatedLog = Log.builder().tour(getTour()).id(log.getId()).date(getDate()).comment(getComment()).duration(getDuration()).difficulty(getDifficulty()).rating(getRating()).build();
        System.out.println(updatedLog);
        System.out.println(logService.update(updatedLog));
        logListViewModel.updateList(updatedLog);
        System.out.println(logService.getLogList());
        feedback.set("Update Successfully!");
    }

    public void deleteLog() {
        logService.delete(log);
        logListViewModel.deleteLog(log);
        System.out.println(logService.getLogList());
        applicationView.mainTabPane.getSelectionModel().select(3);
        applicationView.logDetailsTab.setDisable(true);
    }

    public String getTour_name() {
        return tour_name.get();
    }

    public SimpleStringProperty tour_nameProperty() {
        return tour_name;
    }

    public void setTour_name(String tour_name) {
        this.tour_name.set(tour_name);
    }

    public String getTour_description() {
        return tour_description.get();
    }

    public SimpleStringProperty tour_descriptionProperty() {
        return tour_description;
    }

    public void setTour_description(String tour_description) {
        this.tour_description.set(tour_description);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getComment() {
        return comment.get();
    }

    public SimpleStringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public int getDifficulty() {
        return difficulty.get();
    }

    public SimpleIntegerProperty difficultyProperty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty.set(difficulty);
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

    public int getRating() {
        return rating.get();
    }

    public SimpleIntegerProperty ratingProperty() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating.set(rating);
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

    public TourService getTourService() {
        return tourService;
    }

    public void setTourService(TourService tourService) {
        this.tourService = tourService;
    }

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public TourListViewModel getTourListViewModel() {
        return tourListViewModel;
    }

    public void setTourListViewModel(TourListViewModel tourListViewModel) {
        this.tourListViewModel = tourListViewModel;
    }

    public TourPreviewViewModel getTourPreviewViewModel() {
        return tourPreviewViewModel;
    }

    public void setTourPreviewViewModel(TourPreviewViewModel tourPreviewViewModel) {
        this.tourPreviewViewModel = tourPreviewViewModel;
    }

    public ApplicationView getApplicationView() {
        return applicationView;
    }

    public void setApplicationView(ApplicationView applicationView) {
        this.applicationView = applicationView;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
